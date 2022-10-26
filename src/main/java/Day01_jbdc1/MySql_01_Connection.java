package Day01_jbdc1;

import java.sql.*;

public class MySql_01_Connection {

    public static void main(String[] args) throws SQLException {

        // JDBC connection için kullanılacak
        Connection connection;

        Statement statement;

        ResultSet resultSet;

        //  url = "jdbc:mysql://[ip | localhost | 127.0.0.1]:[port]/[database]"

        connection = DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/sakila",
                "gsuser",
                "Gsuser!123456");

        statement = connection.createStatement();

        resultSet = statement.executeQuery("SELECT * FROM actor LIMIT 7;");

        while(resultSet.next()){
            int actor_id = resultSet.getInt(1);
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString(3);

            System.out.printf("%5d %-15s %-15s \n",actor_id,first_name,last_name);
        }

        statement.close();
        connection.close();

    }

    /*
        SELECT first_name, last_name, title
        FROM actor
        INNER JOIN film_actor ON actor.actor_id = film_actor.actor_id
        INNER JOIN film ON film.film_id = film_actor.film_id
        WHERE first_name LIKE 'A%';
     */

}
