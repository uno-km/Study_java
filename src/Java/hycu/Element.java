package Java.hycu;

public class Element{
	private String no;
	private String query;
	private String visitor;
	private String email;
	private String homepage;
	private String contents;
	private String adminID;
	private String adminPass;

	
	public void setNo(String no) { this.no=no; }
	public void setQuery(String query) { this.query=query; }
	public void setVisitor(String visitor) { this.visitor=visitor;	}
	public void setEmail(String email) { this.email=email; }
	public void setHomepage(String homepage) { this.homepage=homepage; }
	public void setContents(String contents) { this.contents=contents; }
	public void setAdminID(String adminID) { this.adminID=adminID; }
	public void setAdminPass(String adminPass) { this.adminPass=adminPass;	}
	
	public String getNo() { return no; }
	public String getQuery() { return query; }	
	public String getVisitor() { return visitor; }
	public String getEmail() { return email; }
	public String getHomepage() { return homepage;	}
	public String getContents() { return contents;	}
	public String getAdminID() { return adminID; }
	public String getAdminPass() { return adminPass; }
}