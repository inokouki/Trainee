package springboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class HomeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static List<Map<String, Object>> getContribution(JdbcTemplate jdbcTemplate) {

		String sql = "SELECT * FROM sbs.contribution_user";

		List<Map<String, Object>> lists = jdbcTemplate.queryForList(sql);

		return lists;
	}
}