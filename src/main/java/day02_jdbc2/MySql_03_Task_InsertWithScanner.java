package day02_jdbc2;

import java.sql.*;
import java.util.Scanner;

public class MySql_03_Task_InsertWithScanner {

   static Connection connection;
   static Statement statement;
   static ResultSet resultSet;
   static ResultSetMetaData rsmd;

    public static void main(String[] args) throws SQLException {

        connect();

        Scanner input = new Scanner(System.in);
        System.out.print("Ad : ");
        String isim = input.nextLine();
        System.out.print("Soyadı : ");
        String soyadı = input.nextLine();
        System.out.print("Yas : ");
        int yas = input.nextInt();
        input.nextLine();
        System.out.print("Sehir : ");
        String sehir = input.nextLine();

        String sql = "INSERT INTO kartal VALUES('"+isim+"', '"+soyadı+"','"+yas+"','"+sehir+"');";
        int effectedRows = statement.executeUpdate(sql);
        if (effectedRows<1) throw new RuntimeException("Kayıt eklenemedi");

        sql = "SELECT * FROM kartal WHERE adi LIKE '%" + isim + "%'";

        resultSet = statement.executeQuery(sql);
        rsmd = resultSet.getMetaData();
        int cols = rsmd.getColumnCount();
        while (resultSet.next()){
            for (int i = 1; i <= cols; i++) {
                System.out.printf("%-20s", resultSet.getString(i)+ "\t");
            }
            System.out.println();
        }

        disconnect();
    }

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/tempdb",
                "gsuser",
                "Gsuser!123456");

        statement = connection.createStatement();
    }

    public static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }
}
