package io.github.ysmkotm.practicalappstarter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.ysmkotm.practicalappstarter.entity.Department;
import io.github.ysmkotm.practicalappstarter.form.DepartmentSearchForm;
import io.github.ysmkotm.practicalappstarter.mapper.DepartmentMapper;

/**
 * 部署管理の Service です。
 */
@Service
public class DepartmentService {

	private final DepartmentMapper departmentMapper;

	public DepartmentService(DepartmentMapper departmentMapper) {
		this.departmentMapper = departmentMapper;
	}

	/**
	 * 検索条件に合致する部署一覧を取得します。
	 *
	 * @param form 検索条件
	 * @return 部署一覧
	 */
	public List<Department> searchDepartments(DepartmentSearchForm form) {
		return departmentMapper.searchDepartments(form);
	}
}
