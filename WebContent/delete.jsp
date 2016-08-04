<%@page import="kr.ac.sungkyul.guestbook.dao.GuestBookDao"%>
<%@page import="kr.ac.sungkyul.guestbook.vo.GuestBookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String no = request.getParameter("no");
	String password = request.getParameter("password");
	
		
	GuestBookDao dao = new GuestBookDao();
	boolean result = dao.delete(Long.parseLong(no),password);
	
	response.sendRedirect("/guestbook");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		if( result == true ){
	%>
	<h1>성공했습니다.</h1>
	<%
		}else{ 
	%>
	<h1>실패했습니다.</h1>
	<%
		} 
	%>
</body>
</html>