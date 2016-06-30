package chapter5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
	public static void main(String[] args) throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/";
		String user = "root";
		String password = "1qazxsw2";

		Class.forName(driver);

		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		insert(connection);

		Select.select(connection);

		connection.commit();
		connection.close();
	}

	public static void insert(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String sql = "INSERT INTO test1.author (id, name, kana, sex) VALUES (0, '木村', 'きむら', '男')";
		int updateCount = statement.executeUpdate(sql);

		if(updateCount == 1) {
			System.out.println("登録成功");
		} else {
			System.out.println("登録失敗");
		}
		statement.close();
	}
}
