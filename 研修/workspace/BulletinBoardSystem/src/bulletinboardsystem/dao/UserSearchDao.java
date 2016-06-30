package bulletinboardsystem.dao;

import static bulletinboardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bulletinboardsystem.beans.SearchMessage;
import bulletinboardsystem.exception.SQLRuntimeException;

public class UserSearchDao {

	public List<SearchMessage> getUserMessages(Connection connection, int num,
			String searchCategory, String searchTimeBefore, String searchTimeAfter) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM kadai4.contribution_user ");

			if (!StringUtils.isEmpty(searchCategory) && StringUtils.isEmpty(searchTimeBefore) &&
					StringUtils.isEmpty(searchTimeAfter)) { // 1,0,0
				sql.append("WHERE ");
				sql.append("category = \"" + searchCategory + "\" ");

			} else if (StringUtils.isEmpty(searchCategory) && !StringUtils.isEmpty(searchTimeBefore) &&
					!StringUtils.isEmpty(searchTimeAfter)) { // 0,1,1
				sql.append("WHERE created BETWEEN \"");
				sql.append(searchTimeBefore + " 00:00:00\" AND \"" + searchTimeAfter + " 23:59:59\" " );

			} else if (!StringUtils.isEmpty(searchCategory) && !StringUtils.isEmpty(searchTimeBefore) &&
					!StringUtils.isEmpty(searchTimeAfter)) { // 1,1,1
				sql.append("WHERE ");
				sql.append("category = \"" + searchCategory + "\" ");
				sql.append("AND ");
				sql.append("created BETWEEN \"");
				sql.append(searchTimeBefore + " 00:00:00\" AND \"" + searchTimeAfter + " 23:59:59\" " );

			} else if (!StringUtils.isEmpty(searchCategory) && !StringUtils.isEmpty(searchTimeBefore) &&
					StringUtils.isEmpty(searchTimeAfter)) { // 1,1,0
				sql.append("WHERE ");
				sql.append("category = \"" + searchCategory + "\" AND ");
				sql.append("created > \"" + searchTimeBefore + " 00:00:00\" ");

			} else if (!StringUtils.isEmpty(searchCategory) && StringUtils.isEmpty(searchTimeBefore) &&
					!StringUtils.isEmpty(searchTimeAfter)) { // 1,0,1
				sql.append("WHERE ");
				sql.append("category = \"" + searchCategory + "\" AND ");
				sql.append("created < \"" + searchTimeAfter + " 00:00:00\" ");

			} else if (StringUtils.isEmpty(searchCategory) && !StringUtils.isEmpty(searchTimeBefore) &&
					StringUtils.isEmpty(searchTimeAfter)) { // 0,1,0
				sql.append("WHERE ");
				sql.append("created > \"" + searchTimeBefore + " 00:00:00\" ");

			} else if (StringUtils.isEmpty(searchCategory) && StringUtils.isEmpty(searchTimeBefore) &&
					!StringUtils.isEmpty(searchTimeAfter)) { // 0,0,1
				sql.append("WHERE ");
				sql.append("created < \"" + searchTimeAfter + " 00:00:00\" ");

			}

			sql.append("ORDER BY created DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<SearchMessage> ret = toUserMessageList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<SearchMessage> toUserMessageList(ResultSet rs) throws SQLException {

		List<SearchMessage> ret = new ArrayList<SearchMessage>();
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

				SearchMessage message = new SearchMessage();

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