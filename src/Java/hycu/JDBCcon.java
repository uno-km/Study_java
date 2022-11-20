package Java.hycu;
import java.sql.*;

public class JDBCcon
{
	private String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/jsp_db?serverTimezone=UTC&useSSL=false";
	private String userName = "unokim";
	private String userPW = "zkakfk12!@";

	public Connection getConnection()
	{
		try
		{
			Class.forName(jdbc_driver);
			Connection con = DriverManager.getConnection(dbUrl, userName, userPW);
			System.out.println("connected : " + con);
			return con;
		}
		catch (ClassNotFoundException CNFE)
		{
			CNFE.printStackTrace();
			return null;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}