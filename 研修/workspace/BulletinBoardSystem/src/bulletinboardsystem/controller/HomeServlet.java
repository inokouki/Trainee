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

import org.apache.commons.lang.StringUtils;

import bulletinboardsystem.beans.Comment;
import bulletinboardsystem.beans.SearchMessage;
import bulletinboardsystem.beans.User;
import bulletinboardsystem.beans.UserComment;
import bulletinboardsystem.beans.UserMessage;
import bulletinboardsystem.service.AdminDeleteService;
import bulletinboardsystem.service.CategoryService;
import bulletinboardsystem.service.CommentService;
import bulletinboardsystem.service.HomeService;
import bulletinboardsystem.service.MessageSearchService;
import bulletinboardsystem.service.MessageService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String searchCategory=null, searchTimeBefore = null, searchTimeAfter = null, deletestr = null;
	private static String conname = null;
	int loginbranchid;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		searchCategory = request.getParameter("searchCategory");
		deletestr = request.getParameter("delete");

		String searchTimeBeforeYear = request.getParameter("searchTimeBeforeYear");
		String searchTimeBeforeMonth = request.getParameter("searchTimeBeforeMonth");
		String searchTimeBeforeDay = request.getParameter("searchTimeBeforeDay");
		String searchTimeAfterYear = request.getParameter("searchTimeAfterYear");
		String searchTimeAfterMonth = request.getParameter("searchTimeAfterMonth");
		String searchTimeAfterDay = request.getParameter("searchTimeAfterDay");

		if ( !StringUtils.isEmpty(searchTimeBeforeYear) && !StringUtils.isEmpty(searchTimeBeforeMonth) &&
				!StringUtils.isEmpty(searchTimeBeforeDay) ) {
			searchTimeBefore = searchTimeBeforeYear + "-" + searchTimeBeforeMonth + "-" +
					searchTimeBeforeDay;

		} else if ( !(StringUtils.isEmpty(searchTimeBeforeYear) && StringUtils.isEmpty(searchTimeBeforeMonth) &&
				StringUtils.isEmpty(searchTimeBeforeDay))) {
			List<String> searchmessages = new ArrayList<String>();
			searchmessages.add("・入力は\"年月日\"全て選択してください");
			request.setAttribute("errorMessages", searchmessages);

		} else {
			searchTimeBefore = "";
		}

		if ( !StringUtils.isEmpty(searchTimeAfterYear) && !StringUtils.isEmpty(searchTimeAfterMonth) &&
				!StringUtils.isEmpty(searchTimeAfterDay) ) {
			searchTimeAfter = searchTimeAfterYear + "-" + searchTimeAfterMonth + "-" +
					searchTimeAfterDay;

		} else if ( !(StringUtils.isEmpty(searchTimeAfterYear) && StringUtils.isEmpty(searchTimeAfterMonth) &&
				StringUtils.isEmpty(searchTimeAfterDay))) {
			List<String> searchmessages = new ArrayList<String>();
			searchmessages.add("・入力は\"年月日\"全て選択してください");
			request.setAttribute("errorMessages", searchmessages);

		} else {
			searchTimeAfter = "";
		}

		if ( !StringUtils.isEmpty(searchTimeBefore) && !StringUtils.isEmpty(searchTimeAfter)) {
			int beforedate, afterdate;
			List<String> searchmessages = new ArrayList<String>();

			beforedate = Integer.parseInt(searchTimeBeforeYear + searchTimeBeforeMonth + searchTimeBeforeDay);
			afterdate = Integer.parseInt(searchTimeAfterYear + searchTimeAfterMonth + searchTimeAfterDay);

			if (afterdate - beforedate < 0) {
				searchmessages.add("・日付検索に誤りがあります。正しく選択してください。");
				request.setAttribute("errorMessages", searchmessages);
			}

		}

		User loginuser = (User) request.getSession().getAttribute("loginUser");

		List<UserMessage> contributions = new MessageService().getMessage();
		List<UserComment> comments = new CommentService().getComment();
		List<User> bracons = new HomeService().getUser();
		List<UserMessage> concategories = new CategoryService().getSearchCategory();

		if (searchCategory != null || searchTimeBefore != null || searchTimeAfter != null) {
			List<SearchMessage> searches = new MessageSearchService().getMessage(searchCategory,
					searchTimeBefore, searchTimeAfter);

			searchTimeBeforeYear = request.getParameter("searchTimeBeforeYear");
			searchTimeBeforeMonth = request.getParameter("searchTimeBeforeMonth");
			searchTimeBeforeDay = request.getParameter("searchTimeBeforeDay");
			searchTimeAfterYear = request.getParameter("searchTimeAfterYear");
			searchTimeAfterMonth = request.getParameter("searchTimeAfterMonth");
			searchTimeAfterDay = request.getParameter("searchTimeAfterDay");

			request.setAttribute("searches", searches);

			if (searches.isEmpty()) {
				List<String> searchmessages = new ArrayList<String>();
				searchmessages.add("検索した結果、該当する記事は0件でした。");
				request.setAttribute("errorMessages", searchmessages);
			}

			request.setAttribute("searchTimeBeforeYear", searchTimeBeforeYear);
			request.setAttribute("searchTimeBeforeMonth", searchTimeBeforeMonth);
			request.setAttribute("searchTimeBeforeDay", searchTimeBeforeDay);
			request.setAttribute("searchTimeAfterYear", searchTimeAfterYear);
			request.setAttribute("searchTimeAfterMonth", searchTimeAfterMonth);
			request.setAttribute("searchTimeAfterDay", searchTimeAfterDay);
		}

		request.setAttribute("searchCategory", searchCategory);
		request.setAttribute("categories", concategories);
		request.setAttribute("contributions", contributions);
		request.setAttribute("comments", comments);
		request.setAttribute("loginUser", loginuser);
		request.setAttribute("bracons", bracons);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		deletestr = request.getParameter("delete");
		conname = request.getParameter("conname");

		if (request.getParameter("loginbranchid") != null) {
			loginbranchid = Integer.parseInt(request.getParameter("loginbranchid"));
		}

		List<String> comments = new ArrayList<String>();

		if (deletestr == null) {
			if (isValid(request, comments) == true) {
				User user = (User)session.getAttribute("loginUser");

				Comment comment = new Comment();

				comment.setComment(request.getParameter("comment"));
				comment.setContributionId(request.getParameter("contribution_id"));
				comment.setUserId(user.getId());

				new CommentService().register(comment);
			} else {
				session.setAttribute("errorMessages", comments);
			}
		} else {
			int deleteid = Integer.parseInt(deletestr);

			System.out.println(deleteid);

			if (StringUtils.length(conname) == 0) {
				CommentService.deleteUser(deleteid);
			} else {
				boolean admincheck = AdminDeleteService.getCheck(conname, loginbranchid);

				System.out.println(admincheck);

				if (admincheck == true) {
					CommentService.deleteUser(deleteid);
				} else {
					comments.add("・削除権限がありません。");
					session.setAttribute("errorMessages", comments);
				}
			}
		}

		response.sendRedirect("./");
	}

	private boolean isValid(HttpServletRequest request, List<String> comments) {

		String comment = request.getParameter("comment");

		int error = 0;

		if (StringUtils.isEmpty(searchCategory) && StringUtils.isEmpty(searchTimeBefore) &&
				StringUtils.isEmpty(searchTimeAfter)) {

			if (StringUtils.isEmpty(comment) && StringUtils.isEmpty(deletestr) ) {
				comments.add("・コメント文を入力してください");
			} else if (comment.length() > 500) {
				comments.add("・コメント文は500文字以下です");
			}
		}

		if ( (!StringUtils.isEmpty(searchCategory) ||
				(!StringUtils.isEmpty(searchTimeBefore) && StringUtils.isEmpty(searchTimeAfter)))) {
			comments.removeAll(comments);
			error = 1;
		}

		if (comments.size() == 0 && error == 0) {
			return true;
		} else {
			return false;
		}
	}
}