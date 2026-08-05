package io.github.ysmkotm.practicalappstarter.form;

import jakarta.validation.constraints.Size;

/**
 * 部署一覧画面の検索条件を保持する Form です。
 */
public class DepartmentSearchForm {

	@Size(max = 20, message = "部署コードは20文字以内で入力してください。")
	private String departmentCode;

	@Size(max = 100, message = "部署名は100文字以内で入力してください。")
	private String departmentName;

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = trimToNull(departmentCode);
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = trimToNull(departmentName);
	}

	private static String trimToNull(String value) {
		if (value == null) {
			return null;
		}
		String trimmed = value.trim();
		return trimmed.isEmpty() ? null : trimmed;
	}
}
