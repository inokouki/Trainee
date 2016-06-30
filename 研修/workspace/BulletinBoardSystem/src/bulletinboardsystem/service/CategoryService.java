package bulletinboardsystem.service;

import static bulletinboardsystem.utils.CloseableUtil.*;
import static bulletinboardsystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinboardsystem.beans.UserMessage;
import bulletinboardsystem.dao.CategoryDao;

public class CategoryService {

	private static final int LIMIT_NUM = 1000;

	public List<UserMessage> getSearchCategory() {
		Connection connection = null;
		try {
			connection = getConnection();

			CategoryDao categoryDao = new CategoryDao();
			List<UserMessage> ret = categoryDao.getCategories(connection, LIMIT_NUM);

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