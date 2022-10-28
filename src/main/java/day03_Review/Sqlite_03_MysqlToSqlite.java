package day03_Review;

import java.sql.*;

public class Sqlite_03_MysqlToSqlite {

    // gsuser'daki kartal'ı local dosyadaki kartal'a yazdır

    public static void main(String[] args) throws SQLException {

        Connection connectionMysql;
        Connection connectionSqlite;
        Statement statementMysql;
        Statement statementSqlite;
        ResultSet resultSetMysql;
        ResultSet resultSetSqlite;

        connectionMysql = DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/tempdb",
                "gsuser",
                "Gsuser!123456");
        statementMysql = connectionMysql.createStatement();
        String sql = "SELECT * FROM kartal";
        resultSetMysql = statementMysql.executeQuery(sql);

        connectionSqlite = DriverManager.getConnection("jdbc:sqlite:src/main/resources/data.sqlite");
        statementSqlite = connectionSqlite.createStatement();

        while (resultSetMysql.next()){
            String sqlInsert = "INSERT INTO kartal VALUES('"+resultSetMysql.getString(1)+"','"+resultSetMysql.getString(2)+"'," +
                    ""+resultSetMysql.getString(3)+" ,'"+resultSetMysql.getString(4)+"')";

            statementSqlite.executeUpdate(sqlInsert);
        }

        String sqlite = "SELECT * FROM kartal";

        resultSetSqlite = statementSqlite.executeQuery(sqlite);

        while (resultSetSqlite.next()){
            System.out.printf("%s %s %s %s\t\n",resultSetSqlite.getString(1),resultSetSqlite.getString(2),resultSetSqlite.getString(3),resultSetSqlite.getString(4));
        }

        statementMysql.close();
        statementSqlite.close();
        connectionMysql.close();
        connectionSqlite.close();

    }
}
