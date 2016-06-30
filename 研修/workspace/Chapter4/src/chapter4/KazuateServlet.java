package chapter4;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kazuate")
public class KazuateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int answerNum;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Map<String, String[]> parameterMap = request.getParameterMap();

		if(isNewGame(parameterMap) == true) {
			processNewGame(request);
		} else {
			processGuess(request);
		}
	}

	private void processGuess(HttpServletRequest request) {

		int num = Integer.parseInt(request.getParameter("num"));
		String message;
		if(answerNum == num) {
			message = "正解です! ";
		} else if(answerNum < num) {
			message = "ハズレ! " + num + "は正解より大きい値です。";
		} else {
			message = "ハズレ! " + num + "は正解より小さい値です。";
		}
		request.setAttribute("message", message);
	}

	private void processNewGame(HttpServletRequest request) {
		answerNum = StrictMath.abs(new Random(System.currentTimeMillis()).nextInt() % 100 );
	}

	private boolean isNewGame(Map<String, String[]> parameterMap) {
		return parameterMap.containsKey("newGame");
	}
}