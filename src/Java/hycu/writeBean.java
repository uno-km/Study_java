package Java.hycu;
public class writeBean extends Element
{
	private String insertQuery;

	public String TO_DB(String str)
	{
		try
		{
			if(str == null)
			{
				return null;
			}
			return new String(str.getBytes("8859_1"), "utf-8");
		}
		catch (Exception e)
		{
			return "error";
		}
	}

	public String getInsert()
	{
		if(getHomepage().equals("http://"))
		{
			insertQuery = "INSERT INTO visit_board(visitor,email,homepage,regist_date,contents)" + " values('" + TO_DB(getVisitor()) + "','"
					+ getEmail() + "','" + " " + "',sysdate(),'" + TO_DB(getContents()) + "')";
			return insertQuery;
		}
		else
		{
			insertQuery = "INSERT INTO visit_board(visitor,email,homepage,regist_date,contents) " + " values('" + TO_DB(getVisitor()) + "','"
					+ getEmail() + "','" + getHomepage() + "',sysdate(),'" + TO_DB(getContents()) + "')";
			return insertQuery;
		}
	}
}