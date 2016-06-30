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
import bulletinboardsystemfiltertest.exception.NoRowsUpdatedRuntimeException;
import bulletinboardsystemfiltertest.exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String loginid, String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM kadai4.users WHERE login_id = ? AND password = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, loginid);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginid = rs.getString("login_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String branchid = rs.getString("branch_id");
				String departmentid = rs.getString("department_id");
				int available = rs.getInt("available");
				Timestamp created = rs.getTimestamp("created");
				Timestamp modified = rs.getTimestamp("modified");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginid);
				user.setName(name);
				user.setPassword(password);
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

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO kadai4.users ( ");
			sql.append("login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", department_id");

			sql.append(", available");
			sql.append(", created");
			sql.append(", modified");
			sql.append(") VALUES (");
			sql.append("?");  // login_id
			sql.append(", ?");  // password
			sql.append(", ?");  // name
			sql.append(", ?");  // branch_id
			sql.append(", ?");  // department_id
			sql.append(", 1");  // available
			sql.append(", CURRENT_TIMESTAMP"); // created
			sql.append(", CURRENT_TIMESTAMP"); // modified
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getBranchId());
			ps.setString(5, user.getDepartmentId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE kadai4.users SET");
			sql.append(" login_id = ?");
			sql.append(", name = ?");
			sql.append(", password = ?");
			sql.append(", description = ?");
			sql.append(", update_date = CURRENT_TIMESTAMP");

			sql.append(" WHERE");
			sql.append(" id = ?");
			sql.append(" AND");
			sql.append(" update_date = ?");
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getName());
			ps.setString(4, user.getPassword());
			ps.setInt(6, user.getId());
			ps.setTimestamp(7, new Timestamp(user.getCreated().getTime()));

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e){
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void delete(Connection connection, int deleteid) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM kadai4.users");
			sql.append(" WHERE");
			sql.append(" id = " + deleteid);

			ps = connection.prepareStatement(sql.toString());

			ps.executeUpdate();
		} catch (SQLException e){
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}