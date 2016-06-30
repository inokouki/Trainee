package chapter4;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/content_type")
public class ContentTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");

		if ("stream".equals(type) == true) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment; filename=\"foo.pdf\"");
		} else if ("pdf".equals(type) == true) {
			response.setContentType("application/pdf");
		} else {
			throw new UnsupportedOperationException("type=[" + type + "]");
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("pdf/sample.pdf");
		dispatcher.forward(request, response);

	}
}
