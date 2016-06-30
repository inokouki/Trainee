package bulletinboardsystem.dao;

import static bulletinboardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bulletinboardsystem.beans.Comment;
import bulletinboardsystem.exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO kadai4.comments (");
			sql.append("text");
			sql.append(", contribution_id");
			sql.append(", user_id");
			sql.append(", created");
			sql.append(", modified");
			sql.append(")");
			sql.append(" VALUES ( ");
			sql.append("?"); // text
			sql.append(", ?"); // contribution_id
			sql.append(", ?"); // user_id
			sql.append(", CURRENT_TIMESTAMP"); // created
			sql.append(", CURRENT_TIMESTAMP"); // modified
			sql.append(" )");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getComment());
			ps.setString(2, comment.getContributionId());
			ps.setInt(3, comment.getUserId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void delete(Connection connection, int deleteid) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM kadai4.contributions");
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