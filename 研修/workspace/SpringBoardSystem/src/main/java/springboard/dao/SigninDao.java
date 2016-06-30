package springboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SigninDao {

	@Autowired
    static JdbcTemplate jdbcTemplate;

	public static List<Map<String, Object>> search (JdbcTemplate jdbcTemplate, String loginid, String password) {

		String sql = "SELECT * FROM sbs.users WHERE login_id = \"" + loginid + "\" AND password = \""
				+ password + "\"";

		List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);

		return users;
	}
}