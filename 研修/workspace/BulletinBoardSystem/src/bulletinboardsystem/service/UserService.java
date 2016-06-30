package bulletinboardsystem.service;

import static bulletinboardsystem.utils.CloseableUtil.*;
import static bulletinboardsystem.utils.DBUtil.*;

import java.sql.Connection;

import bulletinboardsystem.beans.User;
import bulletinboardsystem.dao.UserDao;
import bulletinboardsystem.utils.CipherUtil;

public class UserService {

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();

			userDao.insert(connection, user);

			commit(connection);
		} catch (RuntimeException e){
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.update(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public User getUser(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}