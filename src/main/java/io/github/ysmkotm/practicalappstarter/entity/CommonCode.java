package io.github.ysmkotm.practicalappstarter.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 共通コードマスタ（common_code）に対応する Entity です。
 */
@Getter
@Setter
public class CommonCode {

	private Long commonCodeId;
	private String codeType;
	private String code;
	private String codeName;
	private Integer displayOrder;
	private Boolean deletedFlg;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
