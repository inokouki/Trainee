package bulletinboardsystemfiltertest.service;

import static bulletinboardsystemfiltertest.utils.CloseableUtil.*;
import static bulletinboardsystemfiltertest.utils.DBUtil.*;

import java.sql.Connection;

import bulletinboardsystemfiltertest.beans.User;
import bulletinboardsystemfiltertest.dao.UserDao;
import bulletinboardsystemfiltertest.utils.CipherUtil;

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