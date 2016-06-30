package springboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import springboard.dao.HomeDao;

public class HomeService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static List<Map<String, Object>> getContribution(JdbcTemplate jdbcTemplate) {

		List<Map<String, Object>> lists = HomeDao.getContribution(jdbcTemplate);

		return lists;
	}
}