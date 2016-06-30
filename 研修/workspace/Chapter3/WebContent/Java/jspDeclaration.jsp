<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP宣言のサンプル</title>
</head>
<body>
<h3>JSP宣言のサンプル</h3>

<p>現在日時は、<%=getCurrentTime("yyyy/MM/dd HH:mm:ss")%>です。</p>

</body>
</html>

<%!
	private static String getCurrentTime(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}
%>