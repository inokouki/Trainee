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

import bulletinboardsystemfiltertest.beans.Message;
import bulletinboardsystemfiltertest.beans.User;
import bulletinboardsystemfiltertest.service.MessageService;

@WebServlet(urlPatterns = { "/post" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.getRequestDispatcher("post.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if (isValid(request, messages) == true) {
			User user = (User)session.getAttribute("loginUser");

			int userid;
			Message message = new Message();

			message.setTitle(request.getParameter("title"));
			message.setText(request.getParameter("text"));
			message.setCategory(request.getParameter("category"));
			message.setUserId(user.getId());
			userid = user.getId();

			new MessageService().register(message);

			session.setAttribute("contributionnameid", userid);
			response.sendRedirect("./");
		} else {
			session.setAttribute("title", title);
			session.setAttribute("text", text);
			session.setAttribute("category", category);
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("post");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if (StringUtils.isEmpty(title) == true) {
			messages.add("・件名を入力してください");
		} else if (title.length() > 50) {
			messages.add("・件名は50文字以下です");
		}

		if (StringUtils.isEmpty(text) == true) {
			messages.add("・本文を入力してください");
		} else if (text.length() > 1000) {
			messages.add("・本文は1000文字以下です");
		}

		if (StringUtils.isEmpty(category) == true) {
			messages.add("・カテゴリーを入力してください");
		} else if (category.length() > 10) {
			messages.add("・カテゴリーは10文字以下です");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}