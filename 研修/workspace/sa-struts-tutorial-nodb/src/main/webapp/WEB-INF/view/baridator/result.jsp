<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Baridator:QuestionPage</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>

<body>
Finish!!!!<br/><br/>

あなたが思い浮かべた人って...
<p>${f:h(searchname)}</p>　ですか？<br/><br/>

request<br/>
<c:out value="${question1}"/>
<c:out value="${question2}"/>
<c:out value="${question3}"/>
<c:out value="${question4}"/>

<s:form>
<input type="submit" name="index" value="トップページに戻る">
</s:form>

</body>
</html>