<%@ page import="chapter3.Bean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JavaBeansを利用したEL式のサンプル</title>
</head>
<body>
<h3>JavaBeansを利用したEL式のサンプル</h3>

<%
	List<Bean> beanList = getBeanList();
	pageContext.setAttribute("beanList", beanList);
%>

1人目 : IDは ${beanList[0].ID}。名前は${beanList[0].name}。
<br />
2人目 : IDは ${beanList[1].ID}。名前は${beanList[1].name}。
<br />

</body>
</html>
<%!
	private static String[] names = { "太郎", "花子" };

	private static List<Bean> getBeanList() {
		List<Bean> ret = new ArrayList<Bean>();
		for ( int i = 0; i < names.length; i++) {
			Bean bean = new Bean();
			bean.setID(i);
			bean.setName(names[i]);
			ret.add(bean);
		}
		return ret;
	}
%>