package day02_jdbc2;

import java.sql.*;

public class MySql_04_PreparedStatement {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static ResultSetMetaData rsmd;
    static PreparedStatement preparedStatement;

    public static void main(String[] args) throws SQLException {
        connect();

        preparedStatement.setString(1,"firstName");
        preparedStatement.setString(2,"lastName");
        preparedStatement.setInt(3,53);
        preparedStatement.setString(4,"city");

        preparedStatement.executeUpdate();

        disconnect();
    }

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/tempdb",
                "gsuser",
                "Gsuser!123456");

        preparedStatement = connection.prepareStatement("INSERT INTO kartal VALUES(?,?,?,?);");
    }

    public static void disconnect() throws SQLException {
        preparedStatement.close();
        connection.close();
    }

}
