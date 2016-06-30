<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>urlのサンプル</title>
</head>
<body>
<h3>urlのサンプル</h3>

<c:url var="javaUrl" value="http://www.google.co.jp/search">
	<c:param name="q" value="Java" />
	<c:param name="num" value="100" />
	<c:param name="hl" value="ja" />
</c:url>
<a href="${javaUrl}">[Java]で検索</a>

<br />

<c:url var="eclipseJavaUrl" value="http://www.google.co.jp/search">
	<c:param name="q" value="Eclipse Java" />
	<c:param name="num" value="100" />
	<c:param name="hl" value="ja" />
</c:url>
<a href="${eclipseJavaUrl}">[Eclipse Java]で検索</a>

<br />

<c:url var="encodedUrl" value="http://www.google.co.jp/search">
	<c:param name="q" value="1+1=" />
	<c:param name="num" value="100" />
	<c:param name="hl" value="ja" />
</c:url>
<a href="${encodedUrl}">[1+1=]で検索</a>

</body>
</html>