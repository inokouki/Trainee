package bulletinboardsystem.service;

import static bulletinboardsystem.utils.CloseableUtil.*;
import static bulletinboardsystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinboardsystem.beans.Branch;
import bulletinboardsystem.dao.BranchDao;

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