<%@page pageEncoding="UTF-8"%>
<html>
<head>
<title>Baridator:QuestionPage</title>
<link rel="stylesheet" type="text/css" href="${f:url('/css/sa.css')}" />
</head>

<body>
[Q3] 2016年入社？<br/><br/>

<s:form>
<input type="hidden" name="ask1answer" value="${f:h(answer) }"/>
<input type="submit" name="freshman" value="はい"/><br /><br/>
<input type="submit" name="staff" value="いいえ"/><br /><br /><br/><br /><br/>
<input type="submit" name="index" value="トップページに戻る">
</s:form>

request<br/>
<c:out value="${question1}"/>
<c:out value="${question2}"/>
<c:out value="${question3}"/>
<c:out value="${question4}"/>

</body>
</html>