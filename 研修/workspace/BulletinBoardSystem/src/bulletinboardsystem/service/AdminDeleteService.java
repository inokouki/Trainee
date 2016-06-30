package bulletinboardsystem.service;

import static bulletinboardsystem.utils.CloseableUtil.*;
import static bulletinboardsystem.utils.DBUtil.*;

import java.sql.Connection;

import bulletinboardsystem.dao.AdminDeleteDao;

public class AdminDeleteService {

	private static final int LIMIT_NUM = 1000;

	public static boolean getCheck(String conname, int loginbranchid) {
		Connection connection = null;
		try {
			connection = getConnection();

			AdminDeleteDao getUserDao = new AdminDeleteDao();

			return getUserDao.getUsers(connection, LIMIT_NUM, conname, loginbranchid);
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