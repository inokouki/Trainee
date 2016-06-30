package springboard;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import springboard.bean.User;
import springboard.form.PostForm;
import springboard.form.SigninForm;

@Component
@Controller
public class PostController {

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String showPage(Model model, PostForm form, @ModelAttribute SigninForm loginuser) {

		form = new PostForm();
		model.addAttribute("PostForm", form);

		return "post";
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String doContribution(@ModelAttribute("PostForm") PostForm form, Model model,
			@RequestParam("title") String title, @RequestParam("text") String text,
			@RequestParam("category") String category, @ModelAttribute("SigninForm") SigninForm loginuser) {

//		User loginUser = (User) model("loginUser");

		System.out.println("loginID:" + loginuser.getLoginId());

		System.out.println("title:" + title);
		System.out.println("text:" + text);
		System.out.println("category:" + category);

		form.setTitle(title);
		form.setText(text);
		form.setCategory(category);

		form = new PostForm();
		model.addAttribute("PostForm", form);

		return "redirect:/home";
	}

}