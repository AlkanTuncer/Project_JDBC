package day02_jdbc2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class MySql_03_Insert {

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
        statement = connection.createStatement();
    }

    @AfterTest
    public void afterTest() throws SQLException {
        statement.close();
        connection.close();
    }


    @Test
    public void test01() throws SQLException {
        String sql = "INSERT INTO kartal VALUES('Cristiano','Ronaldo','07','Manchester');";
        int insertNumber = statement.executeUpdate(sql);
        System.out.println(insertNumber + " kayıt eklendi");
    }

    @Test
    public void test02() throws SQLException {
        String sql = "SELECT * FROM kartal;";
        resultSet = statement.executeQuery(sql);
        rsmd = resultSet.getMetaData();
        int cols = rsmd.getColumnCount();
        while (resultSet.next()){
            for (int i = 1; i <= cols; i++) {
                System.out.printf("%-20s", resultSet.getString(i));
            }
            System.out.println();
        }
    }

}
