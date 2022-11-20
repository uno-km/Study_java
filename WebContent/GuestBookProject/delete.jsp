<%@ page contentType="text/html; charset=EUC-KR" %>	
<html>
 <head>
  <title>방명록 글 삭제
  </title>
 <STYLE TYPE="text/css">
  <!--
   body { font-family: 돋움, Verdana; font-size: 9pt}
   td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
  --->
 </STYLE>			
 <Script Language="JavaScript">
  <!--
    function unsubmit(){				
     if(document.submit_user.adminID.value=="") {
      alert("ID가 입력되지 않았습니다.");						
      return false;
    }
     if(document.submit_user.adminPass.value=="") {
     alert("Password가 입력되지 않았습니다.");
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
  <%=request.getParameter("delno")%>번</b></font><font color="#336600"> 글을 삭제하겠습니다.</h3>
  <h3 align="center">관리자의 아이디와 비밀번호를 입력해 주세요.</font></h3>

  <FORM name="submit_user" METHOD=POST ACTION="delete_end.jsp?delno=<%=request.getParameter("delno")%>"
  onSubmit="return unsubmit()">
				
    <table align="center">
    <br>
    <br>&nbsp;&nbsp;adminID :  <font color="red">'id'</font><br>
    &nbsp;&nbsp;adminPass :  <font color="red">'pass'</font><br>
     <tr>
      <td><li></td>
      <td align="center"><font color="#0000CC">관리자 ID</font></td>
      <td><INPUT TYPE="text" NAME="adminID"></td>
     </tr>
     <tr>
      <td><li></td>
      <td align="center"><font color="#CC0033">관리자 암호</font></td>
      <td><INPUT TYPE="password" NAME="adminPass"></td>
       <INPUT TYPE="hidden" name="no" value="<%=request.getParameter("delno")%>">
     </tr>
    </table>					
    <p>
    <table  align="center">
     <tr>
      <td>							
       <INPUT TYPE="submit" value="삭제">&nbsp;&nbsp;
       </FORM>
      </td>
      <td>
       <FORM name="new_User" METHOD=POST ACTION="list.jsp">&nbsp;&nbsp;
       <INPUT TYPE="submit" value="취소">
       </FORM>
      </td>
     </tr>					
    </table>		
 </body>
</html>
