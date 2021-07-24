package cpw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
   static String serverName = "localhost";
   static String mydatabase = "mydatabase";
   static final String DB_URL = "jdbc:mysql://" + serverName + "/" + mydatabase; 
   static final String USER = "root";
   static final String PASS = "Onmpon-547";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		      
         String sql = "CREATE DATABASE STUDENTS";
         stmt.executeUpdate(sql);
         System.out.println("Database created successfully...");   	  
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}