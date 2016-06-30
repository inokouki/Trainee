package bulletinboardsystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinboardsystem.beans.User;
import bulletinboardsystem.service.AdminService;

@WebServlet(urlPatterns = { "/admin" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String admineditstr = null, admindeletestr = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		User loginuser = (User) request.getSession().getAttribute("loginUser");

		List<User> users = new AdminService().getUser();

		request.setAttribute("users", users);
		request.setAttribute("loginUser", loginuser);

		request.getRequestDispatcher("/admin.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		admindeletestr = request.getParameter("delete");
		admineditstr = request.getParameter("stop");

		if (admindeletestr == null) {
			int editid = Integer.parseInt(admineditstr);
			List<User> users = new AdminService().editAvailableUser(editid);
			request.setAttribute("users", users);
		}

		if (admineditstr == null) {
			int deleteid = Integer.parseInt(admindeletestr);
			AdminService.deleteUser(deleteid);
		}


		response.sendRedirect("admin");
	}
}