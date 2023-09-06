import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://localhost/OV-Chipkaart";
        String username = "postgres";
        String password = "rauf";
        Connection conn = DriverManager.getConnection(url,username,password);

        Statement statement = conn.createStatement();
        String sqlQuery = "SELECT * FROM reiziger";


        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            System.out.println("*result*");
            System.out.println(resultSet.getString(1));
            System.out.println("-------------");
            System.out.println(resultSet.getString(2));
            System.out.println("-------------");
            System.out.println(resultSet.getString(3));
            System.out.println("-------------");
            System.out.println(resultSet.getString(4));
            System.out.println("-------------");
            System.out.println(resultSet.getString(5));

        }
        resultSet.close();
        statement.close();

    }
}