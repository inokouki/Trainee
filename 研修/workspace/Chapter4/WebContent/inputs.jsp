<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>いろいろな入力のサンプル</title>
</head>
<body>
<h3>いろいろな入力のサンプル</h3>

<form action="inputs" method="post"><br />

名前(text) : <input type="text" name="name" />
<br />

パスワード(password) : <input type="password" name="password" />
<br />

hidden : <input type="hidden" name="hidden" value="123"/>
<br />

性別(radio) :
<input type=radio name="sex" value="male" checked>男性
<input type=radio name="sex" value="female">女性
<br />

趣味(checkbox) :
<input type=checkbox name="hobby" value="pc">パソコン
<input type=checkbox name="hobby" value="sports">スポーツ
<input type=checkbox name="hobby" value="book">読書
<br />

国籍(select) :
<select name="nationality">
	<option value="japan">日本</option>
	<option value="other">その他</option>
</select>
<br />

言語(select) :
<select name="language" multiple size="4">
	<option value="japanese">日本語</option>
	<option value="english">英語</option>
	<option value="chinese">中国語</option>
	<option value="french">フランス語</option>
	<option value="german">ドイツ語</option>
	<option value="korean">韓国語</option>
</select>
<br />

メモ(textarea) :
<textarea name="memo" cols="40" rows="4">
</textarea>
<br />

サブミット1(submit) : <input type="submit" name="submit1" value="サブミット1" />
<br />
サブミット2(submit) : <input type="submit" name="submit2" value="サブミット2" />
<br />
画像(image) : <input type="image" name="image" src="images/sample_button.png"/> <br />
</form>

<br />

<c:if test="${ not empty inputsResult }">
名前 : ${inputsResult.name}<br />
パスワード : ${inputsResult.password}<br />
hidden : ${inputsResult.hidden}<br />
性別 : ${inputsResult.sex}<br />
趣味 : <c:forEach items="$inputsResult.hobby}" var="hobby"> ${hobby},</c:forEach><br />
国籍 : ${inputsResult.nationality}<br />
言語 : <c:forEach items="${inputsResult.language}" var="language">
${language}, </c:forEach><br />
メモ : ${inputsResult.memo}<br />
サブミット1 : ${inputsResult.submit1}<br />
サブミット2 : ${inputsResult.submit2}<br />
画像.x : ${inputsResult.image_x}<br />
画像.y : ${inputsResult.image_y}<br />
</c:if>

</body>
</html>