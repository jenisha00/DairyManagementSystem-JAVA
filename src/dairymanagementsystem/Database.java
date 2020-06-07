package dairymanagementsystem;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Jenisha Munikar
 */
public class Database {
    Connection conn=null;
     public static Connection connector1(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection c=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Owner\\Documents\\NetBeansProjects\\DairyManagementSystem\\mydb.sqlite");
            
            return c;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
            
        }
}
