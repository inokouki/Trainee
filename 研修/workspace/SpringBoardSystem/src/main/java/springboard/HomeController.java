package springboard;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springboard.form.SigninForm;
import springboard.service.HomeService;

@Component
@Controller
public class HomeController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showMessage(Model model, @ModelAttribute("SigninForm") SigninForm loginuser,
			RedirectAttributes attributes, HttpSession session) {

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> loginUserAccount =
			(List<Map<String, Object>>) session.getAttribute("loginUser");

		System.out.println("[home]session:" + loginUserAccount);

		loginuser = new SigninForm();
		model.addAttribute("SigninForm", loginuser);

		System.out.println("[home]loginuser:" + loginuser);
		System.out.println("[home]model-before:" + model);
		System.out.println("home-id:" + loginuser.getDepartmentId());
		model.addAttribute("loginUser", model);

		List<Map<String, Object>> contributions = HomeService.getContribution(jdbcTemplate);

		model.addAttribute("contributions", contributions);

		System.out.println("[home]model-after:" + model);

		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String doComment(Model model, @ModelAttribute("SigninForm") SigninForm loginuser) {

		model.addAttribute("loginUser", loginuser);
		return "redirect:/home";
	}
}