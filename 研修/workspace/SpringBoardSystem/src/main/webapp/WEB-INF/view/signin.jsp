<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<meta charset="utf-8">
	<title>signin</title>
	<link rel="stylesheet" type="text/css" href="resources/css/signin.css" />
</head>

<body>
<c:if test="${empty errorMessage}">

<c:if test="${ not empty errorMessages }">
	<div class="errors">
		<h3><font color="red">${errorMessages}</font></h3>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<h4>掲示板システム</h4>

<form:form modelAttribute="SigninForm" method="post">
	<form:label path="loginId">ログインID</form:label><br />
	<form:input path="loginId" value="${loginId}"></form:input><br/><br/>

	<form:label path="password">パスワード</form:label><br />
	<form:input path="password" type="password" value="${password}"></form:input><br/><br/>


	<input type="submit" value="ログイン">
</form:form>
</c:if>

<div class="error">
<c:if test="${ not empty errorMessage }">
	<img src="./image/kowai.jpg" width="1340" height="620"/>
</c:if>
</div>

</body>
</html>