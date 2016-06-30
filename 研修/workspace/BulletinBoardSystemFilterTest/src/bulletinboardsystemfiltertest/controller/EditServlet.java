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
import bulletinboardsystemfiltertest.service.EditService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int edituserid;
	private String inputid = null;
	private boolean editflag = false;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		request.setAttribute("loginId", request.getParameter("editloginId"));
		request.setAttribute("name", request.getParameter("editname"));
		request.setAttribute("branch_id", request.getParameter("editbranch"));
		request.setAttribute("department_id", request.getParameter("editdepartment"));

		inputid = request.getParameter("editid");

		User loginuser = (User) request.getSession().getAttribute("loginUser");
		request.setAttribute("loginUser", loginuser);

		List<Branch> branches = new BranchService().getBranch();
		request.setAttribute("branches", branches);

		List<Department> departments = new DepartmentService().getDepartment();
		request.setAttribute("departments", departments);

		request.getRequestDispatcher("edit.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");


		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String branchid = request.getParameter("branch_id");
		String departmentid = request.getParameter("department_id");

		if (editflag == false) {
			edituserid = Integer.parseInt(inputid);
			editflag = true;
		}

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if (isValid(request, messages) == true) {
			User edituser = new User();
			edituser.setLoginId(request.getParameter("login_id"));
			edituser.setPassword(request.getParameter("password"));
			edituser.setName(request.getParameter("name"));
			edituser.setBranchId(request.getParameter("branch_id"));
			edituser.setDepartmentId(request.getParameter("department_id"));

			new EditService().editer(edituserid, login_id, password, name, branchid, departmentid, edituser);

			inputid = null;
			editflag = false;

			response.sendRedirect("admin");
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("loginId", login_id);
			session.setAttribute("password", password);
			session.setAttribute("name", name);
			session.setAttribute("branch_id", branchid);
			session.setAttribute("department_id", departmentid);

			response.sendRedirect("edit");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String loginid = request.getParameter("login_id");
		String checkpassword = request.getParameter("checkpassword");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String branchid = request.getParameter("branch_id");
		String departmentid = request.getParameter("department_id");

		if (StringUtils.isEmpty(loginid) == true) {
			messages.add("・ログインIDを入力してください");
		} else if (StringUtils.length(loginid) < 6) {
			messages.add("・ログインIDは6文字以上です");
		} else if (StringUtils.length(loginid) > 20) {
			messages.add("・ログインIDは20文字以下です");
		}


		if (StringUtils.length(password) != 0) {
			if (StringUtils.length(password) < 6) {
				messages.add("・パスワードは6文字以上です");
			} else if (StringUtils.length(password) > 255) {
				messages.add("・パスワードは255文字以下です");
			}
			if (!(password.equals(checkpassword)) || (password != null && checkpassword == null)) {
				messages.add("・パスワードが一致しません");
			}
		}

		if (StringUtils.length(checkpassword) != 0 && StringUtils.length(password) == 0) {
			messages.add("・パスワードのフォームは両方入力してください");
		}

		if (StringUtils.isEmpty(name)  == true ) {
			messages.add("・名前を入力してください");
		} else if (StringUtils.length(name) > 10) {
			messages.add("・名前は10文字以下です");
		}

		if (branchid.equals("0")) {
			messages.add("・支店を選んでください");
		}
		if (departmentid.equals("0")) {
			messages.add("・部署・役職を選んでください");
		}

		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}