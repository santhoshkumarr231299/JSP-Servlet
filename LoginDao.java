package demologininner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/userdb";
	private String dbUname = "root";
	private String dbPassword = "";
	 public void loadDriver(String dbDriver) throws InstantiationException, IllegalAccessException
	 {
		 try
		 {
		 Class.forName(dbDriver);
		 }
		 catch(ClassNotFoundException e)
		 {
			 
		 }
	 }
	 
	 public Connection getConnection() throws SQLException
	 {
		 Connection con = null;
		 con = DriverManager.getConnection(dbUrl,dbUname,dbPassword);
		 return con;
	 }
	 
	 public boolean validateLogin(String uname, String pword) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
	 {
		 loadDriver("com.mysql.cj.jdbc.Driver");
		 Connection con = getConnection();

		 String sqlQuery = "select * from user where username = ? and password = ?";
		 
		 PreparedStatement ps;
		 ps = con.prepareStatement(sqlQuery);
		 
		 ps.setString(1, uname);
		 
		 ps.setString(2, pword);
		 
		 
		 
		 ResultSet rs = ps.executeQuery();
		 
		 return rs.next();
	 }
	 
public boolean validateNewUser(String uname, String email) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
{
	 loadDriver("com.mysql.cj.jdbc.Driver");
	 Connection con = getConnection();

	 String sqlQuery = "select * from user where username = ? or email = ?";
	 
	 PreparedStatement ps;
	 ps = con.prepareStatement(sqlQuery);
	 
	 ps.setString(1, uname);
	 
	 ps.setString(2, email);
	 
	 ResultSet rs = ps.executeQuery();
	 
	 return rs.next();
}
public boolean validateAdmin(String uname) throws InstantiationException, IllegalAccessException, SQLException
{
	loadDriver("com.mysql.cj.jdbc.Driver");
	Connection con = getConnection();
	PreparedStatement ps;
	
	String sqlQuery = "select * from user where username = '"+uname+"' and admin = '1'";
	
	ps = con.prepareStatement(sqlQuery);
	
	ResultSet rs = ps.executeQuery();
	
	return rs.next();
}
public boolean validateForgotPass(String uname, String email) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
{
	 loadDriver("com.mysql.cj.jdbc.Driver");
	 Connection con = getConnection();

	 String sqlQuery = "select * from user where username = ? and email = ?";
	 
	 PreparedStatement ps;
	 ps = con.prepareStatement(sqlQuery);
	 
	 ps.setString(1, uname);
	 
	 ps.setString(2, email);
	 
	 
	 
	 ResultSet rs = ps.executeQuery();
	 
	 return rs.next();
}
public boolean validateAllJobs(String uname) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
{
	 loadDriver("com.mysql.cj.jdbc.Driver");
	 Connection con = getConnection();

	 String sqlQuery = "select * from userdb where username = '"+uname+"'";
	 
	 PreparedStatement ps;
	 ps = con.prepareStatement(sqlQuery);
	 
	 ResultSet rs = ps.executeQuery();
	 int count = 0;
	 while(rs.next())
	 {
		 count++;
	 }
	 if(count == 3)
		 return true;
	 else
		 return false;
}
public ResultSet allUsers() throws InstantiationException, IllegalAccessException, SQLException
{
	loadDriver("com.mysql.cj.jdbc.Driver");
	 Connection con = getConnection();

	 String sqlQuery = "select * from userdb";
	 
	 PreparedStatement ps;
	 ps = con.prepareStatement(sqlQuery);
	 
	 ResultSet rs = ps.executeQuery();
	 return rs;
}
public ResultSet getRoles() throws InstantiationException, IllegalAccessException, SQLException
{
	loadDriver("com.mysql.cj.jdbc.Driver");
	 Connection con = getConnection();

	 String sqlQuery = "select * from roles";
	 
	 PreparedStatement ps;
	 ps = con.prepareStatement(sqlQuery);
	 
	 ResultSet rs = ps.executeQuery();
	 return rs;
}

}