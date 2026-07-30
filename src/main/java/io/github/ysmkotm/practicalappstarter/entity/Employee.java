package io.github.ysmkotm.practicalappstarter.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 社員マスタ（employee）に対応する Entity です。
 */
@Getter
@Setter
public class Employee {

	private Long employeeId;
	private String employeeCode;
	private String employeeName;
	private String employeeNameKana;
	private String email;
	private String phoneNumber;
	private Long departmentId;
	private String position;
	private LocalDate hireDate;
	private String statusCode;
	private Boolean remoteWorkFlg;
	private String remarks;
	private Boolean deletedFlg;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
