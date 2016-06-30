<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
<!--
function getCurrentTime(){
	$.ajax({
		url: "ajaxServlet",
		cache: false,
		dataType: "text",
		success: function(html){
			$("#results").append(html);
			$("#results").append("<br />");
  		}
	});
}
-->
</script>

<title>Ajaxのサンプル</title>
</head>
<body>
<h3>Ajaxのサンプル</h3>

<%
	Date date = new Date();
	request.setAttribute("currentDate", date);
%>

初めにJSPを表示した時間 :
<fmt:formatDate value="${currentDate}" pattern="yyyy/MM/dd HH:mm:ss" /><br />

<form action="">
	<input type="button" value="Ajaxで値を取得"
		onClick="getCurrentTime()">
</form>

<div id="results"></div>

</body>
</html>