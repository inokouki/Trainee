package chapter4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns = { "/path", "/path1", "/path/*" },
	initParams = { @WebInitParam(name = "key1", value = "hoge"),
			@WebInitParam(name = "key2", value = "hogehoge")}
)

public class AnnotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String val1;
	private String val2;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");
		out.println("<H3>AnnotationServlet</H3>");

		out.println("key1=" + val1 + "<BR />");
		out.println("key2=" + val2 + "<BR />");

		out.println("</BODY>");
		out.println("</HTML>");
	}

	@Override
	public void init() throws ServletException {
		super.init();

		val1 = getInitParameter("key1");
		val2 = getInitParameter("key2");
	}
}
