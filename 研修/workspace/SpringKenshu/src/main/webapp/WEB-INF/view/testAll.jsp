<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Welcome</title>
    </head>
    <body>
           <h1>${message}</h1>
	<c:forEach items="${tests}" var="test">
		<p><c:out value="${test.id}"></c:out> : <c:out value="${test.name}"></c:out>
		<a href="${pageContext.request.contextPath}/test/update/input/${test.id}/">編集</a></p>

	</c:forEach>
    </body>
</html>