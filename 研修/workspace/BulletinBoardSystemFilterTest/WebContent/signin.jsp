<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
	<link rel="stylesheet" type="text/css" href="css/signin.css" />
</head>
<body>

<div class="backimage">
<div class="main-contents">

<h4>掲示板システム</h4>
<HR width="100%"><br />

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

<form action="signin" method="post"><br />
	<h1><label for="loginid">ログインID</label></h1>
	<h1><input name="loginid" id="loginid" /></h1> <br />

	<h1><label for="password">パスワード</label></h1>
	<input name="password" type="password" id="password" />
	<br /><br />

	<input type="submit" value="ログイン" />
	<br /><br />
	<HR width="100%"><br />
	<div class="copyright">Copyright © 2016 BARISTRIDE GROUP All Rights Reserved.</div>
</form>
</div>
</div>
</body>
</html>