package io.github.ysmkotm.practicalappstarter.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 部署登録・編集画面の入力値を保持する Form です。
 */
public class DepartmentForm {

	@NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "部署コードを入力してください。")
	@Size(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, max = 20, message = "部署コードは20文字以内で入力してください。")
	private String departmentCode;

	@NotBlank(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "部署名を入力してください。")
	@Size(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, max = 100, message = "部署名は100文字以内で入力してください。")
	private String departmentName;

	@NotNull(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, message = "表示順を入力してください。")
	@Min(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}, value = 0, message = "表示順は0以上の整数で入力してください。")
	private Integer displayOrder;

	public DepartmentForm() {
		this.displayOrder = 0;
	}

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

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	private static String trimToNull(String value) {
		if (value == null) {
			return null;
		}
		String trimmed = value.trim();
		return trimmed.isEmpty() ? null : trimmed;
	}
}
