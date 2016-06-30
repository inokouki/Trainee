<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<link rel="stylesheet" type="text/css" href="css/admin.css" />
</head>
<body>

<c:if test="${ empty loginUser }">
<meta http-equiv="refresh" content="0; url=signin.jsp">
</c:if>

<h1>ユーザー管理</h1>

<c:if test="${ loginUser.departmentId == 1}">

<h2><a href="signup">ユーザー新規登録</a></h2>
<h2><a href="./">ホーム</a></h2>
<br />

<div class="admin">
	<table border=1 >
 	<tr><th>ログインID</th><th>名称</th><th>支店</th><th>部署・役職</th><th>編集ボタン</th>
 	<th>アカウント状況</th><th>復活停止ボタン</th><th>アカウント削除</th></tr>
	<c:forEach items="${users}" var="message">
		<div class="admin">
 						<td>${message.loginId}</td><td>${message.name}</td>

 						<th>
 						<c:if test="${message.branchId == 1}"><font color="red">本社</font></c:if>
 						<c:if test="${message.branchId == 2}">支店A</c:if>
 						<c:if test="${message.branchId == 3}">支店B</c:if>
 						<c:if test="${message.branchId == 4}">支店C</c:if>
 						</th>

 						<th>
						<c:if test="${message.departmentId == 1}"><font color="red">総務人事担当者</font></c:if>
						<c:if test="${message.departmentId == 2}"><font color="blue">情報管理担当者</font></c:if>
						<c:if test="${message.departmentId == 3}">支店長</c:if>
						<c:if test="${message.departmentId == 4}">社員</c:if>
						</th>

 						<th><form action="edit" method="get">
						<input type="submit" value="ユーザー情報の編集" >
						<input name="editid" type="hidden" id="editid" value="${message.id}">
						<input name="editloginId" type="hidden" id="editloginId" value="${message.loginId}">
						<input name="editpassword" type="hidden" id="editpassword" value="${message.password}">
						<input name="editname" type="hidden" id="editname" value="${message.name}">
						<input name="editdepartment" type="hidden" id="editdepartment" value="${message.departmentId}">
						<input name="editbranch" type="hidden" id="editbranch" value="${message.branchId}">
						</form></th>

						<th>
						<c:if test="${ message.available  == 1}"><font color="green"><c:out value="使用可能"></c:out>
						</font></c:if>
						<c:if test="${ message.available  == 0}"><font color="red"><c:out value="使用不可"></c:out>
						</font></c:if>
						</th>

 					<td><c:if test="${ message.available  == 1 && loginUser.loginId != message.loginId}">
					<form action="admin" method="post">
							<input type="submit" value="アカウントの[停止]"
							onClick="return confirm('このアカウントは現在使用可能です。アカウントの[停止]を行いますか？')">
							<input name="stop" type="hidden" id="stop" value="${message.id}">
					</form></c:if>

					<c:if test="${ message.available  == 1 && loginUser.loginId == message.loginId}">
					<p align="center"><font color="green"><c:out value="ログイン中"></c:out></font></p></c:if>

						<c:if test="${ message.available  == 0}">
						<form action="admin" method="post">
							<input type="submit" value="アカウントの[復活]"
							onClick="return confirm('このアカウントは現在停止中です。アカウントの[復活]を行いますか？')">
							<input name="stop" type="hidden" id="stop" value="${message.id}">
						</form>
						</c:if>
					</td>

					<td>
					<c:if test="${loginUser.loginId == message.loginId}"><p align="center"><font color="green">
					<c:out value="ログイン中"></c:out></font></p></c:if>
					<c:if test="${loginUser.loginId != message.loginId}">
					<form action="admin" method="post">
							<input type="submit" value="アカウントの削除"
							onClick="return confirm('アカウントの削除を行いますか？')">
							<input name="delete" type="hidden" id="delete" value="${message.id}">
					</form>
					</c:if>
					</td>
					</tr>
		</c:forEach>
	</table>
</div>

</c:if>

<c:if test="${ loginUser.departmentId != 1 }">
アクセス権限がありません。3秒後にホーム画面に戻ります。
	<meta http-equiv="refresh"content="3; url=./">
</c:if>

</body>
</html>