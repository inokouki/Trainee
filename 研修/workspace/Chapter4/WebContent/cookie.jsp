<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>クッキーのサンプル</title>
</head>
<body>
<h3>クッキーのサンプル</h3>

<form action="cookie" method="post"><br />
名前を入力してください。<input type="text" name="name" /> <br />
<input type="submit" name="submit" value="入力！"/> <br />
<a href="cookie">リロード</a><br />
</form>

<br />

<c:if test="${ not empty userName }">
${ userName }さんですね。
</c:if>
<c:if test="${ empty userName }">
前回入力した値がありません。
</c:if>

</body>
</html>