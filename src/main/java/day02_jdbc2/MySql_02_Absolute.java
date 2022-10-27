package day02_jdbc2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class MySql_02_Absolute {

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    ResultSetMetaData rsmd;

    @BeforeTest
    public void beforeTest() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/tempdb",
                "gsuser",
                "Gsuser!123456"
        );
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE , ResultSet.CONCUR_READ_ONLY);
    }

    @AfterTest
    public void afterTest() throws SQLException {
        statement.close();
        connection.close();
    }


    @Test (description = "resultSet.absolute")
    public void test01() throws SQLException {
        String sql = "SELECT * FROM meslekler1;";

        resultSet = statement.executeQuery(sql);
        resultSet.next();
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));

        resultSet.absolute(10);
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));
    }

}
