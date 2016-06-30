package bulletinboardsystemfiltertest.service;

import static bulletinboardsystemfiltertest.utils.CloseableUtil.*;
import static bulletinboardsystemfiltertest.utils.DBUtil.*;

import java.sql.Connection;

import bulletinboardsystemfiltertest.dao.AdminDeleteDao;

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