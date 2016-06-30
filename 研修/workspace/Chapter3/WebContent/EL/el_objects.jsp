<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>暗黙オブジェクトのサンプル</title>
</head>
<body>
<h3>暗黙オブジェクトのサンプル</h3>

<table border="1">
<tr><td>利用しているブラウザの情報</td><td>${header['user-agent']} </td></tr>
<tr><td>ブラウザに設定されている言語の情報</td><td>${header['accept-language']}</td></tr>
<tr><td>CPUの情報</td><td>${header['ua-cpu']} </td></tr>
</table>

</body>
</html>