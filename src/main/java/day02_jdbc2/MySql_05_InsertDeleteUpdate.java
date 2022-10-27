package day02_jdbc2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class MySql_05_InsertDeleteUpdate {

    /*
        1. Before,After Test
        2. DataProvider ile 10 kayıt ekleyin
        3. Update ile 2 kayıt güncelleyin
        4. Delete ile 5 kayıt silin
     */

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    ResultSetMetaData rsmd;

    @BeforeTest
    public void beforeTest() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://142.93.110.12:3306/tempdb",
                "gsuser",
                "Gsuser!123456"
        );
        statement = connection.createStatement();
    }

    @AfterTest
    public void afterTest() throws SQLException {
        statement.close();
        connection.close();
    }

    @Test(dataProvider = "getData")
    public void insertData(Object[] array) throws SQLException {

        String sql = "INSERT INTO kartal VALUES('"+array[0]+"', '"+array[1]+"', "+array[2]+" , '"+array[3]+"');";

        if(statement.executeUpdate(sql)<1){
            throw new RuntimeException("Kayıt eklenemedi.\n"+sql);
        }
    }

    @Test(dataProvider = "getData")
    public void updateData(Object[] array) throws SQLException {

        String sql = "UPDATE INTO kartal VALUES('"+array[0]+"', '"+array[1]+"', "+array[2]+" , '"+array[3]+"');";

        if(statement.executeUpdate(sql)<1){
            throw new RuntimeException("Kayıt eklenemedi.\n"+sql);
        }
    }

    @Test
    public void writeResult() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM kartal;");
        int columns = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()){
            for (int i = 1; i <= columns ; i++) {
                System.out.printf("%-20s", resultSet.getString(i));
            }
            System.out.println();
        }
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"A1","A1",3,"X1"},
                {"A2","A2",7,"X2"},
                {"A3","A3",17,"X3"},
                {"A4","A4",23,"X4"},
                {"A5","A5",53,"X5"},
                {"A6","A6",92,"X6"},
                {"A7","A7",1905,"X7"},
                {"A8","A8",1992,"X8"},
                {"A9","A9",34,"X9"},
                {"A10","A10",1012,"X10"}
        };
    }
}
