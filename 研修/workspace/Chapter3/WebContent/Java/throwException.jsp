<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %><%-- この指定をすることで、例外が発生した場合にerror.jspに飛びます。 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>例外を発生させるページ</title>
</head>
<body>
<h3>例外を発生させるページ</h3>

<%
	String str = null;
	str.charAt(0);
%>

</body>
</html>