package jp.co.kenshu.form.employee;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import jp.co.kenshu.validator.annotation.Memo;

public class EmployeeListForm {
	@NotEmpty(message = "名前は必須です")
    private String name;
	@NotNull
	@Min(value = 18, message = "{value}以上の値を設定してください")
    private Integer age;
	@Memo
    private String memo;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}