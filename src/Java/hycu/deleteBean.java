package Java.hycu;

public class deleteBean extends Element
{
	private String deleteQuery;
	public void setDeleteQuery(String deleteQuery)
	{
		this.deleteQuery = deleteQuery;
	}
	public String getDeleteQuery()
	{
		return deleteQuery;
	}

	public String getDelete()
	{
		deleteQuery = "delete from visit_board where no=" + getNo();
		return deleteQuery;
	}

	public boolean comparePass()
	{

		if(getAdminID().equals("id") && getAdminPass().equals("pass"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}