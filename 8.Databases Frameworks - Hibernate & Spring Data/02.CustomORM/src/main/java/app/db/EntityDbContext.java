package app.db;

import app.db.annotations.Column;
import app.db.annotations.Entity;
import app.db.annotations.PrimaryKey;
import app.db.dbConstants.DbConstants;
import app.db.dbContracts.DbContext;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;

public class EntityDbContext<T> implements DbContext<T> {

    private final Class<T> klass;
    private final Connection connection;

    public EntityDbContext(Class<T> klass, Connection connection) throws SQLException {
        this.klass = klass;
        this.connection = connection;

        if (this.checksIfTableExists()) {
            this.updateTable();
        } else {
            this.createTable();
        }
    }

    public boolean persist(T entity) throws IllegalAccessException, SQLException {

        long value = getPrimaryKeyValue(entity);

        if (value > 0) {
            return update(entity);
        }

        return insert(entity);
    }

    /**
     * @param entity
     * @return true if the insert is correctly done
     * @throws SQLException
     */
    private boolean insert(T entity) throws SQLException {
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        getColumnFields()
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        String columnName = field.getAnnotation(Column.class)
                                .name();
                        Object value = field.get(entity);
                        columns.add(columnName);
                        values.add(value);

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        String columnsNames = String.join(", ", columns);
        String columnValues = values
                .stream()
                .map(value -> {
                    if (value instanceof String) {
                        return "\'" + value + "\'";
                    }

                    return value;
                })
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        String queryString = MessageFormat.format(
                DbConstants.INSERT_QUERY_TEMPLATE,
                getTableName(),
                columnsNames,
                columnValues
        );

        return connection.prepareStatement(queryString)
                .execute();
    }

    /**
     * @return list from type field with column fields
     */
    private List<Field> getColumnFields() {
        return Arrays.stream(klass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());
    }

    /**
     * @param entity
     * @return true if update is succsess
     * @throws IllegalAccessException
     * @throws SQLException
     */
    private boolean update(T entity) throws IllegalAccessException, SQLException {
        List<String> updateQueries =
                getColumnFields().stream()
                        .map(field -> {
                            field.setAccessible(true);
                            try {
                                String columnName = field.getAnnotation(Column.class)
                                        .name();
                                Object value = field.get(entity);
                                if (value instanceof String) {
                                    value = "\'" + value + "\'";
                                }

                                return MessageFormat.format(
                                        DbConstants.SET_QUERY_TEMPLATE,
                                        columnName,
                                        value
                                );
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .collect(Collectors.toList());

        String updateQueriesString = String.join(", ", updateQueries);

        Field primaryKey = getPrimaryKeyField();
        primaryKey.setAccessible(true);
        String primaryKeyName =
                primaryKey.getAnnotation(PrimaryKey.class)
                        .name();

        long primaryKeyValue =
                (long) primaryKey
                        .get(entity);

        String queryString = MessageFormat.format(
                DbConstants.UPDATE_QUERY_TEMPLATE,
                getTableName(),
                updateQueriesString,
                primaryKeyName,
                primaryKeyValue
        );

        return connection.prepareStatement(queryString)
                .execute();
    }


    /**
     * @return list from T
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Iterable<T> find() throws SQLException, InstantiationException, IllegalAccessException {

        return find(null);


    }

    /**
     * @param where
     * @return Iterable From T
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException Find all result with where clause
     *                                Cast all to T odjects and put them in T iterable
     *                                return iterable from T
     */
    public Iterable<T> find(String where) throws SQLException, InstantiationException, IllegalAccessException {

        String queryString = where == null
                ? DbConstants.SELECT_QUERY_TEMPLATE
                : DbConstants.SELECT_WHERE_QUERY_TEMPLATE;

        return find(queryString, where);

    }

    /**
     * @return T entity
     * @throws IllegalAccessException
     * @throws SQLException
     * @throws InstantiationException Return the first entity from database
     */
    public T findFirst() throws IllegalAccessException, SQLException, InstantiationException {
        return findFirst(null);
    }

    /**
     * @param where
     * @return T entity
     * @throws IllegalAccessException
     * @throws SQLException
     * @throws InstantiationException Return the first Result from database with Where clause like T entity
     */
    public T findFirst(String where) throws IllegalAccessException, SQLException, InstantiationException {

        String queryString = where == null
                ? DbConstants.SELECT_FIRST_QUERY_TEMPLATE
                : DbConstants.SELECT_FIRST_WHERE_QUERY_TEMPLATE;

        return ((List<T>) find(queryString, where)).get(0);
    }

    /**
     * @param id
     * @return T entity
     * @throws IllegalAccessException
     * @throws SQLException
     * @throws InstantiationException returns T entity by given id in database
     */
    public T findById(long id) throws IllegalAccessException, SQLException, InstantiationException {
        String whereString = MessageFormat.format(DbConstants.WHERE_ID_TEMPLATE, id);

        return findFirst(whereString);
    }

    @Override
    public boolean delete(String where) throws SQLException {
        String query = MessageFormat.format(DbConstants.DELITE_QUERY_TEMPLATE,
                this.getTableName(),
                where
        );

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        return preparedStatement.execute();
    }

    /**
     * @param rs
     * @return T List
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException This function get the Result set and make each find into T entity.
     *                                Put all T entities into T List and returns the T List
     */
    private Iterable<T> getList(ResultSet rs) throws SQLException, IllegalAccessException, InstantiationException {

        List<T> entitiesList = new ArrayList<>();

        while (rs.next()) {

            T entity = createEntity(rs);

            entitiesList.add(entity);
        }

        return entitiesList;

    }

    /**
     * @param rs
     * @return entity from T
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    private T createEntity(ResultSet rs) throws InstantiationException, IllegalAccessException, SQLException {
        T entity = klass.newInstance();

        setPrimaryKey(rs, entity);

        List<Field> fieldList = getDeclaredFields();

        fieldList.stream()
                .forEach(field -> {
                    setField(rs, entity, field);
                });
        return entity;
    }

    /**
     * @param rs
     * @param entity
     * @param field  set field accessible true
     *               get field column name
     *               set the field in the entity with the value from result set
     */
    private void setField(ResultSet rs, T entity, Field field) {

        String columnName = field
                .getAnnotation(Column.class).name();

        field.setAccessible(true);

        try {
            if (field.getType() == Long.class || field.getType() == long.class) {
                long value = rs.getLong(columnName);
                field.set(entity, value);
            } else if (field.getType() == String.class) {
                String value = rs.getString(columnName);
                field.set(entity, value);
            } else if (field.getType() == Integer.class || field.getType() == int.class) {
                int value = rs.getInt(columnName);
                field.set(entity, value);
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param rs
     * @param entity
     * @throws SQLException
     * @throws IllegalAccessException This function set the primary key of Entity from resultSet
     */
    private void setPrimaryKey(ResultSet rs, T entity) throws SQLException, IllegalAccessException {
        Field primaryKeyField = getPrimaryKeyField();
        primaryKeyField.setAccessible(true);
        String primaryKeyName = getPrimaryKeyName(primaryKeyField);
        long primaryKeyValue = rs.getLong(primaryKeyName);
        primaryKeyField.set(entity, primaryKeyValue);
    }

    /**
     * @return collection of declared fields woith annotation column
     */
    private List<Field> getDeclaredFields() {

        return Arrays.stream(klass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

    }

    /**
     * @return field from the klass, witch have annotation "PrimaryKey"
     * @throws RuntimeException if there is no such class
     */
    private Field getPrimaryKeyField() {
        return Arrays.stream(klass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(PrimaryKey.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException
                        ("No " + klass.getSimpleName() + "exists in database"));
    }

    /**
     * @param primaryKeyField
     * @return the name of primary key column
     */
    private String getPrimaryKeyName(Field primaryKeyField) {
        return primaryKeyField
                .getAnnotation(PrimaryKey.class)
                .name();
    }

    /**
     * @return the name of the table in the database
     */
    private String getTableName() {

        return klass.getAnnotation(Entity.class).name();

    }

    /**
     * @param queryString
     * @param where
     * @return iterable from T with entities witch are filled by the result from the query
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private Iterable<T> find(String queryString, String where) throws SQLException, InstantiationException, IllegalAccessException {

        String queryWithParams = MessageFormat.format(queryString,
                getTableName(),
                where);

        PreparedStatement stmt = connection
                .prepareStatement(queryWithParams);

        ResultSet rs = stmt.executeQuery();

        return getList(rs);
    }

    /**
     * @param entity
     * @return primary key value
     * @throws IllegalAccessException get the primary key field and set accessible = true
     *                                return the value of primary key from the entity;
     */
    private long getPrimaryKeyValue(T entity) throws IllegalAccessException {
        Field primaryKeyField = getPrimaryKeyField();
        primaryKeyField.setAccessible(true);
        return (long) primaryKeyField.get(entity);
    }

    /**
     * @throws SQLException
     *
     * This method creates a new Table in the datebase
     */
    private void createTable() throws SQLException {

        Field primaryKeyField = getPrimaryKeyField();
        String primaryKeyColumnName = getPrimaryKeyName(primaryKeyField);
        String primaryKeyColumnType = getColumnTypeString(primaryKeyField);

        String primaryKeyColumnDefinition = MessageFormat
                .format(
                        DbConstants.PRIMARY_KEY_DEFINITION_TEMPLATE,
                        primaryKeyColumnName,
                        primaryKeyColumnType
                );

        List<Field> columnsFields = getColumnFields();

        List<String> columnsParams = new ArrayList<>();

        getColumnDefinition(columnsFields, columnsParams);

        String createStatementBody = MessageFormat
                .format(
                        DbConstants.STATEMENT_BODY_TEMPLATE,
                        primaryKeyColumnDefinition,
                        columnsParams.stream().collect(Collectors.joining(", "))
                );

        String query = MessageFormat
                .format(
                        DbConstants.CREATE_TABLE_QUERY_TEMPLATE,
                        getTableName(),
                        createStatementBody
                );

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.execute();
    }

    /**
     * @param columnsFields
     * @param columnsParams
     *
     * Gets columns definition and put them into parameter "columnsParams"
     */
    private void getColumnDefinition(List<Field> columnsFields, List<String> columnsParams) {

        columnsFields
                .stream()
                .forEach(field -> {
                    String columnName = field.getDeclaredAnnotation(Column.class).name();
                    String columnType = getColumnTypeString(field);

                    String columnDefinition = MessageFormat
                            .format(
                                    DbConstants.COLUMN_DEFINITION_TEMPLATE,
                                    columnName,
                                    columnType
                            );

                    columnsParams.add(columnDefinition);
                });
    }

    /**
     * @param field
     * @return the type of the field in String format
     * Works only for string , long and integer
     */
    private String getColumnTypeString(Field field) {

        if (field.getType() == long.class || field.getType() == Long.class || field.getType() == int.class) {
            return "INT";
        } else if (field.getType() == String.class) {
            return "VARCHAR(255)";
        }else if(field.getType() == Integer.class || field.getType() == int.class){
            return "INT(11)";
        }

        return null;
    }

    /**
     * @return true if the table exists and false if the table dont exists
     * @throws SQLException
     */
    private boolean checksIfTableExists() throws SQLException {

        String query = String
                .format(DbConstants.SELECT_TABLE_NAME_QUERY_TEMPLATE,
                        getTableName()
                );

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    /**
     * @throws SQLException
     *
     * Updates already existing table
     */
    private void updateTable() throws SQLException {

        List<String> entityColumnsNames = getEntityColumnNames();

        entityColumnsNames.add(getPrimaryKeyField().getDeclaredAnnotation(PrimaryKey.class).name());

        List<String> databaseTableColumnsNames = getDatabaseTableColumnsNames();

        List<String> newColumnsNames = getNewColumnsNames(entityColumnsNames,databaseTableColumnsNames);

        List<Field> newFields = getNewFields(newColumnsNames);

        List<String> columnsDefinitions = new ArrayList<>();

        newFields.stream().forEach(field -> {
            String columnDefinition = MessageFormat
                    .format(DbConstants.ADD_COLUMN_QUERY_TEMPLATE,
                            field.getDeclaredAnnotation(Column.class).name(),
                            this.getColumnTypeString(field)
                    );

            columnsDefinitions.add(columnDefinition);
        });

        String queryBody = String.join(DbConstants.COMMA_DELIMITER, columnsDefinitions);

        String query = MessageFormat
                .format(
                        DbConstants.ALTER_TABLE_QUERY_TEMPLATE,
                        this.getTableName(),
                        queryBody
                );

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        preparedStatement.execute();
    }

    /**
     * @param newColumnsNames
     * @return fields that have to be made
     */
    private List<Field> getNewFields(List<String> newColumnsNames) {

        return getColumnFields()
                .stream()
                .filter(field -> {
                    return newColumnsNames.contains(field.getDeclaredAnnotation(Column.class).name());
                })
                .collect(Collectors.toList());

    }

    /**
     * @param entityColumnsNames
     * @param databaseTableColumnsNames
     * @return names of the new columns that have to be made
     */
    private List<String> getNewColumnsNames(List<String> entityColumnsNames,List<String> databaseTableColumnsNames) {

        return entityColumnsNames
                .stream()
                .filter(columnName -> !databaseTableColumnsNames.contains(columnName))
                .collect(Collectors.toList());

    }

    /**
     * @return list with strings full will names of the colums
     */
    private List<String> getEntityColumnNames() {

        return getColumnFields()
                .stream()
                .map(field -> {
                    return field.getDeclaredAnnotation(Column.class).name();
                })
                .collect(Collectors.toList());
    }

    /**
     * @return list with names of database tables
     * @throws SQLException
     */
    private List<String> getDatabaseTableColumnsNames() throws SQLException {

        String query = String
                .format("SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = '%s'",
                        this.getTableName()
                );

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> columnsNames = new ArrayList<>();

        while (resultSet.next()) {
            columnsNames.add(resultSet.getString(1));
        }

        return columnsNames;
    }
}
