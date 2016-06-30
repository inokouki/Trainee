package springboard;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springboard.form.SigninForm;
import springboard.service.SigninService;

@Controller
public class SigninController {

	private int misscounter = 0;

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String showSigninPage(SigninForm form, Model model) {

		form = new SigninForm();
		model.addAttribute("SigninForm", form);

		return "signin";
	}

	@Autowired
    JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String doSignin(Model model, @ModelAttribute("SigninForm") SigninForm form,
			@RequestParam String loginId, @RequestParam String password,
			RedirectAttributes attributes, HttpSession session) {

		List<Map<String, Object>> user = SigninService.getUser(jdbcTemplate, loginId, password);

		System.out.println("[Signin]user:" + user);

		if (user.size() == 0) {

			if (misscounter == 0) {
				misscounter = 1;
				String message = "ログインできません(あと3回)";
				model.addAttribute("errorMessages", message);
			} else if (misscounter == 1) {
				misscounter = 2;
				String message = "ログインできません(あと2回)";
				model.addAttribute("errorMessages", message);
			} else if (misscounter == 2) {
				misscounter = 3;
				String message = "ログインできません(あと1回)";
				model.addAttribute("errorMessages", message);
			} else if (misscounter == 3) {
				String message = "die";
				model.addAttribute("errorMessage", message);
				misscounter = 0;
			}

			return "signin";
		} else {
			misscounter = 0;
			model.addAttribute("loginUser", user);
			attributes.addFlashAttribute("loginUser", user);
			session.setAttribute("loginUser", user);
			System.out.println("[Signin]session:" + session.getAttribute("loginUser"));
			return "redirect:/home";
		}
	}
}