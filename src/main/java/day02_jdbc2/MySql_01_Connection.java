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

        resultSet = statement.executeQuery("SELECT * FROM kartal;");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s", resultSetMetaData.getColumnName(i));
        }
        System.out.println();


        while (resultSet.next()){
            String adi = resultSet.getString("adi");
            String soyadi = resultSet.getString("soyadi");
            int yas = resultSet.getInt(3);
            String sehir = resultSet.getString(4);

            System.out.printf("%-20s %-20s %-15d %-20s \n",adi,soyadi,yas,sehir);
        }

        statement.close();
        connection.close();

    }

}
