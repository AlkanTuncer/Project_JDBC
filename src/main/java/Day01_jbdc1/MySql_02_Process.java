package Day01_jbdc1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class MySql_02_Process {

       /*
            BeforClass'ta connection olusturun
            @Test -> actorlerin isimleri ve rol aldıkları film sayılarını consola yazdırın
            AfterClass'ta connectionları kapatın
        */

        Connection connection;
        Statement statement;
        ResultSet resultSet;

        @BeforeClass
        public void setup() throws SQLException {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://142.93.110.12:3306/sakila",
                    "gsuser",
                    "Gsuser!123456");

            statement = connection.createStatement();
        }

        @Test
        public void testMySQL() throws SQLException {
            resultSet = statement.executeQuery("SELECT actor.first_name, actor.last_name, COUNT(film.title) " +
                                                    "FROM actor " +
                                                    "INNER JOIN film_actor ON actor.actor_id = film_actor.actor_id " +
                                                    "INNER JOIN film ON film.film_id = film_actor.film_id " +
                                                    "GROUP BY first_name, last_name");

            while (resultSet.next()){
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int count = resultSet.getInt(3);

                System.out.printf("%s %s %d \n",firstName,lastName,count);
            }
        }

        @AfterClass
        public void tearDown() throws SQLException {
            statement.close();
            connection.close();
        }

}
