package bulletinboardsystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bulletinboardsystem.beans.User;
import bulletinboardsystem.service.LoginService;

@WebServlet(urlPatterns = { "/signin" })
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.getRequestDispatcher("signin.jsp").forward(request, response);;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		String loginid = request.getParameter("loginid");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		User user = loginService.login(loginid, password);

		if (user != null) {
			session.setAttribute("loginUser", user);
			response.sendRedirect("./");
		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("・ログインに失敗しました。");
			session.setAttribute("errorMessages", messages);
			session.setAttribute("loginid", loginid);
			session.setAttribute("password", password);

			response.sendRedirect("signin");
		}
	}
}