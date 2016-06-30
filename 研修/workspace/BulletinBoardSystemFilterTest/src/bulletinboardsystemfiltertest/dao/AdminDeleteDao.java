package bulletinboardsystemfiltertest.dao;

import static bulletinboardsystemfiltertest.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bulletinboardsystemfiltertest.exception.SQLRuntimeException;

public class AdminDeleteDao {

	public boolean getUsers(Connection connection, int num, String conname, int loginbranchid) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM kadai4.users ");
			sql.append("WHERE login_id = \"" + conname + "\" ");
			sql.append("ORDER BY id ASC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();

			return toUserAdmin(rs, conname, loginbranchid);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private boolean toUserAdmin(ResultSet rs, String conname, int loginbranchid) throws SQLException {

		try {
			while (rs.next()) {

				String loginid = rs.getString("login_id");
				String branchid = rs.getString("branch_id");
				String departmentid = rs.getString("department_id");

				System.out.println("loginid:" + loginid);
				System.out.println("conname:" + conname);
				System.out.println("branchid:" + branchid);
				System.out.println("loginbranchid:" + loginbranchid);
				System.out.println("departmentid:" + departmentid);

				System.out.println("1:" + conname.equals(loginid));
				System.out.println("2:" + String.valueOf(loginbranchid).equals(branchid));
				System.out.println("3:" + (departmentid == "4"));


				if (conname.equals(loginid) && String.valueOf(loginbranchid).equals(branchid) &&
						departmentid.equals("4")) {
					return true;
				}

			}
			return false;
		} finally {
			close(rs);
		}
	}
}