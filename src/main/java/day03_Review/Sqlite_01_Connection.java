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

        String sql = "SELECT make, COUNT(*) FROM cars GROUP BY make";  // araba markalarının modelleri
        String sql2 = "SELECT make, COUNT(*) FROM cars" +              // araba markalarını kac kişi kullanıyor
                " INNER JOIN personel ON personel.car_id = cars.id" +
                " GROUP BY make;";

        resultSet = statement.executeQuery(sql2);

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
