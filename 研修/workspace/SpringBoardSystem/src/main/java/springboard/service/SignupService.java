package springboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import springboard.dao.SignupDao;
import springboard.form.SignupForm;

@Service
public class SignupService {

	@Autowired
	SignupDao SignupDao;

	public static void register (SignupForm form, JdbcTemplate jdbcTemplate) {

//		String password = form.getPassword();
//		System.out.println("service-pass:" + password);
//
//		String encrypted = passwordEncoder().encode(password);
//		System.out.println("service-after:" + encrypted);
//
//		form.setPassword(encrypted);

		springboard.dao.SignupDao.insert(form, jdbcTemplate);
	}

}