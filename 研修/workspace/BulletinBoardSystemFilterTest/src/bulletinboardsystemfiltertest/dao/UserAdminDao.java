package bulletinboardsystemfiltertest.dao;

import static bulletinboardsystemfiltertest.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletinboardsystemfiltertest.beans.User;
import bulletinboardsystemfiltertest.exception.SQLRuntimeException;

public class UserAdminDao {

	public List<User> getUserAdmins(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM kadai4.users ");
			sql.append("ORDER BY id ASC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserAdminList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserAdminList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String branchid = rs.getString("branch_id");
				String departmentid = rs.getString("department_id");
				int available = rs.getInt("available");

				Timestamp created = rs.getTimestamp("created");
				Timestamp modified = rs.getTimestamp("modified");

				User message = new User();

				message.setId(id);
				message.setLoginId(login_id);
				message.setPassword(password);
				message.setName(name);
				message.setBranchId(branchid);
				message.setDepartmentId(departmentid);
				message.setAvailable(available);
				message.setCreated(created);
				message.setModified(modified);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}