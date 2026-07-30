package io.github.ysmkotm.practicalappstarter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import io.github.ysmkotm.practicalappstarter.entity.CommonCode;
import io.github.ysmkotm.practicalappstarter.entity.Department;
import io.github.ysmkotm.practicalappstarter.entity.Employee;
import io.github.ysmkotm.practicalappstarter.form.EmployeeForm;
import io.github.ysmkotm.practicalappstarter.mapper.CommonCodeMapper;
import io.github.ysmkotm.practicalappstarter.mapper.DepartmentMapper;
import io.github.ysmkotm.practicalappstarter.mapper.EmployeeMapper;

class EmployeeServiceMasterReferenceTest {

	private DepartmentMapper departmentMapper;
	private CommonCodeMapper commonCodeMapper;
	private EmployeeService employeeService;

	@BeforeEach
	void setUp() {
		EmployeeMapper employeeMapper = mock(EmployeeMapper.class);
		departmentMapper = mock(DepartmentMapper.class);
		commonCodeMapper = mock(CommonCodeMapper.class);
		employeeService = new EmployeeService(employeeMapper, departmentMapper, commonCodeMapper);
	}

	@Test
	void validateMasterReferences_登録で未削除部署ならエラーにならない() {
		EmployeeForm form = formWith(1L, "ACTIVE");
		when(departmentMapper.findDepartmentById(1L)).thenReturn(activeDepartment(1L, "営業部"));
		when(commonCodeMapper.findByCodeTypeAndCode("EMPLOYEE_STATUS", "ACTIVE"))
				.thenReturn(activeStatus("ACTIVE", "在籍"));

		Errors errors = newErrors(form);
		employeeService.validateMasterReferences(form, null, errors);

		assertFalse(errors.hasErrors());
	}

	@Test
	void validateMasterReferences_登録で削除済み部署ならエラーになる() {
		EmployeeForm form = formWith(9L, "ACTIVE");
		when(departmentMapper.findDepartmentById(9L)).thenReturn(null);
		when(commonCodeMapper.findByCodeTypeAndCode("EMPLOYEE_STATUS", "ACTIVE"))
				.thenReturn(activeStatus("ACTIVE", "在籍"));

		Errors errors = newErrors(form);
		employeeService.validateMasterReferences(form, null, errors);

		assertTrue(errors.hasFieldErrors("departmentId"));
		assertEquals("選択された部署は現在使用できません。",
				errors.getFieldError("departmentId").getDefaultMessage());
	}

	@Test
	void validateMasterReferences_更新で現在の削除済み部署の維持は許可する() {
		EmployeeForm form = formWith(9L, "ACTIVE");
		Employee existing = existingEmployee(9L, "ACTIVE");
		when(departmentMapper.findDepartmentById(9L)).thenReturn(null);
		when(departmentMapper.findDepartmentByIdIncludingDeleted(9L))
				.thenReturn(deletedDepartment(9L, "旧営業部"));
		when(commonCodeMapper.findByCodeTypeAndCode("EMPLOYEE_STATUS", "ACTIVE"))
				.thenReturn(activeStatus("ACTIVE", "在籍"));

		Errors errors = newErrors(form);
		employeeService.validateMasterReferences(form, existing, errors);

		assertFalse(errors.hasFieldErrors("departmentId"));
	}

	@Test
	void validateMasterReferences_更新で別の削除済み部署への変更は拒否する() {
		EmployeeForm form = formWith(8L, "ACTIVE");
		Employee existing = existingEmployee(9L, "ACTIVE");
		when(departmentMapper.findDepartmentById(8L)).thenReturn(null);
		when(commonCodeMapper.findByCodeTypeAndCode("EMPLOYEE_STATUS", "ACTIVE"))
				.thenReturn(activeStatus("ACTIVE", "在籍"));

		Errors errors = newErrors(form);
		employeeService.validateMasterReferences(form, existing, errors);

		assertTrue(errors.hasFieldErrors("departmentId"));
	}

	@Test
	void validateMasterReferences_更新で現在の削除済み在籍区分の維持は許可する() {
		EmployeeForm form = formWith(1L, "OLD_STATUS");
		Employee existing = existingEmployee(1L, "OLD_STATUS");
		when(departmentMapper.findDepartmentById(1L)).thenReturn(activeDepartment(1L, "営業部"));
		when(commonCodeMapper.findByCodeTypeAndCode("EMPLOYEE_STATUS", "OLD_STATUS")).thenReturn(null);
		when(commonCodeMapper.findByCodeTypeAndCodeIncludingDeleted("EMPLOYEE_STATUS", "OLD_STATUS"))
				.thenReturn(deletedStatus("OLD_STATUS", "旧区分"));

		Errors errors = newErrors(form);
		employeeService.validateMasterReferences(form, existing, errors);

		assertFalse(errors.hasFieldErrors("statusCode"));
	}

	@Test
	void validateMasterReferences_更新で別の削除済み在籍区分への変更は拒否する() {
		EmployeeForm form = formWith(1L, "OTHER_DELETED");
		Employee existing = existingEmployee(1L, "OLD_STATUS");
		when(departmentMapper.findDepartmentById(1L)).thenReturn(activeDepartment(1L, "営業部"));
		when(commonCodeMapper.findByCodeTypeAndCode("EMPLOYEE_STATUS", "OTHER_DELETED")).thenReturn(null);

		Errors errors = newErrors(form);
		employeeService.validateMasterReferences(form, existing, errors);

		assertTrue(errors.hasFieldErrors("statusCode"));
		assertEquals("選択された在籍区分は現在使用できません。",
				errors.getFieldError("statusCode").getDefaultMessage());
	}

	@Test
	void findDepartmentsForForm_削除済み現在値を補完する() {
		when(departmentMapper.findDepartments()).thenReturn(List.of(activeDepartment(1L, "営業部")));
		when(departmentMapper.findDepartmentByIdIncludingDeleted(9L))
				.thenReturn(deletedDepartment(9L, "旧営業部"));

		List<Department> departments = employeeService.findDepartmentsForForm(9L);

		assertEquals(2, departments.size());
		assertEquals("旧営業部（削除済み）", departments.get(1).getDepartmentName());
		assertEquals(9L, departments.get(1).getDepartmentId());
	}

	@Test
	void findDepartmentsForForm_未削除に含まれる場合は補完しない() {
		when(departmentMapper.findDepartments()).thenReturn(List.of(activeDepartment(1L, "営業部")));

		List<Department> departments = employeeService.findDepartmentsForForm(1L);

		assertEquals(1, departments.size());
		assertEquals("営業部", departments.get(0).getDepartmentName());
	}

	@Test
	void findEmployeeStatusesForForm_削除済み現在値を補完する() {
		when(commonCodeMapper.findByCodeType("EMPLOYEE_STATUS"))
				.thenReturn(List.of(activeStatus("ACTIVE", "在籍")));
		when(commonCodeMapper.findByCodeTypeAndCodeIncludingDeleted(eq("EMPLOYEE_STATUS"), eq("OLD")))
				.thenReturn(deletedStatus("OLD", "旧区分"));

		List<CommonCode> statuses = employeeService.findEmployeeStatusesForForm("OLD");

		assertEquals(2, statuses.size());
		assertEquals("旧区分（削除済み）", statuses.get(1).getCodeName());
	}

	private static EmployeeForm formWith(Long departmentId, String statusCode) {
		EmployeeForm form = new EmployeeForm();
		form.setDepartmentId(departmentId);
		form.setStatusCode(statusCode);
		return form;
	}

	private static Employee existingEmployee(Long departmentId, String statusCode) {
		Employee employee = new Employee();
		employee.setDepartmentId(departmentId);
		employee.setStatusCode(statusCode);
		return employee;
	}

	private static Department activeDepartment(Long id, String name) {
		Department department = new Department();
		department.setDepartmentId(id);
		department.setDepartmentName(name);
		department.setDeletedFlg(false);
		return department;
	}

	private static Department deletedDepartment(Long id, String name) {
		Department department = activeDepartment(id, name);
		department.setDeletedFlg(true);
		return department;
	}

	private static CommonCode activeStatus(String code, String name) {
		CommonCode status = new CommonCode();
		status.setCodeType("EMPLOYEE_STATUS");
		status.setCode(code);
		status.setCodeName(name);
		status.setDeletedFlg(false);
		return status;
	}

	private static CommonCode deletedStatus(String code, String name) {
		CommonCode status = activeStatus(code, name);
		status.setDeletedFlg(true);
		return status;
	}

	private static Errors newErrors(EmployeeForm form) {
		return new BeanPropertyBindingResult(form, "employeeForm");
	}
}
