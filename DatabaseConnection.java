import java.sql.*;
import javax.swing.*;


public class DatabaseConnection
{
	Connection conn=null;
    
    
    public static Connection DbConnector() 
	{
        
            try 
			{
                
                Class.forName("com.mysql.jdbc.Driver");
				Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/nursery","root","");
				
				System.out.println("Successful");
                                return conn;
            }
			catch(Exception ex){
                System.out.println("Failed to get DBConn:: "+ex.getMessage());
                return null;
            }

            
      
        }
}
    