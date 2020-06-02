/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbms;

/**
 *
 * @author Admin
 */import java.sql.*;
import java.util.stream.Stream;
//im
public class oracleDataBAseConnection {
    interface WhyUNoCheckedExceptionRunnable {
    void run() throws Exception;
}
 
static void logServerOutput(
    Connection connection, 
    WhyUNoCheckedExceptionRunnable runnable
) throws Exception {
    try (Statement s = connection.createStatement()) {
       try {
           s.executeUpdate("begin dbms_output.enable(); end;");
           runnable.run();
 
           try (CallableStatement call = connection.prepareCall(
               "declare "
             + "  num integer := 1000;"
             + "begin "
             + "  dbms_output.get_lines(?, num);"
             + "end;"
           )) {
               call.registerOutParameter(1, Types.ARRAY,
                   "DBMSOUTPUT_LINESARRAY");
               call.execute();
 
               Array array = null;
               try {
                   array = call.getArray(1);
//                   System.out.println(array);
                   Stream.of((Object[]) array.getArray())
                         .forEach(System.out::println);
                   System.out.println(Stream.of((Object[]) array.getArray()).count());//to get count of number of lines
//                         .forEach(System.out::println);
//                   System.out.println(array.getArray());
               }
               finally {
                   if (array != null)
                       System.out.println("");;
               }
           }
       }
       finally {
           s.executeUpdate("begin dbms_output.disable(); end;");
       }
   }
}
    public static void main(String[] args) throws SQLException, Exception {
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
                
//                Connection c = DriverManager.getConnection(url, properties);
//     Statement s = conn.createStatement(); 
 try (
     Statement s = conn.createStatement()) {
 
    logServerOutput(conn, () -> 
        s.executeUpdate("begin ex ; end;"));
}
    }
}



/*
A TABLE AND PROCEDURE PEHLA CREATE KARJE

CREATE TABLE my_table (i INT);
 
CREATE OR REPLACE PROCEDURE my_procedure (i1 INT, i2 INT) IS
BEGIN
  INSERT INTO my_table 
  SELECT i1 FROM dual UNION ALL
  SELECT i2 FROM dual;
   
  dbms_output.put_line(i1||' '||i2||' SO WE ARE GETTING THE DBMS OUTPUT');
END my_procedure;
*/