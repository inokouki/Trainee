package bulletinboardsystemfiltertest.dao;

import static bulletinboardsystemfiltertest.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bulletinboardsystemfiltertest.beans.Message;
import bulletinboardsystemfiltertest.exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO kadai4.contributions (");
			sql.append("title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", user_id");
			sql.append(", created");
			sql.append(", modified");
			sql.append(") VALUES (");
			sql.append("?"); // title
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", ?"); // user_id
			sql.append(", CURRENT_TIMESTAMP"); // created
			sql.append(", CURRENT_TIMESTAMP"); // modified
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, message.getTitle());
			ps.setString(2, message.getText());
			ps.setString(3, message.getCategory());
			ps.setInt(4, message.getUserId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}