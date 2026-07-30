package io.github.ysmkotm.practicalappstarter.form;

import jakarta.validation.constraints.Size;

/**
 * 社員一覧画面の検索条件を保持する Form です。
 */
public class EmployeeSearchForm {

	@Size(max = 20, message = "社員番号は20文字以内で入力してください。")
	private String employeeCode;

	@Size(max = 100, message = "氏名は100文字以内で入力してください。")
	private String employeeName;

	private Long departmentId;

	private String statusCode;

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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = trimToNull(statusCode);
	}

	private static String trimToNull(String value) {
		if (value == null) {
			return null;
		}
		String trimmed = value.trim();
		return trimmed.isEmpty() ? null : trimmed;
	}
}
