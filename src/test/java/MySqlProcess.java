import Day01_jbdc1.MySqlProcess.MYSqlProcessBase;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class MySqlProcess extends MYSqlProcessBase {

    @Test
    public void sql01() throws SQLException {
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

}
