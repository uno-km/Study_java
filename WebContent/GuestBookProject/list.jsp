<%@ page contentType="text/html; charset=EUC-KR" %> 
<%@ page language="java" import=" java.util.Vector" %>
<jsp:useBean	id="DB_con" class="ch11.JDBCcon" scope="application" />
<jsp:useBean	id="list" class="ch11.listBean" scope="session"/>
<jsp:setProperty name="list" property="*"/>
<% list.setConnection(DB_con.getConnection());%>	
<% list.list(list.listQuery());	%>
<jsp:setProperty name="list" property="current_pg" param="pg_count"/>
JSP BEANS�� ����� �����Դϴ�.
	
 <html>
  <head>
  <title>���� ����Ʈ</title>
   <STYLE TYPE="text/css">
    <!--
     body { font-family: ����, Verdana; font-size: 10pt}
     td   { font-family: ����, Verdana; font-size: 10pt; text-decoration: none; color: #6600CC} 
     --->
   </STYLE>
  </head>
   <body>
   <%  list.scopeList();  %>
					
<br>
��
<jsp:getProperty name="list" property="itotal"/>���� �Խù��� ��ϵǾ� �ֽ��ϴ�. 
    <p align="center"><img src=image/guestbook.jpg></p>
   <font size="1pt">
    <FORM METHOD="POST" ACTION="write.html">
     <table align="center" width="550" border="0">
      <tr>
       <td align="right" width="183">
      <%if(list.getIremain()==0 && list.getCurrent()<1){%>
        &nbsp;&nbsp;&nbsp;&nbsp;								
      <%}else if(list.getCurrent()!=0){%>	
        <a href="list.jsp?pg_count=<%=list.getCurrent()-1%>">
        <IMG SRC="image/left.jpg" WIDTH="50" HEIGHT="50" BORDER=0 ALT="�������"></a>
      <%}else{%>
        &nbsp;&nbsp;&nbsp;&nbsp;
      <%}%>
       </td>
       <td align="center" width="184">
       <a href="write.html" onMouseOver="status='post a new article';return true;" onMouseOut="status=''"><img src="image/write.jpg" width=50 height=50 border=0></a>
       </td>
       <td align="left" width="183">
      <%if(list.getCurrent()==list.getIvalue()-1&& list.getIremain()==0){%>
        &nbsp;&nbsp;&nbsp;&nbsp;
      <%}else if(list.getCurrent()!=list.getIvalue()){%>
        <a href="list.jsp?pg_count=<%=list.getCurrent()+1%>">
        <IMG SRC="image/right.jpg" WIDTH="50" HEIGHT="50" BORDER=0 ALT="�������"></a>
      <%}%>
       </td>
     </table>					
					
      <%for(int i=list.getIstart();i<list.getIend();i++){  %>			
     <table align="center" border="1"width="550">
      <hr>
      <tr>
       <td align="center" width="60" >�ۼ���</td>
       <td>&nbsp;&nbsp;<%= list.getVisitor().elementAt(i)	%></td>
       <td align="center" width="60">E-Mail</td>
       <td colspan="3">&nbsp;&nbsp;<%= list.getEmail().elementAt(i)%></td>
      </tr>
      <tr>
       <td align="center" width="60">�۹�ȣ</td>
       <td>&nbsp;&nbsp;<%= list.getNo().elementAt(i)%></td>	
       <td align="center" width="60">�ۼ���¥</td>
       <td colspan="3">&nbsp;&nbsp;<%=list.getRegist_date().elementAt(i)%></td>	
      </tr>
      <tr>
       <td align="center" width="60">Ȩ ������</td>
       <td colspan="5">&nbsp;&nbsp;<a href="<%=list.getHomepage().elementAt(i)%>"><%=list.getHomepage().elementAt(i)%></a></td>
      </tr>						
     </table>
     <table align="center" cellpadding="10" border="1"width="550">
      <tr>
       <td><a href="delete.jsp?delno=<%=list.getNo().elementAt(i)%>">����</a><font color="#663366"><pre><%=list.getContents().elementAt(i)%></pre></font></td>						
      </tr>	
     </table>
      <%}%>

     <table align="center" width="550" border="0">
      <tr>
       <td align="right" width="183">
      <%if(list.getIremain()==0 && list.getCurrent()<1){%>
        &nbsp;&nbsp;&nbsp;&nbsp;								
      <%}else if(list.getCurrent()!=0){%>	
        <a href="list.jsp?pg_count=<%=list.getCurrent()-1%>">
        <IMG SRC="image/left.jpg" WIDTH="50" HEIGHT="50" BORDER=0 ALT="�������"></a>
      <%}else{%>
        &nbsp;&nbsp;&nbsp;&nbsp;
      <%}%>
							
       </td>
       <td align="center" width="184">
       <a href="write.html" onMouseOver="status='post a new article';return true;" onMouseOut="status=''"><img src="image/write.jpg" width=50 height=50 border=0></a>
       </td>
       <td align="left" width="183">
      <%if(list.getCurrent()==list.getIvalue()-1&& list.getIremain()==0){%>
        &nbsp;&nbsp;&nbsp;&nbsp;
      <%}else if(list.getCurrent()!=list.getIvalue()){%>
        <a href="list.jsp?pg_count=<%=list.getCurrent()+1%>">
        <IMG SRC="image/right.jpg" WIDTH="50" HEIGHT="50" BORDER=0 ALT="�������"></a>
      <%}%>
       </td>
     </table>
     </font>
    </FORM>
   </body>
 </html>
