package day03_Review;

import utilities.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsageConnectionManager {
    public static void main(String[] args) {

        ConnectionManager connectionManager = new ConnectionManager("jdbc:sqlite:src/main/resources/data.sqlite");

        ResultSet resultSet = connectionManager.getResultSet("SELECT * FROM kartal");

        try{
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" - "+resultSet.getString(2)+" - "+
                        resultSet.getString(3)+" - "+resultSet.getString(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        connectionManager.tearDown();

    }
}
