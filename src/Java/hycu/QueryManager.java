package Java.hycu;
import java.sql.*;

public class QueryManager
{
	private Connection con = null;
	public void setConnection(Connection c)
	{
		this.con = c;
	}

	public String update(String Query)
	{
		if(con == null)
		{
			return "You must set Connection in advance";
		}
		try
		{
			System.out.println("SQL : " + Query);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(Query);
			stmt.close();
			con.close();
			return "<li>Update Success!";
		}
		catch (SQLException E)
		{
			return "<li>SQLException: " + E.getMessage();
		}
	}
}
