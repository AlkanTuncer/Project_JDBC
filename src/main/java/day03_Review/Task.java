package day03_Review;

import utilities.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Task {
    public static void main(String[] args) {

        // ConnectionManager ile sakila'ya baglanıp filmlerde oynayan aktör sayıları

        ConnectionManager connectionManager = new ConnectionManager(
                "jdbc:mysql://142.93.110.12:3306/sakila",
                "gsuser",
                "Gsuser!123456");

        ResultSet resultSet = connectionManager.getResultSet(
                "SELECT title, COUNT(*) FROM actor " +
                        "LEFT JOIN film_actor ON actor.actor_id = film_actor.actor_id " +
                        "LEFT JOIN film ON film_actor.film_id = film.film_id " +
                        "GROUP BY title");

        try{
            while (resultSet.next()){{
                System.out.printf("%-20s %5d\n",resultSet.getString(1),resultSet.getInt(2));
            }}
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
