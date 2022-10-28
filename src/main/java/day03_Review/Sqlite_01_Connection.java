package day03_Review;

import java.sql.*;

public class Sqlite_01_Connection {
    public static void main(String[] args) throws SQLException {

        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connection = DriverManager.getConnection(
                "jdbc:sqlite:src/main/resources/data.sqlite");

        statement = connection.createStatement();
        statement.setQueryTimeout(30);

        resultSet = statement.executeQuery("SELECT * FROM personel LIMIT 10");

        while (resultSet.next()) {
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                System.out.printf("%-30s", resultSet.getString(i));
            }
            System.out.println();
        }

        statement.close();
        connection.close();

    }
}
