<%@ page contentType="text/html; charset=EUC-KR" %>	
<jsp:useBean	id="SQL_Manager" class="ch11.QueryManager"	scope="session" />	
<jsp:useBean	id="DB_con" class="ch11.JDBCcon" scope="application" />	
<jsp:useBean	id="insert" class="ch11.writeBean" scope="page"	 />	
<jsp:setProperty name="insert" property="*"/>	

<% SQL_Manager.setConnection(DB_con.getConnection()); %>	
<% SQL_Manager.update(insert.getInsert());%>	
 
<html>
<head><title>글쓰기 확인</title></head>
<script language="javascript">
function go_list(){
	alert("글이 성공적으로 등록되었습니다.")
	location.href="list.jsp?pg_count=0";
	}
</script>
<body onload="go_list();">
</body>
</html>