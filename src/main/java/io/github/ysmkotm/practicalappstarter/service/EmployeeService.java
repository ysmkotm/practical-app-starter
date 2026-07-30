package io.github.ysmkotm.practicalappstarter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import io.github.ysmkotm.practicalappstarter.dto.EmployeeListItemDto;
import io.github.ysmkotm.practicalappstarter.entity.CommonCode;
import io.github.ysmkotm.practicalappstarter.entity.Department;
import io.github.ysmkotm.practicalappstarter.entity.Employee;
import io.github.ysmkotm.practicalappstarter.form.EmployeeForm;
import io.github.ysmkotm.practicalappstarter.form.EmployeeSearchForm;
import io.github.ysmkotm.practicalappstarter.mapper.CommonCodeMapper;
import io.github.ysmkotm.practicalappstarter.mapper.DepartmentMapper;
import io.github.ysmkotm.practicalappstarter.mapper.EmployeeMapper;

/**
 * 社員管理の Service です。
 */
@Service
public class EmployeeService {

	private static final String EMPLOYEE_STATUS_CODE_TYPE = "EMPLOYEE_STATUS";
	private static final String DUPLICATE_EMPLOYEE_CODE_MESSAGE = "社員番号は既に登録されています。";
	private static final String DUPLICATE_EMAIL_MESSAGE = "メールアドレスは既に登録されています。";
	private static final String DB_CONSTRAINT_VIOLATION_MESSAGE = "登録内容が重複しています。入力内容を確認してください。";
	private static final String UNAVAILABLE_DEPARTMENT_MESSAGE = "選択された部署は現在使用できません。";
	private static final String UNAVAILABLE_STATUS_MESSAGE = "選択された在籍区分は現在使用できません。";
	private static final String DELETED_OPTION_SUFFIX = "（削除済み）";

	/**
	 * 社員番号 UNIQUE 制約の物理名（PostgreSQL が列 UNIQUE から自動命名。
	 * 正本は docs/04_db/table/employee.md §4）。
	 */
	private static final String UNIQUE_CONSTRAINT_EMPLOYEE_CODE = "employee_employee_code_key";

	/**
	 * メールアドレス UNIQUE 制約の物理名（PostgreSQL が列 UNIQUE から自動命名。
	 * 正本は docs/04_db/table/employee.md §4）。
	 */
	private static final String UNIQUE_CONSTRAINT_EMAIL = "employee_email_key";

	private final EmployeeMapper employeeMapper;
	private final DepartmentMapper departmentMapper;
	private final CommonCodeMapper commonCodeMapper;

	public EmployeeService(
			EmployeeMapper employeeMapper,
			DepartmentMapper departmentMapper,
			CommonCodeMapper commonCodeMapper) {
		this.employeeMapper = employeeMapper;
		this.departmentMapper = departmentMapper;
		this.commonCodeMapper = commonCodeMapper;
	}

	/**
	 * 検索条件に合致する社員一覧を取得します。
	 *
	 * @param form 検索条件
	 * @return 社員一覧
	 */
	public List<EmployeeListItemDto> searchEmployees(EmployeeSearchForm form) {
		return employeeMapper.searchEmployees(form);
	}

	/**
	 * 未削除の部署一覧を取得します。
	 *
	 * @return 部署一覧
	 */
	public List<Department> findDepartments() {
		return departmentMapper.findDepartments();
	}

	/**
	 * 登録・編集画面用の部署選択肢を取得します。
	 * <p>
	 * 編集時に現在の部署が論理削除済みの場合のみ、名称に「（削除済み）」を付与して選択肢へ補完します。
	 *
	 * @param currentDepartmentId 編集中社員の部署ID。登録時は {@code null}
	 * @return 部署選択肢
	 */
	public List<Department> findDepartmentsForForm(Long currentDepartmentId) {
		List<Department> departments = new ArrayList<>(departmentMapper.findDepartments());
		if (currentDepartmentId == null) {
			return departments;
		}

		boolean contained = departments.stream()
				.anyMatch(department -> Objects.equals(department.getDepartmentId(), currentDepartmentId));
		if (contained) {
			return departments;
		}

		Department current = departmentMapper.findDepartmentByIdIncludingDeleted(currentDepartmentId);
		if (current != null) {
			current.setDepartmentName(current.getDepartmentName() + DELETED_OPTION_SUFFIX);
			departments.add(current);
		}
		return departments;
	}

	/**
	 * 在籍区分の共通コード一覧を取得します。
	 *
	 * @return 在籍区分一覧
	 */
	public List<CommonCode> findEmployeeStatuses() {
		return commonCodeMapper.findByCodeType(EMPLOYEE_STATUS_CODE_TYPE);
	}

	/**
	 * 登録・編集画面用の在籍区分選択肢を取得します。
	 * <p>
	 * 編集時に現在の在籍区分が論理削除済みの場合のみ、名称に「（削除済み）」を付与して選択肢へ補完します。
	 *
	 * @param currentStatusCode 編集中社員の在籍区分コード。登録時は {@code null}
	 * @return 在籍区分選択肢
	 */
	public List<CommonCode> findEmployeeStatusesForForm(String currentStatusCode) {
		List<CommonCode> statuses = new ArrayList<>(commonCodeMapper.findByCodeType(EMPLOYEE_STATUS_CODE_TYPE));
		if (currentStatusCode == null) {
			return statuses;
		}

		boolean contained = statuses.stream()
				.anyMatch(status -> Objects.equals(status.getCode(), currentStatusCode));
		if (contained) {
			return statuses;
		}

		CommonCode current = commonCodeMapper.findByCodeTypeAndCodeIncludingDeleted(
				EMPLOYEE_STATUS_CODE_TYPE, currentStatusCode);
		if (current != null) {
			current.setCodeName(current.getCodeName() + DELETED_OPTION_SUFFIX);
			statuses.add(current);
		}
		return statuses;
	}

	/**
	 * 社員IDで未削除の社員を1件取得します。
	 *
	 * @param employeeId 社員ID
	 * @return 社員。存在しない、または論理削除済みの場合は {@code null}
	 */
	public Employee findEmployeeById(Long employeeId) {
		return employeeMapper.findEmployeeById(employeeId);
	}

	/**
	 * 社員 Entity を登録・編集画面用 Form に変換します。
	 *
	 * @param employee 社員
	 * @return 社員 Form
	 */
	public EmployeeForm toEmployeeForm(Employee employee) {
		EmployeeForm form = new EmployeeForm();
		form.setEmployeeCode(employee.getEmployeeCode());
		form.setEmployeeName(employee.getEmployeeName());
		form.setEmployeeNameKana(employee.getEmployeeNameKana());
		form.setEmail(employee.getEmail());
		form.setPhoneNumber(employee.getPhoneNumber());
		form.setDepartmentId(employee.getDepartmentId());
		form.setPosition(employee.getPosition());
		form.setHireDate(employee.getHireDate());
		form.setStatusCode(employee.getStatusCode());
		form.setRemoteWorkFlg(employee.getRemoteWorkFlg());
		form.setRemarks(employee.getRemarks());
		return form;
	}

	/**
	 * 部署・在籍区分の参照妥当性を検証します。
	 * <p>
	 * 登録時は未削除マスタのみ許可します。更新時は DB 現在値と同一であれば論理削除済みの維持を許可し、
	 * 別の論理削除済みマスタへの変更は許可しません。
	 *
	 * @param form 入力 Form
	 * @param existingEmployee 更新対象の既存社員。登録時は {@code null}
	 * @param errors バリデーションエラー
	 */
	public void validateMasterReferences(EmployeeForm form, Employee existingEmployee, Errors errors) {
		if (form.getDepartmentId() != null && !isAllowedDepartment(form.getDepartmentId(), existingEmployee)) {
			errors.rejectValue("departmentId", "unavailable", UNAVAILABLE_DEPARTMENT_MESSAGE);
		}

		if (form.getStatusCode() != null && !isAllowedStatusCode(form.getStatusCode(), existingEmployee)) {
			errors.rejectValue("statusCode", "unavailable", UNAVAILABLE_STATUS_MESSAGE);
		}
	}

	/**
	 * 社員番号・メールアドレスの一意性を検証します。
	 *
	 * @param form 入力 Form
	 * @param excludeEmployeeId 除外する社員ID（更新時）。登録時は {@code null}
	 * @param errors バリデーションエラー
	 */
	public void validateUniqueConstraints(EmployeeForm form, Long excludeEmployeeId, Errors errors) {
		if (form.getEmployeeCode() != null
				&& employeeMapper.countByEmployeeCode(form.getEmployeeCode(), excludeEmployeeId) > 0) {
			errors.rejectValue("employeeCode", "duplicate", DUPLICATE_EMPLOYEE_CODE_MESSAGE);
		}

		if (form.getEmail() != null
				&& employeeMapper.countByEmail(form.getEmail(), excludeEmployeeId) > 0) {
			errors.rejectValue("email", "duplicate", DUPLICATE_EMAIL_MESSAGE);
		}
	}

	/**
	 * 社員を登録します。
	 *
	 * @param form 入力 Form
	 * @throws DataIntegrityViolationException DB 制約違反時
	 */
	public void createEmployee(EmployeeForm form) {
		Employee employee = toEmployeeForCreate(form);
		employeeMapper.insertEmployee(employee);
	}

	/**
	 * 社員を更新します。
	 * <p>
	 * 社員番号（{@code employee_code}）は更新対象外です。Form に含まれていても Mapper では更新しません。
	 *
	 * @param employeeId 社員ID
	 * @param form 入力 Form
	 * @return 更新件数
	 * @throws DataIntegrityViolationException DB 制約違反時
	 */
	public int updateEmployee(Long employeeId, EmployeeForm form) {
		Employee employee = toEmployeeForUpdate(employeeId, form);
		return employeeMapper.updateEmployee(employee);
	}

	/**
	 * 社員を論理削除します。
	 *
	 * @param employeeId 社員ID
	 * @return 更新件数
	 */
	public int deleteEmployee(Long employeeId) {
		return employeeMapper.logicalDeleteEmployee(employeeId);
	}

	/**
	 * 社員番号またはメールアドレスの UNIQUE 制約違反を Form エラーへ変換します。
	 * <p>
	 * 制約名が {@code employee_employee_code_key} または {@code employee_email_key} と一致する場合のみ変換します。
	 * それ以外の制約違反、または制約名を特定できない場合は {@code false} を返します（呼び出し側で再スローしてください）。
	 *
	 * @param ex DB 制約違反
	 * @param errors バリデーションエラー
	 * @return 画面へ戻す重複エラーとして変換した場合 {@code true}
	 */
	public boolean handleDataIntegrityViolation(DataIntegrityViolationException ex, Errors errors) {
		if (!isEmployeeCodeOrEmailUniqueViolation(ex)) {
			return false;
		}

		errors.reject("dbConstraintViolation", DB_CONSTRAINT_VIOLATION_MESSAGE);
		return true;
	}

	private boolean isAllowedDepartment(Long departmentId, Employee existingEmployee) {
		if (departmentMapper.findDepartmentById(departmentId) != null) {
			return true;
		}

		if (existingEmployee == null || !Objects.equals(departmentId, existingEmployee.getDepartmentId())) {
			return false;
		}

		return departmentMapper.findDepartmentByIdIncludingDeleted(departmentId) != null;
	}

	private boolean isAllowedStatusCode(String statusCode, Employee existingEmployee) {
		if (commonCodeMapper.findByCodeTypeAndCode(EMPLOYEE_STATUS_CODE_TYPE, statusCode) != null) {
			return true;
		}

		if (existingEmployee == null || !Objects.equals(statusCode, existingEmployee.getStatusCode())) {
			return false;
		}

		return commonCodeMapper.findByCodeTypeAndCodeIncludingDeleted(
				EMPLOYEE_STATUS_CODE_TYPE, statusCode) != null;
	}

	private boolean isEmployeeCodeOrEmailUniqueViolation(DataIntegrityViolationException ex) {
		String constraintName = findPostgresConstraintName(ex);
		return UNIQUE_CONSTRAINT_EMPLOYEE_CODE.equals(constraintName)
				|| UNIQUE_CONSTRAINT_EMAIL.equals(constraintName);
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

	private Employee toEmployeeForCreate(EmployeeForm form) {
		Employee employee = new Employee();
		employee.setEmployeeCode(form.getEmployeeCode());
		copyMutableFields(form, employee);
		return employee;
	}

	private Employee toEmployeeForUpdate(Long employeeId, EmployeeForm form) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		copyMutableFields(form, employee);
		return employee;
	}

	private void copyMutableFields(EmployeeForm form, Employee employee) {
		employee.setEmployeeName(form.getEmployeeName());
		employee.setEmployeeNameKana(form.getEmployeeNameKana());
		employee.setEmail(form.getEmail());
		employee.setPhoneNumber(form.getPhoneNumber());
		employee.setDepartmentId(form.getDepartmentId());
		employee.setPosition(form.getPosition());
		employee.setHireDate(form.getHireDate());
		employee.setStatusCode(form.getStatusCode());
		employee.setRemoteWorkFlg(form.getRemoteWorkFlg());
		employee.setRemarks(form.getRemarks());
	}
}
