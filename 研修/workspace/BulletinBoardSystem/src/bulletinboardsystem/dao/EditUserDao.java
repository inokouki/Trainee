package bulletinboardsystem.dao;

import static bulletinboardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletinboardsystem.beans.User;
import bulletinboardsystem.exception.SQLRuntimeException;

public class EditUserDao {

	public List<User> getUserEdits(Connection connection, int editid) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM kadai4.users ");
			sql.append("WHERE id=" + editid);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserEditList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserEditList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginid = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String branchid = rs.getString("branch_id");
				String departmentid = rs.getString("department_id");
				int available = rs.getInt("available");
				Timestamp created = rs.getTimestamp("created");
				Timestamp modified = rs.getTimestamp("modified");

				User user = new User();

				user.setId(id);
				user.setLoginId(loginid);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branchid);
				user.setDepartmentId(departmentid);
				user.setAvailable(available);
				user.setCreated(created);
				user.setModified(modified);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void update(Connection connection, int editid, String loginid, String password,
			String name, String branchid, String departmentid) {

		PreparedStatement ps = null;
		boolean nullflag = false;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE kadai4.users SET ");
			sql.append("login_id=\"" + loginid + "\"");

			if (password == "") {
				nullflag = false;
			} else {
				nullflag = true;
			}

			if (nullflag == true) {
				sql.append(", password=\"" + password + "\"");
			}

			sql.append(", name=\"" + name + "\"");
			sql.append(", branch_id=" + branchid);
			sql.append(", department_id=" + departmentid);
			sql.append(", modified=CURRENT_TIMESTAMP");
			sql.append(" WHERE id=" + editid);

			ps = connection.prepareStatement(sql.toString());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}