/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



/**
 *
 * @author SCC
 */
public class dbConnector {
    
    private Connection connect;
    
    public dbConnector(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrentalsystem", "root", "");
        }catch(SQLException ex){
            System.out.println("Can't connect to database: "+ex.getMessage());
        }
    }
    
    //Function to retrieve data
    public ResultSet getData(String sql) throws SQLException{
        Statement stmt = connect.createStatement();
        ResultSet rst = stmt.executeQuery(sql);
        return rst;
    }
        
    //Function to save data
    public boolean insertData(String sql){
        try{
            PreparedStatement pst = connect.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("Inserted Successfully!");
            pst.close();
            return true;
        }catch(SQLException ex){
            System.out.println("Connection Error: "+ex);
            return false;
        }
    }
    
    //Function to update data
    public void updateData(String sql){
        try{
            PreparedStatement pst = connect.prepareStatement(sql);
            int rowsUpdated = pst.executeUpdate();
                if(rowsUpdated > 0){
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                }else{
                    System.out.println("Data Update Failed!");
                }
                pst.close();
        }catch(SQLException ex){
            System.out.println("Connection Error: "+ex.getMessage());
        }
    }
    
    public void deleteUser(int userId) {
        try {
            // Create a statement
            Statement stmt = connect.createStatement();

            // Execute the deletion query
            String query = "DELETE FROM tbl_user WHERE u_id = " + userId;
            int rowsAffected = stmt.executeUpdate(query);

            // Close the statement
            stmt.close();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("No user found with ID: " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
    
    public void deleteCar(int carId) {
        try {
            // Create a statement
            Statement stmt = connect.createStatement();

            // Execute the deletion query
            String query = "DELETE FROM tbl_car WHERE c_id = " + carId;
            int rowsAffected = stmt.executeUpdate(query);

            // Close the statement
            stmt.close();

            if (rowsAffected > 0) {
                System.out.println("Car deleted successfully.");
            } else {
                System.out.println("No Car found with ID: " + carId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting Car: " + e.getMessage());
        }
    }
    
    public void deleteTransact(int transId) {
        try {
            // Create a statement
            Statement stmt = connect.createStatement();

            // Execute the deletion query
            String query = "DELETE FROM tbl_carrent WHERE carRent_ID = " + transId;
            int rowsAffected = stmt.executeUpdate(query);

            // Close the statement
            stmt.close();

            if (rowsAffected > 0) {
                System.out.println("Transaction deleted successfully.");
            } else {
                System.out.println("No Transaction found with ID: " + transId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting Transaction: " + e.getMessage());
        }
    }

    public ResultSet refreshData() {
        ResultSet resultSet = null;
        try {
            // Create a statement
            Statement stmt = connect.createStatement();

            // Execute a query to select all user data
            String query = "SELECT * FROM tbl_user";
            resultSet = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Error refreshing data: " + ex.getMessage());
        }
        return resultSet;
    }

    public ResultSet getData(String query, String parameter) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(query);
        preparedStatement.setString(1, parameter);
        return preparedStatement.executeQuery();
    }
}
