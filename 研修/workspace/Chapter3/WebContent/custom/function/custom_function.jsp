<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.example.com/jsp/myfunction"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>functionsのサンプル</title>
</head>
<body>
<h3>functionsのサンプル</h3>

<%
	request.setAttribute("tag", "<b>aBc</b>");
%>

<table border="1">
	<tr>
		<td>記述</td>
		<td>結果</td>
	</tr>
	<tr>
		<td>\${tag}</td>
		<td>${tag}</td>
	</tr>
	<tr>
		<td>&lt;c:out value="\${tag}"/&gt;</td>
		<td><c:out value="${tag}"/></td>
	</tr>
	<tr>
		<td>&lt;c:out value="\${tag}" escapeXml="false"/&gt;</td>
		<td><c:out value="${tag}" escapeXml="false"/></td>
	</tr>
	<tr>
		<td>\${fn:escapeXml(tag)}</td>
		<td>${fn:escapeXml(tag)}</td>
	</tr>
	<tr>
		<td>\${f:esc(tag)}</td>
		<td>${f:esc(tag)}</td>
	</tr>
</table>
</body>
</html>