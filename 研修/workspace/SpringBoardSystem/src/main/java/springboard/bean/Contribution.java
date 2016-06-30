package springboard.bean;

import java.util.Date;

public class Contribution {

	private int id;
	private int users_id;
	private String users_name;
    private String title;
    private String text;
    private String category;
    private Date created;
    private Date modified;
    private int branch_id;
    private int department_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return users_id;
	}

	public void setUserId(int users_id) {
		this.users_id = users_id;
	}

	public String getUserName() {
		return users_name;
	}

	public void setUserName(String users_name) {
		this.users_name = users_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public int getBranchId() {
		return branch_id;
	}

	public void setBranchId(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getDepartmentId() {
		return department_id;
	}

	public void setDepartmentId(int department_id) {
		this.department_id = department_id;
	}
}