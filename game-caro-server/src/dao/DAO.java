package dao;

import java.sql.Connection;
import java.sql.*;

public class DAO {
    protected Connection con;

    public DAO() {
        String jdbcURL = "jdbc:mysql://localhost:3306/caronhom2?useSSL=false";
        String jdbcUsername = "root";
        String jdbcPassword = ""; //please change information to connect to DB
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection to database failed");
        }
    }
}
