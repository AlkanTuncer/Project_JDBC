package day02_jdbc2;

import java.sql.*;

public class MySql_01_Connection {

    // connect to tempdb
    // kartal'dakileri konsola yazdır

    public static void main(String[] args) throws SQLException {

        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connection = DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/tempdb",
                "gsuser",
                "Gsuser!123456"
        );

        statement = connection.createStatement();

        resultSet = statement.executeQuery("SELECT * FROM meslekler1 LIMIT 10;");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        for (int i = 1; i <= columnCount-2; i++) {  // Tabloda 6 kolon var ben 4 ünü alıyorum.
            int size = resultSetMetaData.getColumnDisplaySize(i);
            System.out.printf("%-"+size+"s", resultSetMetaData.getColumnName(i));
        }
        System.out.println();


        while (resultSet.next()){
            for (int i = 1; i <=columnCount-2 ; i++) {
                int size = resultSetMetaData.getColumnDisplaySize(i);
                System.out.printf("%-"+size+"s",resultSet.getString(i));
            }
            System.out.println();
        }

        statement.close();
        connection.close();

    }

}
