package utilities;

import java.sql.*;

public class ConnectionManager {

    Connection connection;
    Statement statement;
    String connectionStr;
    String username;
    String password;

    public ConnectionManager(String connectionStr, String username, String password) {
        try {
            this.connectionStr = connectionStr;
            this.username = username;
            this.password = password;

            connection = DriverManager.getConnection(connectionStr, username, password);
            statement = connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ConnectionManager(String connectionStr) {
        try {
            this.connectionStr = connectionStr;

            connection = DriverManager.getConnection(connectionStr);
            statement = connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet(String stringSql){
        try {
            return statement.executeQuery(stringSql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void tearDown(){
       try {
           connection.close();
           statement.close();
       }catch (SQLException e){
           e.printStackTrace();
       }
    }

}
