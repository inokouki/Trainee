package bulletinboardsystem.dao;

import static bulletinboardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletinboardsystem.beans.UserMessage;
import bulletinboardsystem.exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT * FROM kadai4.contribution_user ");
			sql.append("ORDER BY created DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserMessage> toUserMessageList(ResultSet rs) throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String user_id = rs.getString("users_id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp created = rs.getTimestamp("created");
				Timestamp modified = rs.getTimestamp("modified");
				int branchid = rs.getInt("branch_id");
				int departmentid = rs.getInt("department_id");

				UserMessage message = new UserMessage();

				message.setId(id);
				message.setUserId(user_id);
				message.setName(name);
				message.setTitle(title);
				message.setText(text);
				message.setCategory(category);
				message.setCreated(created);
				message.setModified(modified);
				message.setBranchId(branchid);
				message.setDepartmentId(departmentid);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}