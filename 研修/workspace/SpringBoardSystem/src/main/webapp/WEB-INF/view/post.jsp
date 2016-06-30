<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta charset="utf-8">
	<title>新規投稿画面</title>
</head>

<body>

<form:form modelAttribute="PostForm" method="post">
	<form:label path="title">件名</form:label><br />
	<form:textarea path="title" cols="100" rows="1" value="${title}"></form:textarea><br/><br/>

	<form:label path="text">本文</form:label><br />
	<form:textarea path="text" cols="100" rows="10" value="${text}"></form:textarea><br/><br/>

	<form:label path="category">カテゴリー</form:label><br />
	<form:textarea path="category" cols="20" rows="1" value="${category}"></form:textarea><br/><br/>

	<input type="submit" value="投稿する">
</form:form>
<br /><br />

<c:url value="/home" var="messageUrl" />
<a href="${messageUrl}"><button>戻る</button></a>

</body>
</html>