package io.github.ysmkotm.practicalappstarter.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 社員登録・編集画面の入力値を保持する Form です。
 */
public class EmployeeForm {

	@NotBlank(groups = ValidationGroups.Create.class, message = "社員番号を入力してください。")
	@Size(groups = ValidationGroups.Create.class, max = 20, message = "社員番号は20文字以内で入力してください。")
	private String employeeCode;

	@NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "氏名を入力してください。")
	@Size(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, max = 100, message = "氏名は100文字以内で入力してください。")
	private String employeeName;

	@Size(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, max = 100, message = "氏名カナは100文字以内で入力してください。")
	private String employeeNameKana;

	@NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "メールアドレスを入力してください。")
	@Email(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "メールアドレスの形式が正しくありません。")
	@Size(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, max = 255, message = "メールアドレスは255文字以内で入力してください。")
	private String email;

	@Size(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, max = 20, message = "電話番号は20文字以内で入力してください。")
	private String phoneNumber;

	@NotNull(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "部署を選択してください。")
	private Long departmentId;

	@Size(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, max = 100, message = "役職は100文字以内で入力してください。")
	private String position;

	@NotNull(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "入社日を入力してください。")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate hireDate;

	@NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "在籍区分を選択してください。")
	private String statusCode;

	private Boolean remoteWorkFlg;

	private String remarks;

	public EmployeeForm() {
		this.remoteWorkFlg = false;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = trimToNull(employeeCode);
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = trimToNull(employeeName);
	}

	public String getEmployeeNameKana() {
		return employeeNameKana;
	}

	public void setEmployeeNameKana(String employeeNameKana) {
		this.employeeNameKana = trimToNull(employeeNameKana);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = trimToNull(email);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = trimToNull(phoneNumber);
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = trimToNull(position);
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = trimToNull(statusCode);
	}

	public Boolean getRemoteWorkFlg() {
		return remoteWorkFlg;
	}

	public void setRemoteWorkFlg(Boolean remoteWorkFlg) {
		this.remoteWorkFlg = remoteWorkFlg != null && remoteWorkFlg;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = trimToNull(remarks);
	}

	private static String trimToNull(String value) {
		if (value == null) {
			return null;
		}
		String trimmed = value.trim();
		return trimmed.isEmpty() ? null : trimmed;
	}
}
