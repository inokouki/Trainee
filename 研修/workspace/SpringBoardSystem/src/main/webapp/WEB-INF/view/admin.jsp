<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<meta charset="utf-8">
	<title>admin</title>
</head>
<body>

	<c:url value="/signup" var="messageUrl" />
	<a href="${messageUrl}"><button>新規登録画面</button></a> <br /><br />

	<c:url value="/edit" var="messageUrl" />
	<a href="${messageUrl}"><button>情報編集画面</button></a> <br /><br />

	<c:url value="/home" var="messageUrl" />
	<a href="${messageUrl}"><button>ホーム画面</button></a>

</body>
</html>