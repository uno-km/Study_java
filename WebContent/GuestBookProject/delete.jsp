<%@ page contentType="text/html; charset=EUC-KR" %>	
<html>
 <head>
  <title>���� �� ����
  </title>
 <STYLE TYPE="text/css">
  <!--
   body { font-family: ����, Verdana; font-size: 9pt}
   td   { font-family: ����, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
  --->
 </STYLE>			
 <Script Language="JavaScript">
  <!--
    function unsubmit(){				
     if(document.submit_user.adminID.value=="") {
      alert("ID�� �Էµ��� �ʾҽ��ϴ�.");						
      return false;
    }
     if(document.submit_user.adminPass.value=="") {
     alert("Password�� �Էµ��� �ʾҽ��ϴ�.");
     return false;
    }
     return true;
    }
  //-->
 </Script>
 </head>
 <body>
  <BR><BR><BR><BR>
  <h3 align="center"><font color="#FF0066"><b>
  <%=request.getParameter("delno")%>��</b></font><font color="#336600"> ���� �����ϰڽ��ϴ�.</h3>
  <h3 align="center">�������� ���̵�� ��й�ȣ�� �Է��� �ּ���.</font></h3>

  <FORM name="submit_user" METHOD=POST ACTION="delete_end.jsp?delno=<%=request.getParameter("delno")%>"
  onSubmit="return unsubmit()">
				
    <table align="center">
    <br>
    <br>&nbsp;&nbsp;adminID :  <font color="red">'id'</font><br>
    &nbsp;&nbsp;adminPass :  <font color="red">'pass'</font><br>
     <tr>
      <td><li></td>
      <td align="center"><font color="#0000CC">������ ID</font></td>
      <td><INPUT TYPE="text" NAME="adminID"></td>
     </tr>
     <tr>
      <td><li></td>
      <td align="center"><font color="#CC0033">������ ��ȣ</font></td>
      <td><INPUT TYPE="password" NAME="adminPass"></td>
       <INPUT TYPE="hidden" name="no" value="<%=request.getParameter("delno")%>">
     </tr>
    </table>					
    <p>
    <table  align="center">
     <tr>
      <td>							
       <INPUT TYPE="submit" value="����">&nbsp;&nbsp;
       </FORM>
      </td>
      <td>
       <FORM name="new_User" METHOD=POST ACTION="list.jsp">&nbsp;&nbsp;
       <INPUT TYPE="submit" value="���">
       </FORM>
      </td>
     </tr>					
    </table>		
 </body>
</html>
