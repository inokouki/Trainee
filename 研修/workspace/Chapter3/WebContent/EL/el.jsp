<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EL式のサンプル</title>
</head>
<body>
<h3>EL式のサンプル</h3>
<h4>演算子を利用したサンプル</h4>
<%
	int[] intArray = { 1, 2, 3, 4, 5 };
	for (int val : intArray) {
		pageContext.setAttribute("val", val);
%>

${val} は、${val % 2 == 0 ? "偶数" : "奇数"} です。
<br />

<%
	}
%>

<h4>四則演算のサンプル</h4>
1+2は、${ 1 + 2 }

</body>
</html>