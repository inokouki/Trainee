<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>set,out,removeのサンプル</title>
</head>
<body>
<h3>set,out,removeのサンプル</h3>

<c:set var="str" value="Hello, core!"/><%-- strにHello, core!をセットw --%>

<c:out value="${str}"/><%-- strを出力 --%>
<br/>

<c:remove var="str"/><%-- strを削除 --%>

<c:out value="${str}"
	default="指定した変数が空の場合に表示されるデフォルトの文字列です"/><%-- 削除されたstrを出力 --%>
<br/>

<%-- JavaScriptを出力する。 --%>
<c:set var="str" value="<script type='text/javascript'> alart('Hello, core!'); </script>" />
<c:out value="${str}" /><br/><!-- escapeXml属性省略 -->
<c:out value="${str}" escapeXml="false"/><br/><!-- escapeXXml=false -->
${str}<br/><!-- EL式で出力 -->
<br/>

</body>
</html>