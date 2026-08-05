package io.github.ysmkotm.practicalappstarter.mapper;

import java.util.List;

import io.github.ysmkotm.practicalappstarter.entity.Department;
import io.github.ysmkotm.practicalappstarter.form.DepartmentSearchForm;

/**
 * 部署マスタの Mapper です。
 */
public interface DepartmentMapper {

	/**
	 * 検索条件に合致する部署一覧を取得します。
	 *
	 * @param form 検索条件
	 * @return 部署一覧
	 */
	List<Department> searchDepartments(DepartmentSearchForm form);

	/**
	 * 未削除の部署を表示順昇順で取得します。
	 *
	 * @return 部署一覧
	 */
	List<Department> findDepartments();

	/**
	 * 部署IDで未削除の部署を1件取得します。
	 *
	 * @param departmentId 部署ID
	 * @return 部署。存在しない、または論理削除済みの場合は {@code null}
	 */
	Department findDepartmentById(Long departmentId);

	/**
	 * 部署IDで部署を1件取得します（論理削除済みを含む）。
	 *
	 * @param departmentId 部署ID
	 * @return 部署。存在しない場合は {@code null}
	 */
	Department findDepartmentByIdIncludingDeleted(Long departmentId);
}
