<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<meta charset="utf-8">
	<title>ホーム画面</title>
</head>
<body>

${loginUser.departmentId}<br/>

<div class="post">
<c:url value="/post" var="messageUrl" />
<a href="${messageUrl}"><button>新規投稿画面</button></a>
</div>

<div class="logout" align="right">
<c:url value="/signin" var="messageUrl" />
<a href="${messageUrl}"><button>ログイン画面</button></a>
</div>

<c:if test="${loginUser.departmentId == 1 }">
<c:url value="/admin" var="messageUrl" />
<a href="${messageUrl}"><button>管理画面</button></a>
<br /><br /><br />
</c:if>

<div class="contribution">
<c:forEach items="${contributions}" var="contribution">
	<hr width="100%" size="10" color="black"><br />
	<span class="title">[件名] <c:out value="${contribution.title}" /></span>
	<span class="category">[投稿カテゴリー] <c:out value="${contribution.category}"/></span>
	<span class="name">[投稿者] <c:out value="${contribution.users_name}"></c:out></span>
	<span class="created">[投稿日時] <fmt:formatDate value="${contribution.created}"
	pattern="yyyy年MM月dd日（E） kk時mm分ss秒" /></span><br /><br />
	[本文]<br />
	<pre class="text"><c:out value="${contribution.text}"/></pre><br /><br />

	<br /><hr width="100%" size="4" color="green"><br />
</c:forEach><br /><br />
</div>



</body>
</html>