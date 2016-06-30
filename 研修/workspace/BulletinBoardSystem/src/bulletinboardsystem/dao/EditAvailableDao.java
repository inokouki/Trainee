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

public class EditAvailableDao {
	private int available;
	private int updateflag;

	public List<User> editUserAvailable(Connection connection, int num, int id) {

		PreparedStatement ps = null;
		PreparedStatement editps = null;
		try {
			StringBuilder selectsql = new StringBuilder();
			selectsql.append("SELECT * FROM kadai4.users ");
			selectsql.append("ORDER BY id ASC limit " + num);

			ps = connection.prepareStatement(selectsql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserEditList(rs, id);

			StringBuilder updatesql = new StringBuilder();

			updatesql.append("UPDATE kadai4.users SET ");
			updatesql.append("available=" + updateflag + " WHERE id=" + id);

			editps = connection.prepareStatement(updatesql.toString());
			@SuppressWarnings("unused")
			int editrs = editps.executeUpdate();

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserEditList(ResultSet rs, int editid) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String branchid = rs.getString("branch_id");
				String departmentid = rs.getString("department_id");
				available = rs.getInt("available");
				Timestamp created = rs.getTimestamp("created");
				Timestamp modified = rs.getTimestamp("modified");

				if(id == editid) {
					if (available == 1) {
						available = 0;
					} else if (available == 0) {
						available = 1;
					}
					updateflag = available;
				}

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