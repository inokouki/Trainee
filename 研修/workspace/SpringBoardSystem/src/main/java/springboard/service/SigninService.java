package springboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import springboard.dao.SigninDao;

@Service
public class SigninService {

	@Autowired
	SigninDao SigninDao;

	public static List<Map<String, Object>> getUser (JdbcTemplate jdbcTemplate, String loginid, String password) {

		List<Map<String, Object>> users = null;

//		String password = form.getPassword();
//		System.out.println("service-pass:" + password);
//
//		String encrypted = passwordEncoder().encode(password);
//		System.out.println("service-after:" + encrypted);
//
//		form.setPassword(encrypted);

		users = springboard.dao.SigninDao.search(jdbcTemplate, loginid, password);

		return users;
	}

}