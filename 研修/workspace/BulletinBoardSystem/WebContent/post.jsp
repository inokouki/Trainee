<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規投稿画面</title>
	<link rel="stylesheet" type="text/css" href="css/post.css" />
</head>
<body>

<c:if test="${ empty loginUser }">
<meta http-equiv="refresh" content="0; url=signin.jsp">
</c:if>

<h1>新規投稿画面</h1><br />

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<h2><c:out value="${message}" /></h2>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="post" method="post"> <br />
	件名(50文字以下)<br />
	<textarea name="title" cols="100" rows="1" class="title">${title}</textarea>
	<br />
	<br />
	本文(1000文字以下)<br />
	<textarea name="text" cols="100" rows="10" class="text">${text}</textarea>
	<br />
	<br />
	カテゴリー(10文字以下)<br />
	<textarea name="category" cols="20" rows="1" class="category">${category}</textarea>
	<br />
	<br />
	<input type="submit" value="投稿する">
	<c:remove var="title" scope="session"/>
	<c:remove var="text" scope="session"/>
	<c:remove var="category" scope="session"/>
</form>

<a href="./" >戻る</a>
</body>
</html>