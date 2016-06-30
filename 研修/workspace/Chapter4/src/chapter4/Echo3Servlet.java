package chapter4;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/echo3" })
public class Echo3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{

		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("m");
		System.out.println(message);

		request.setAttribute("message", message);

		RequestDispatcher dispatcher = request.getRequestDispatcher("echo3.jsp");
		dispatcher.forward(request, response);
	}
}
