package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class MySQLConnection {
	
	public static String url;
	public static String userName;
	public static String password;
	public Connection con = null;

	public MySQLConnection(String url, String userName, String password) {
		this.url = url;
		this.url = userName;
		this.url = password;
		this.con = connectToDB(url, userName, password);
		
		try {
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS players(Player_id int NOT NULL AUTO_INCREMENT, name varchar(45) NOT NULL, PRIMARY KEY(Player_id))");
            create.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
    public static void createTable() throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS players(Player_id int NOT NULL AUTO_INCREMENT, name varchar(45) NOT NULL, PRIMARY KEY(Player_id)");
            create.executeUpdate();
        }catch(Exception e) {System.out.println(e);}
        finally {
            System.out.println("Function complete.");
            };

    }
    
    public static Connection getConnection() throws Exception{

        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected");
            return conn;
        } catch(Exception e) {System.out.println(e);}

        return null;    
        }



    
    
    
    
	public Connection connectToDB(String url, String userName, String password) {
	    Connection con = null;
	    try {
	      //Class.forName("com.mysql.jdbc.Driver");
	      con = DriverManager.getConnection(url, userName, password);
	      System.out.println("Connected!");
	      return con;

	    } catch (SQLException ex) {
	        throw new Error("Error ", ex);
//	    } finally {
//	      try {
//	        if (con != null) {
//	            con.close();
//	        }
//	      } catch (SQLException ex) {
//	          System.out.println(ex.getMessage());
//	      }
	    }
	}
	
	public void executeQuery(String query) {
		 try
		    {
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        Statement st = con.createStatement();
		        st.execute(query);

		    }
		    catch (Exception ex)
		    {
		    	System.out.println(ex);
		    } 
	}
	

	public String executeQueryAndGetResult(String query) {
		 try
		    {
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        Statement st = con.createStatement();
		        ResultSet rs = st.executeQuery(query);
		        String result = "";
		        while(rs.next()) {
		        	result = rs.getString(1);
		        }
		        return result;

		    }
		    catch (Exception ex)
		    {
		    	System.out.println(ex);
		    	return null;
		    } 
	}


	public  String ResultPlayerInfo(String query) {
		 try
		    {
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        Statement st = con.createStatement();
		        ResultSet rs = st.executeQuery(query);
		        String result = "";
		        while(rs.next()) {
		        	//result = rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7);
		 
		        	result ="Number_of_wins_in_soccer = "+ rs.getString(3)+
					"\nNumber_of_wins_in_tennis = "+rs.getString(4)+
					"\nNumber_of_wins_in_basketball = "+rs.getString(5)+
					"\nTotal_wins = " +rs.getString(6);
		        	
		        	/*
					"\nTotal_wins = " +rs.getString(6)+
					"\nTotal_championship_wins = "+rs.getString(7);
		        	*/
		        	
		        }
				

		        return result;

		    }
		    catch (Exception ex)
		    {
		    	System.out.println(ex);
		    	return null;
		    } 
	}

	
	public  String ResultTypeInfo(String query) {
		 try
		    {
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        Statement st = con.createStatement();
		        ResultSet rs = st.executeQuery(query);
		        String result = "";
		        while(rs.next()) {
		        	//result = rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7);
		 
		        	result ="\nBest game for player = "+ rs.getString(3);
					
		        	
		        	/*
					"\nTotal_wins = " +rs.getString(6)+
					"\nTotal_championship_wins = "+rs.getString(7);
		        	*/
		        	
		        }
				

		        return result;

		    }
		    catch (Exception ex)
		    {
		    	System.out.println(ex);
		    	return null;
		    } 
	}
	
	
	
	public  String ResultWinPlayerInfo(String query) {
		 try
		    {
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        Statement st = con.createStatement();
		        ResultSet rs = st.executeQuery(query);
		        String result = "";
		        while(rs.next()) {
		 
		        	result = "\nTotal_championship_wins = "+rs.getString(3) ;
		        	
		        	
		        }
				

		        return result;

		    }
		    catch (Exception ex)
		    {
		    	System.out.println(ex);
		    	return null;
		    } 
	}
	


	public String ResultWinPlayerInfoState(String query) {
		 try
		    {
		        Class.forName("com.mysql.cj.jdbc.Driver");

		        Statement st = con.createStatement();
		        ResultSet rs = st.executeQuery(query);
		        String result = "";
		        while(rs.next()) {
		        	//result = rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7);
		 
		        	result = "\nState = "+ rs.getString(1) ;
		        	
		        	
		        }
				

		        return result;

		    }
		    catch (Exception ex)
		    {
		    	System.out.println(ex);
		    	return null;
		    } 
	}
	
	  

}
