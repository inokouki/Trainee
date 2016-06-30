package bulletinboardsystemfiltertest.service;

import static bulletinboardsystemfiltertest.utils.CloseableUtil.*;
import static bulletinboardsystemfiltertest.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinboardsystemfiltertest.beans.Branch;
import bulletinboardsystemfiltertest.dao.BranchDao;

public class BranchService {

	private static final int LIMIT_NUM = 1000;

	public List<Branch> getBranch() {
		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			List<Branch> ret = branchDao.getBranches(connection, LIMIT_NUM);

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