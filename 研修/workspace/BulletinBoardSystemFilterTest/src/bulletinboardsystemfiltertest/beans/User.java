package bulletinboardsystemfiltertest.beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginid;
	private String password;
	private String name;
	private String branchid;
	private String departmentid;
	private int available;
	private Date created;
	private Date modified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginId() {
		return loginid;
	}

	public void setLoginId(String loginid) {
		this.loginid = loginid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranchId() {
		return branchid;
	}

	public void setBranchId (String branchid) {
		this.branchid = branchid;
	}

	public String getDepartmentId() {
		return departmentid;
	}

	public void setDepartmentId (String departmentid) {
		this.departmentid = departmentid;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}