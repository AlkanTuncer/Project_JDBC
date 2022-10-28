package day03_Review;

import java.sql.*;

public class Sqlite_02_CreateTableAndData {

    public static void main(String[] args) throws SQLException {

        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/data.sqlite");

        statement = connection.createStatement();
        statement.setQueryTimeout(30);

        String sqlDrop = "DROP TABLE IF EXISTS kartal";

        statement.executeUpdate(sqlDrop);

        String sql = "CREATE TABLE kartal(adi text, soyadi text, yas int, sehir text)";

        int returnStatement =statement.executeUpdate(sql);
        System.out.println(returnStatement);

        sql = "INSERT INTO kartal VALUES('Alkan','Tuncer',29 ,'Istanbul')";

        System.out.println("statement.executeUpdate(sql) = " + statement.executeUpdate(sql));

        String sql3 = "SELECT * FROM kartal";
        resultSet = statement.executeQuery(sql3);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+", "+resultSet.getString(2)+", "+resultSet.getString(3)+", "+resultSet.getString(4));
        }

        statement.close();
        connection.close();


    }

}
