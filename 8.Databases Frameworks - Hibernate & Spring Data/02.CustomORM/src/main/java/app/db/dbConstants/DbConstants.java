package app.db.dbConstants;

public final class DbConstants {

    private DbConstants(){
    }

    public static final String SELECT_QUERY_TEMPLATE = "SELECT * FROM {0}";
    public static final String SELECT_WHERE_QUERY_TEMPLATE = "SELECT * FROM {0} WHERE {1}";
    public static final String SELECT_FIRST_QUERY_TEMPLATE = "SELECT * FROM {0} LIMIT 1";
    public static final String SELECT_FIRST_WHERE_QUERY_TEMPLATE = "SELECT * FROM {0} WHERE {1} LIMIT 1";
    public static final String WHERE_ID_TEMPLATE = "id = {0}";
    public static final String INSERT_QUERY_TEMPLATE = "INSERT INTO {0}({1}) VALUES({2})";
    public static final String UPDATE_QUERY_TEMPLATE = "UPDATE {0} SET {1} WHERE {2}={3}";
    public static final String SET_QUERY_TEMPLATE = "{0}={1}";
    public static final String PRIMARY_KEY_DEFINITION_TEMPLATE = "{0} {1} PRIMARY KEY AUTO_INCREMENT";
    public static final String STATEMENT_BODY_TEMPLATE = "{0}, {1}";
    public static final String CREATE_TABLE_QUERY_TEMPLATE = "CREATE TABLE {0}({1})";
    public static final String COLUMN_DEFINITION_TEMPLATE = "{0} {1}";
    public static final String ADD_COLUMN_QUERY_TEMPLATE = "ADD COLUMN {0} {1}";
    public static final String ALTER_TABLE_QUERY_TEMPLATE = "ALTER TABLE {0} {1}";
    public static final String SELECT_TABLE_COLUMN_NAMES = "SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = '{0}'";
    public static final String COMMA_DELIMITER = ", ";
    public static final String DELITE_QUERY_TEMPLATE = "DELETE FROM {0} WHERE {1}";
    public static final String SELECT_TABLE_NAME_QUERY_TEMPLATE = "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = '%s'";

}
