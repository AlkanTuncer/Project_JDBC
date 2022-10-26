package Day01_jbdc1.MySqlProcess;

import org.testng.annotations.Test;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MySqlProcess extends MYSqlProcessBase{

    @Test
    public void sql01() throws SQLException {
        resultSet = statement.executeQuery("SELECT actor.first_name, actor.last_name, COUNT(film.title) AS count " +
                "FROM actor " +
                "INNER JOIN film_actor ON actor.actor_id = film_actor.actor_id " +
                "INNER JOIN film ON film.film_id = film_actor.film_id " +
                "GROUP BY first_name, last_name " +
                "ORDER BY count DESC;");

        while (resultSet.next()){
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int count = resultSet.getInt(3);

            System.out.printf("%s %s %d \n",firstName,lastName,count);
        }
    }


    @Test(description = "Cinsiyetlerin yaş ortalamaları")
    public void sql02() throws SQLException {
        String sql = "SELECT gender, AVG(age) FROM personel GROUP BY gender;";

        resultSet = statement.executeQuery(sql);
        while (resultSet.next())
        System.out.printf("%-15s %5.2f \n",resultSet.getString(1),resultSet.getDouble(2));
    }


    @Test(description = "Field headers")
    public void testSql03() throws SQLException {
        String sql = "SELECT gender, AVG(age) AS count FROM personel GROUP BY gender;";

        resultSet = statement.executeQuery(sql);

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();   // Bu kodla beraber tablonun baslıklarını getirdik.
        System.out.printf("%-15s %s \n", resultSetMetaData.getColumnName(1),resultSetMetaData.getColumnName(2));

        while (resultSet.next())
            System.out.printf("%-15s %5.2f \n",resultSet.getString(1),resultSet.getDouble(2));
    }


    @Test(description = "Actor tablosunu SELECT * ile cagırın ve konsola field baslıkları ile datalarını for i ile yazdırın")
    public void testSql04() throws SQLException {
        String sql = "SELECT * FROM actor;";

        resultSet = statement.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnNum = rsmd.getColumnCount();

        // actor_id    first_name   last_name  last_update
        String format = "";
        String[] headers = new String[columnNum];

        for (int i = 1; i <=columnNum ; i++) {
            int size = rsmd.getColumnDisplaySize(i) > 30 ? 30 : rsmd.getColumnDisplaySize(i);
            size = Math.max(size,rsmd.getColumnName(i).length());
            format += "%-" + size + "s ";
            headers[i-1] = rsmd.getColumnName(i);
        }
        format += "\n";

        System.out.printf(format,headers);

        String[] values = new String[columnNum];
        String str = "";
        while (resultSet.next()){
            for (int i = 1; i <= columnNum ; i++) {
                int size = rsmd.getColumnDisplaySize(i) > 30 ? 30 : rsmd.getColumnDisplaySize(i);
                values[i-1] = resultSet.getString(i);
            }
            System.out.printf(format,values);
        }

    }

}
