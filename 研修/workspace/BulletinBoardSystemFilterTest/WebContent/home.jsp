<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム</title>
	<link rel="stylesheet" type="text/css" href="css/home.css" />
</head>
<body>

<c:if test="${ empty loginUser }">
<meta http-equiv="refresh" content="0; url=signin.jsp">
</c:if>

<c:if test="${not empty loginUser }">

<c:if test="${loginUser.available == 1 }">

<div class="main-contents">

<div class="header">
	<form action="signin" method="get">
		<c:remove var='all' scope="session"/>
		<div align="right" >
		<input type="submit" value="ログアウト"><br />
		<input id="logout" value="1" type="hidden" name="logout">
		</div>
	</form>

	<c:if test="${ empty loginUser }">
		<h2><a href="signin">ログイン</a></h2>
	</c:if>
	<c:if test="${ not empty loginUser }">
		<c:if test="${loginUser.departmentId == 1 }">
		<h2><a href="admin">ユーザー管理(総務人事担当のみ)</a></h2>
		</c:if>
		<h2><a href="post">新規投稿</a></h2>
		<br />
	</c:if>

</div>

		<a href="./"><button>検索リセット</button></a>
		<form action="./" method="get">
			カテゴリー <select name="searchCategory">
			<option value="${searchCategory}">${searchCategory}</option>
			<c:forEach items="${categories}" var="category">
			<option value="${category.category}">${category.category}</option>
			</c:forEach></select><br />

			投稿日検索→ <select name="searchTimeBeforeYear">
			<option value="${searchTimeBeforeYear}">${searchTimeBeforeYear}</option>
			<option value="2016">2016</option>
			<option value="2015">2015</option>
			</select>年

			<select name="searchTimeBeforeMonth">
			<option value="${searchTimeBeforeMonth}">${searchTimeBeforeMonth}</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			</select>月

			<select name="searchTimeBeforeDay">
			<option value="${searchTimeBeforeDay}">${searchTimeBeforeDay}</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
			</select>日から

			<select name="searchTimeAfterYear">
			<option value="${searchTimeAfterYear}">${searchTimeAfterYear}</option>
			<option value="2016">2016</option>
			<option value="2015">2015</option>
			</select>年

			<select name="searchTimeAfterMonth">
			<option value="${searchTimeAfterMonth}">${searchTimeAfterMonth}</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			</select>月

			<select name="searchTimeAfterDay">
			<option value="${searchTimeAfterDay}">${searchTimeAfterDay}</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
			</select>日まで

			<input type="submit" value="検索する" />
		</form>

		<br /><br /><br />

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

<c:if test="${not empty searches }">
<div class="contribution">
	<c:forEach items="${searches}" var="contribution">
		<div class="contribution">
			<div class="contribution">
				<div class="contribution">
					<hr width="100%" size="10" color="black"><br />
					<span class="title">[件名] <c:out value="${contribution.title}" /></span>
					<span class="name">[投稿者] <c:out value="${contribution.name}"></c:out></span>
					<span class="created">[投稿日時] <c:out value="${contribution.created}" /></span>
					<span class="category">[投稿カテゴリー] <c:out value="${contribution.category}"/></span><br /><br />
					[本文]<br />
					<pre class="text"><c:out value="${contribution.text}"/></pre><br /><br />

					<c:if test="${loginUser.departmentId == 2 || loginUser.id == contribution.userId ||
					(loginUser.departmentId == 3 && contribution.departmentId == 4 &&
					 loginUser.branchId == contribution.branchId )}">
					<form action="./" method="post">
						<input type="submit" value="投稿の削除"
						onClick="return confirm('投稿の削除を行いますか？')">
						<input name="delete" type="hidden" id="delete" value="${contribution.id}">
					</form>
					</c:if>


					<br />
					<hr width="100%" size="4" color="green">
					<br />
				</div>
			</div>

			<div class="comment">
				<c:forEach items="${comments}" var="comment">
					<div class="comment">
						<c:if test="${ comment.contributionId == contribution.id }" >
							<span class="name">[コメント投稿者] <c:out value="${comment.name}"></c:out></span>
							<span class="commenttime">[コメント時間] <c:out value="${comment.created}" /></span><br />
							[コメント]<br />
							<pre class="text"><c:out value="${comment.text}"/></pre>
							<hr width="100%" size="1" color="blue" >
							<br /><br />
						</c:if>
					</div>
				</c:forEach>
			</div>

			<form action="./" method="post" > <br />
				コメント(500文字以下)<br />
				<textarea name="comment" cols="100" rows="5" class="cancelEnter" class="text"></textarea>
				<input name="contribution_id" type="hidden" id="contribution_id" value="${contribution.id}"/>
				<br />
				<input type="submit" value="コメントする">
				<input name="searchReset" type="hidden" value="0">
				<br /><br /><br />
			</form>
		</div>
	</c:forEach>
	<br />
	<br />
</div>
</c:if>

</c:if>

<c:if test="${loginUser.available == 0 }">
	アカウントが停止されています。
	<meta http-equiv="refresh"content="3; url=signin">
</c:if>
</c:if>


</body>
</html>
