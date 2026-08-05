package io.github.ysmkotm.practicalappstarter.service;

import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import io.github.ysmkotm.practicalappstarter.entity.Department;
import io.github.ysmkotm.practicalappstarter.form.DepartmentForm;
import io.github.ysmkotm.practicalappstarter.form.DepartmentSearchForm;
import io.github.ysmkotm.practicalappstarter.mapper.DepartmentMapper;

/**
 * 部署管理の Service です。
 */
@Service
public class DepartmentService {

	private static final String DUPLICATE_DEPARTMENT_CODE_MESSAGE = "部署コードは既に登録されています。";
	private static final String DB_CONSTRAINT_VIOLATION_MESSAGE = "登録内容が重複しています。入力内容を確認してください。";

	/**
	 * 部署コード UNIQUE 制約の物理名（PostgreSQL が列 UNIQUE から自動命名。
	 * 正本は docs/04_db/table/department.md §4）。
	 */
	private static final String UNIQUE_CONSTRAINT_DEPARTMENT_CODE = "department_department_code_key";

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

	/**
	 * 部署IDで未削除の部署を1件取得します。
	 *
	 * @param departmentId 部署ID
	 * @return 部署。存在しない、または論理削除済みの場合は {@code null}
	 */
	public Department findDepartmentById(Long departmentId) {
		return departmentMapper.findDepartmentById(departmentId);
	}

	/**
	 * 部署 Entity を登録・編集画面用 Form に変換します。
	 *
	 * @param department 部署
	 * @return 部署 Form
	 */
	public DepartmentForm toDepartmentForm(Department department) {
		DepartmentForm form = new DepartmentForm();
		form.setDepartmentCode(department.getDepartmentCode());
		form.setDepartmentName(department.getDepartmentName());
		form.setDisplayOrder(department.getDisplayOrder());
		return form;
	}

	/**
	 * 部署コードの一意性を検証します。
	 *
	 * @param form 入力 Form
	 * @param excludeDepartmentId 除外する部署ID（更新時）。登録時は {@code null}
	 * @param errors バリデーションエラー
	 */
	public void validateUniqueConstraints(DepartmentForm form, Long excludeDepartmentId, Errors errors) {
		if (form.getDepartmentCode() != null
				&& departmentMapper.countByDepartmentCode(form.getDepartmentCode(), excludeDepartmentId) > 0) {
			errors.rejectValue("departmentCode", "duplicate", DUPLICATE_DEPARTMENT_CODE_MESSAGE);
		}
	}

	/**
	 * 部署を登録します。
	 *
	 * @param form 入力 Form
	 * @throws DataIntegrityViolationException DB 制約違反時
	 */
	public void createDepartment(DepartmentForm form) {
		Department department = toDepartmentForCreate(form);
		departmentMapper.insertDepartment(department);
	}

	/**
	 * 部署を更新します。
	 *
	 * @param departmentId 部署ID
	 * @param form 入力 Form
	 * @return 更新件数
	 * @throws DataIntegrityViolationException DB 制約違反時
	 */
	public int updateDepartment(Long departmentId, DepartmentForm form) {
		Department department = toDepartmentForUpdate(departmentId, form);
		return departmentMapper.updateDepartment(department);
	}

	/**
	 * 部署を論理削除します。
	 *
	 * @param departmentId 部署ID
	 * @return 更新件数
	 */
	public int deleteDepartment(Long departmentId) {
		return departmentMapper.logicalDeleteDepartment(departmentId);
	}

	/**
	 * 部署コードの UNIQUE 制約違反を Form エラーへ変換します。
	 * <p>
	 * 制約名が {@code department_department_code_key} と一致する場合のみ変換します。
	 * それ以外の制約違反、または制約名を特定できない場合は {@code false} を返します（呼び出し側で再スローしてください）。
	 *
	 * @param ex DB 制約違反
	 * @param errors バリデーションエラー
	 * @return 画面へ戻す重複エラーとして変換した場合 {@code true}
	 */
	public boolean handleDataIntegrityViolation(DataIntegrityViolationException ex, Errors errors) {
		if (!isDepartmentCodeUniqueViolation(ex)) {
			return false;
		}

		errors.reject("dbConstraintViolation", DB_CONSTRAINT_VIOLATION_MESSAGE);
		return true;
	}

	private boolean isDepartmentCodeUniqueViolation(DataIntegrityViolationException ex) {
		return UNIQUE_CONSTRAINT_DEPARTMENT_CODE.equals(findPostgresConstraintName(ex));
	}

	private String findPostgresConstraintName(Throwable throwable) {
		Throwable current = throwable;
		while (current != null) {
			if (current instanceof PSQLException psqlException) {
				if (psqlException.getServerErrorMessage() == null) {
					return null;
				}
				return psqlException.getServerErrorMessage().getConstraint();
			}
			current = current.getCause();
		}
		return null;
	}

	private Department toDepartmentForCreate(DepartmentForm form) {
		Department department = new Department();
		copyFormFields(form, department);
		return department;
	}

	private Department toDepartmentForUpdate(Long departmentId, DepartmentForm form) {
		Department department = new Department();
		department.setDepartmentId(departmentId);
		copyFormFields(form, department);
		return department;
	}

	private void copyFormFields(DepartmentForm form, Department department) {
		department.setDepartmentCode(form.getDepartmentCode());
		department.setDepartmentName(form.getDepartmentName());
		department.setDisplayOrder(form.getDisplayOrder());
	}
}
