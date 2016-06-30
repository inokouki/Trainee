package springboard;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@Controller
public class AdminController {

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String showMessage(Model model) {
		model.addAttribute("message", "hello world!!");
		return "admin";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String showAccount(Model model) {
		model.addAttribute("message", "hello world!!");
		return "admin";
	}
}