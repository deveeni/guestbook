<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String no= request.getParameter("no");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
   <form method="post" action="delete.jsp">
   <input type='hidden' name="no" value="<%=no %>"> <!-- 밸류값 세팅 -->
   <table>
      <tr>
         <td>비밀번호</td>
         <td><input type="password" name="password"></td> <!-- 사용자에게 비밀번호 입력받고 no값은 숨겨야함 위에 input에 hidden -->
         <td><input type="submit" value="확인"></td> <!-- delete에서 삭제하고 다시 deleteform으로 redirect -->
         <td><a href="index.jsp">메인으로 돌아가기</a></td>
      </tr>
   </table>
   </form>
</body>
</html>