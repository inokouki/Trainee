package springboard.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignupForm {
	private int id;

	@NotNull(message = "ログインIDを入力してください。")
	@Size(min = 6, max = 20, message = "ログインIDは6文字以上、20文字以内です。")
    private String login_id;

	@NotNull(message = "パスワードを入力してください。")
	@Size(min = 6, max = 255, message = "パスワードは6文字以上、255文字以内です。")
    private String password;

	@NotNull(message = "名前を入力してください。")
	@Size(max = 10, message = "名前は10文字以内です。")
    private String name;

	@NotNull(message = "支店を選択してください。")
    private int branch_id;

	@NotNull(message = "部署・役職を選択してください。")
    private int department_id;

    private int available;
    private Date created;
    private Date modified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return login_id;
	}

	public void setLoginId(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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