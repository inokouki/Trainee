package bulletinboardsystemfiltertest.service;

import static bulletinboardsystemfiltertest.utils.CloseableUtil.*;
import static bulletinboardsystemfiltertest.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinboardsystemfiltertest.beans.Message;
import bulletinboardsystemfiltertest.beans.UserMessage;
import bulletinboardsystemfiltertest.dao.MessageDao;
import bulletinboardsystemfiltertest.dao.UserMessageDao;

public class MessageService {

	public void register(Message message) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();

			messageDao.insert(connection, message);

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

	private static final int LIMIT_NUM = 1000;

	public List<UserMessage> getMessage() {
		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao messageDao = new UserMessageDao();
			List<UserMessage> ret = messageDao.getUserMessages(connection, LIMIT_NUM);

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