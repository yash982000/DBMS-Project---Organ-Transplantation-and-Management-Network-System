/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbms;

import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Admin
 */import java.sql.*;
public class DBMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       Connection conn = null;
        try{
            String driverName = "oracle.jdbc.OracleDriver";
            Class.forName(driverName);
            String serverName = "LAPTOP-CAL2CB75";
            String serverPort = "1522";
            String sid = "XE";
            String url = "jdbc:oracle:thin:@" + serverName +":" + serverPort + ":" + sid;
            String username = "VARSHIL";
            String password = "admin";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Successful login");
        }catch(Exception e){
           System.out.println("Exception caught --> " + e); 
        }
        
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select * from death_records");
            while(rs.next()){
                int no = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println("no: "+no+" name: "+name);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}


