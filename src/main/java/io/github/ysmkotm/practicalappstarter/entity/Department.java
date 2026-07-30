package io.github.ysmkotm.practicalappstarter.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 部署マスタ（department）に対応する Entity です。
 */
@Getter
@Setter
public class Department {

	private Long departmentId;
	private String departmentCode;
	private String departmentName;
	private Integer displayOrder;
	private Boolean deletedFlg;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
