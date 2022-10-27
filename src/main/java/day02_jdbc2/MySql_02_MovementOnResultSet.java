package day02_jdbc2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class MySql_02_MovementOnResultSet {

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

    @Test
    public void getResultSet() throws SQLException {
        String sql = "SELECT * FROM meslekler1;";
        resultSet = statement.executeQuery(sql);
    }


    @Test (description = "resultSet.absolute" , dependsOnMethods = "getResultSet")
    public void test01() throws SQLException {
        resultSet.next();
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));

        resultSet.absolute(10);
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));
    }

    @Test (description = "resultSet.previous" , dependsOnMethods = "getResultSet")
    public void test02() throws SQLException {
        resultSet.absolute(10);
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));

        resultSet.previous();
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));
    }

    @Test (description = "resultSet.relative" , dependsOnMethods = "getResultSet")
    public void test03() throws SQLException {
        resultSet.absolute(10);
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));

        resultSet.relative(2);
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));

        resultSet.relative(-4);
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));
    }

    @Test (description = "resultSet.first and last" , dependsOnMethods = "getResultSet")
    public void test04() throws SQLException {
        resultSet.first();
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));

        resultSet.last();
        System.out.println(resultSet.getString(1) + " , " + resultSet.getString(2) + " , " + resultSet.getString(3));
    }

}
