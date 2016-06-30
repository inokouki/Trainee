package chapter5.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	static {
		String driver = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static Connection getConnection() throws SQLException {

		String url = "jdbc:mysql://localhost/";
		String user = "root";
		String password = "1qazxsw2";
		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		return connection;
	}

	public List<Book> selectAll() throws SQLException {

		Connection connection = getConnection();
		String sql = "SELECT * FROM test1.book ORDER BY id";
		PreparedStatement statement = connection.prepareStatement(sql);

		List<Book> ret = new ArrayList<Book>();

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt("id"));
			book.setAuthorId(rs.getInt("author_id"));
			book.setTitle(rs.getString("title"));

			ret.add(book);
		}

		rs.close();
		statement.close();
		connection.close();

		return ret;
	}

	public int insert(Book book) throws SQLException {

		Connection connection = getConnection();

		String getIdSql = "SELECT NEXT VALUE FOR my_seq " + "FROM information_schema.system_tables "
				+ "WHERE table_name = 'SYSTEM_TABLES'";
		PreparedStatement statement = connection.prepareStatement(getIdSql);
		ResultSet rs = statement.executeQuery();
		rs.next();
		int id = rs.getInt(1);
		rs.close();
		statement.close();

		book.setId(id);

		String sql = "INSERT INTO book (id, author_id, title) VALUES (?, ?, ?)";
		statement = connection.prepareStatement(sql);

		statement.setInt(1, id);
		statement.setInt(2, book.getAuthorId());
		statement.setString(3, book.getTitle());

		int updateCount = statement.executeUpdate();

		statement.close();
		connection.commit();
		connection.close();

		return updateCount;
	}
}