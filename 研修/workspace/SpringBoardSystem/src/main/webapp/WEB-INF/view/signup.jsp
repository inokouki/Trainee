<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta charset="utf-8">
	<title>ユーザー新規登録画面</title>
</head>

<body>

<h4>ユーザー新規登録</h4>

<c:if test="${ not empty errors }">
	<div class="errors">
		<ul>
			<c:forEach items="${errors}" var="message">
				<c:out value="${message}" /><br/>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errors" scope="session"/>
</c:if>

	<form:form modelAttribute="SignupForm" method="post">
		<form:label path="loginId">ログインID</form:label><br />
		<form:input path="loginId" value="${loginId}"></form:input><br/><br/>

		<form:label path="password">パスワード</form:label><br />
		<form:input path="password" value="${password}"></form:input><br/><br/>

		<form:label path="name">名前</form:label><br/>
		<form:input path="name" value="${name}"></form:input><br/><br/>

		<form:label path="branchId">支店</form:label><br/>
		<form:select path="branchId" multiple="false">
			<form:option value="0" label="-選択してください-"/>
			<form:option value="1" label="本社" />
			<form:option value="2" label="支店A" />
			<form:option value="3" label="支店B" />
			<form:option value="4" label="支店C" />
		</form:select><br/><br/>

		<form:label path="departmentId">部署・役職</form:label><br />
		<form:select path="departmentId" multiple="false">
			<form:option value="0" label="-選択してください-"/>
			<form:option value="1" label="総務人事" />
			<form:option value="2" label="情報管理" />
			<form:option value="3" label="支店長" />
			<form:option value="4" label="社員" />
		</form:select><br/><br/>

		<input type="submit" value="登録する">
	</form:form><br/><br/>

	<c:url value="/admin" var="messageUrl" />
	<a href="${messageUrl}"><button>戻る</button></a>
</body>

</html>