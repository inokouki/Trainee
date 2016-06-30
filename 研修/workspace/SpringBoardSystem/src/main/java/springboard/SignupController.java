package springboard;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springboard.form.SignupForm;
import springboard.service.SignupService;

@Controller
public class SignupController {

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showMessage(SignupForm form, Model model) {

		form = new SignupForm();

	    model.addAttribute("SignupForm", form);

	    return "signup";
	}

	@Autowired
	SignupService SignupService;

	@Autowired
    JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String insertAccount(@ModelAttribute("SignupForm") @Valid SignupForm form, BindingResult error, Model model,
			@RequestParam("loginId") String loginid, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("branchId") String branchid,
			@RequestParam("departmentId") String departmentid) {

		form.setLoginId(loginid);
		form.setPassword(password);
		form.setName(name);
		form.setBranchId(Integer.parseInt(branchid));
		form.setDepartmentId(Integer.parseInt(departmentid));

		System.out.println("error:" + error.hasErrors());

		if (error.hasErrors() == false) {

		    springboard.service.SignupService.register(form, jdbcTemplate);

		    return "redirect:/admin";
		} else {
			List<ObjectError> errorList = error.getAllErrors();
			System.out.println("errorList:" + errorList);
			model.addAttribute("errors", errorList);
			return "signup";
		}
	}
}