package bulletinboardsystem.service;

import static bulletinboardsystem.utils.CloseableUtil.*;
import static bulletinboardsystem.utils.DBUtil.*;

import java.sql.Connection;

import bulletinboardsystem.beans.User;
import bulletinboardsystem.dao.UserDao;
import bulletinboardsystem.utils.CipherUtil;

public class LoginService {

	public User login(String loginid, String password) {

		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);



			User user = userDao.getUser(connection, loginid, encPassword);

			commit(connection);
			return user;
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