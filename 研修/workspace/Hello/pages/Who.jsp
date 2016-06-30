<%@ page contentType="text/html; charset=Shift-JIS" %>
<%@ taglib uri="http://struts.apache.org/tags-html"
  prefix="html" %>
<html:html>
  <head>
    <title>Who</title>
  </head>
  <html:form action="/hello">
    <table border="0">
      <html:errors/>
      <tr><td>
        Ç†Ç»ÇΩÇÃñºëOÇÕÅH<br>
        <html:text property="name" size="20" maxlength="30" />Ç≈Ç∑ÅB
      </td></tr>
      <tr><td>
        <html:submit value="OK" />
      </td></tr>
    </table>
  </html:form>
</html:html>