import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "00000000");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo",props);

        PreparedStatement stmt =
                connection.prepareStatement
                        ("SELECT COUNT(ug.id) AS game_played,user_name,first_name,last_name\n" +
                                "FROM users AS u\n" +
                                "JOIN users_games AS ug\n" +
                                "ON u.id = user_id\n" +
                                "WHERE user_name = ?\n" +
                                "GROUP BY u.id;");

        System.out.println("Enter user_name:");
        String userName = reader.readLine();

        stmt.setString(1,userName);
        ResultSet rs = stmt.executeQuery();

        if(!rs.isBeforeFirst()){
            System.out.println("No such user exists");
        }

        while (rs.next()){
            System.out.printf("User: %s\n" +
                    "%s %s has played %d games\n",
                    rs.getString("user_name"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("game_played"));
        }


    }
}
