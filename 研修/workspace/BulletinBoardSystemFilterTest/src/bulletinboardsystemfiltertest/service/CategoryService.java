package bulletinboardsystemfiltertest.service;

import static bulletinboardsystemfiltertest.utils.CloseableUtil.*;
import static bulletinboardsystemfiltertest.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinboardsystemfiltertest.beans.UserMessage;
import bulletinboardsystemfiltertest.dao.CategoryDao;

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