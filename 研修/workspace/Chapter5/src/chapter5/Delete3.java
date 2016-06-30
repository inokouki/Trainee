package chapter5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete3 {
	public static void main(String[] args) throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/";
		String user = "root";
		String password = "1qazxsw2";

		Class.forName(driver);

		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		String deleteId = "1 OR id=id";

		delete(connection, deleteId);

		Select.select(connection);

		connection.commit();
		connection.close();
	}

	public static void delete(Connection connection, String deleteId) throws SQLException {
		Statement statement = connection.createStatement();

		String sql = "DELETE FROM test1.author WHERE id=" + deleteId;
		int updateCount = statement.executeUpdate(sql);

		System.out.println("SELECTの結果(ここから)");

		if(updateCount == 1) {
			System.out.println("削除成功");
		} else {
			System.out.println("削除失敗");
		}

		statement.close();
	}
}