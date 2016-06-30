<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>スクリプトレットのサンプル</title>
</head>
<body>
<h3>スクリプトレットのサンプル</h3>

<%
	String[] array = { "A", "B", "C", "D", "E" };
	for (String val : array) {
		out.println(val);
		out.println("<br />");
	}
%>

</body>
</html>