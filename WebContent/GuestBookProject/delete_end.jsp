<%@ page contentType="text/html; charset=EUC-KR" %> 
<%@ page language="java" import="java.util.Vector" %>
<jsp:useBean  id="SQL_Manager" class="ch11.QueryManager" scope="session" />
<jsp:useBean  id="DB_con" class="ch11.JDBCcon" scope="application" />
<jsp:useBean id="delete" class="ch11.deleteBean" scope="page" />
<jsp:setProperty name="delete" property="adminID"  param="adminID"/>
<jsp:setProperty name="delete" property="adminPass"  param="adminPass"/>
<jsp:setProperty name="delete" property="no" param="delno"/>

<html> 
  <head>
  <title>�ۻ��� Ȯ��</title>
  <script language="javascript">
   function go_list(){
    alert("���� ���������� �����Ͽ����ϴ�");
    location.href="list.jsp";
   }
   function go_back(){
    alert("���̵� �Ǵ� ��й�ȣ�� Ʋ���ϴ�.");
    history.go(-1);
   }
  </script>
  </head>
 <% if(delete.comparePass()){
   SQL_Manager.setConnection(DB_con.getConnection());
   SQL_Manager.update(delete.getDelete());	
 %>
  <body onload="go_list();">
 <%
 }else{
 %>
  <body onload="go_back();">
 <%
 }
 %>
  </body>
  </html>	
