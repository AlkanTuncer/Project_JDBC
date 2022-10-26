package Day01_jbdc1.MySqlProcess;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;

public class MYSqlProcessBase {

    Connection connection;
   protected Statement statement;
   protected ResultSet resultSet;

    @BeforeTest
    public void beforeTest() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/sakila",
                "gsuser",
                "Gsuser!123456");

        statement = connection.createStatement();
    }

    @AfterTest
    public void afterTest() throws SQLException {
        statement.close();
        connection.close();
    }

}
