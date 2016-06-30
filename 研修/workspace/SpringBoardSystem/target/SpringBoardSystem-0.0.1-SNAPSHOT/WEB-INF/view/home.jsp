<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>ホーム画面</title>
	</head>
	<body>
		<c:url value="/post" var="messageUrl" />
		<a href="${messageUrl}"><button>新規投稿画面</button></a> <br /><br />

		<c:url value="/admin" var="messageUrl" />
		<a href="${messageUrl}"><button>管理画面</button></a>
	</body>
</html>