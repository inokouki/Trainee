package bulletinboardsystemfiltertest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletinboardsystemfiltertest.beans.Branch;
import bulletinboardsystemfiltertest.beans.Department;
import bulletinboardsystemfiltertest.beans.User;
import bulletinboardsystemfiltertest.service.BranchService;
import bulletinboardsystemfiltertest.service.DepartmentService;
import bulletinboardsystemfiltertest.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		User loginuser = (User) request.getSession().getAttribute("loginUser");
		request.setAttribute("loginUser", loginuser);

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches", branches);

		List<Department> departments = new DepartmentService().getDepartment();
		request.setAttribute("departments", departments);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		if(isValid(request, messages) == true) {
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setLoginId(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password"));
			user.setBranchId(request.getParameter("branch_id"));
			user.setDepartmentId(request.getParameter("department_id"));

			new UserService().register(user);

			response.sendRedirect("admin");
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("login_id", login_id);
			session.setAttribute("password", password);
			session.setAttribute("name", name);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String checkpassword = request.getParameter("checkpassword");
		String name = request.getParameter("name");
		String branchid = request.getParameter("branch_id");
		String departmentid = request.getParameter("department_id");

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("・ユーザーIDを入力してください");

		} else if (StringUtils.length(login_id) < 6) {
			messages.add("・ユーザーIDは6文字以上です");

		} else if (StringUtils.length(login_id) > 20) {
			messages.add("・ユーザーIDは20文字以下です");
		}

		if (StringUtils.isEmpty(password) == true) {
			messages.add("・パスワードを入力してください");

		} else if (StringUtils.length(password) < 6) {
			messages.add("・パスワードは6文字以上です");

		} else if (StringUtils.length(password) > 255) {
			messages.add("・パスワードは255文字以下です");
		}

		if (!password.equals(checkpassword) || (password == null && checkpassword != null)
				|| (password != null && checkpassword == null)) {
			messages.add("・パスワードが一致しません");
		}

		if (StringUtils.isEmpty(name)  == true ) {
			messages.add("・名前を入力してください");
		} else if (StringUtils.length(name) > 10) {
			messages.add("・名前は10文字以下です");
		}

		if (branchid.equals("0")) {
			messages.add("・支店を選択してください");
		}

		if (departmentid.equals("0")) {
			messages.add("・部署・役職を選択してください");
		}

		if(messages.size() == 0) {
			return true;

		} else {

			return false;
		}
	}
}