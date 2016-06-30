package springboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import springboard.form.SignupForm;

@Component
public class SignupDao {

	@Autowired
    static JdbcTemplate jdbcTemplate;

	public static void insert (SignupForm form, JdbcTemplate jdbcTemplate) {

		String loginid = form.getLoginId();
		String password = form.getPassword();
		String name = form.getName();
		int branch_id = form.getBranchId();
		int department_id = form.getDepartmentId();

		System.out.println("[Dao]loginid:" + loginid);
		System.out.println("[Dao]password:" + password);
		System.out.println("[Dao]name:" + name);
		System.out.println("[Dao]branch_id:" + branch_id);
		System.out.println("[Dao]department_id:" + department_id);

		String sql = "INSERT INTO sbs.users (login_id, password, name, branch_id, department_id, available,"
				+ " created, modified) "
				+ "VALUES (\"" + loginid + "\", \"" + password + "\", \"" + name + "\", " + branch_id
				+ ", " + department_id + ", 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

		System.out.println(sql);

		jdbcTemplate.execute(sql);
	}
}