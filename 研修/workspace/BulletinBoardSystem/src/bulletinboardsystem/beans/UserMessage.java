package bulletinboardsystem.beans;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String text;
	private String category;
	private String user_id;
	private Date created;
	private Date modified;
	private String name;
	private int branchid;
	private int departmentid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUserId() {
		return user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
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

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;;
	}

	public int getBranchId(){
		return branchid;
	}

	public void setBranchId(int branchid){
		this.branchid = branchid;;
	}

	public int getDepartmentId(){
		return departmentid;
	}

	public void setDepartmentId(int departmentid){
		this.departmentid = departmentid;;
	}
}