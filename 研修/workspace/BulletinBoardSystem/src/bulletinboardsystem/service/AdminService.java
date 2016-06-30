package bulletinboardsystem.service;

import static bulletinboardsystem.utils.CloseableUtil.*;
import static bulletinboardsystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinboardsystem.beans.User;
import bulletinboardsystem.dao.EditAvailableDao;
import bulletinboardsystem.dao.UserAdminDao;
import bulletinboardsystem.dao.UserDao;

public class AdminService {

	private static final int LIMIT_NUM = 1000;

	public List<User> getUser() {
		Connection connection = null;
		try {
			connection = getConnection();

			UserAdminDao adminDao = new UserAdminDao();
			List<User> ret = adminDao.getUserAdmins(connection, LIMIT_NUM);

			commit(connection);

			return ret;
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

	public List<User> editAvailableUser(int id) {
		Connection connection = null;
		try {
			connection = getConnection();

			EditAvailableDao adminDao = new EditAvailableDao();
			List<User> ret = adminDao.editUserAvailable(connection, LIMIT_NUM, id);

			commit(connection);

			return ret;
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

	public static void deleteUser(int id) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.delete(connection, id);

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
}