package bulletinboardsystem.service;

import static bulletinboardsystem.utils.CloseableUtil.*;
import static bulletinboardsystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinboardsystem.beans.User;
import bulletinboardsystem.dao.EditUserDao;
import bulletinboardsystem.utils.CipherUtil;

public class EditService {

	public void editer(int editid, String loginid, String password,
			String name, String branchid, String departmentid, User edituser) {

		Connection connection = null;
		try {
			connection = getConnection();

			if (password != null) {
				String encPassword = CipherUtil.encrypt(edituser.getPassword());
				password = encPassword;
			}

			EditUserDao edituserDao = new EditUserDao();

			edituserDao.update(connection, editid, loginid, password, name, branchid, departmentid);

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

	public List<User> getEditUser(int editid) {

		Connection connection = null;
		try {
			connection = getConnection();

			EditUserDao userDao = new EditUserDao();
			List<User> ret = userDao.getUserEdits(connection, editid);

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
}