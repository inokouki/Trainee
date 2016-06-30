package bulletinboardsystem.beans;

import java.io.Serializable;
import java.util.Date;

public class EditUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginid;
	private String password;
	private String name;
	private int branchid;
	private int departmentid;
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

	public int getBranch_Id() {
		return branchid;
	}

	public void setBranchId (int branchid) {
		this.branchid = branchid;
	}

	public int getDepartmentId() {
		return departmentid;
	}

	public void setDepartmentId (int departmentid) {
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