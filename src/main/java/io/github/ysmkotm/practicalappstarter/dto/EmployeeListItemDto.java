package io.github.ysmkotm.practicalappstarter.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 社員一覧画面の1行分の表示データを保持する DTO です。
 */
@Getter
@Setter
public class EmployeeListItemDto {

	private Long employeeId;
	private String employeeCode;
	private String employeeName;
	private String departmentName;
	private String email;
	private String statusName;
}
