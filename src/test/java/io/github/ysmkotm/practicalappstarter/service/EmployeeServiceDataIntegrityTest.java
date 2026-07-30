package io.github.ysmkotm.practicalappstarter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import io.github.ysmkotm.practicalappstarter.form.EmployeeForm;
import io.github.ysmkotm.practicalappstarter.mapper.CommonCodeMapper;
import io.github.ysmkotm.practicalappstarter.mapper.DepartmentMapper;
import io.github.ysmkotm.practicalappstarter.mapper.EmployeeMapper;

class EmployeeServiceDataIntegrityTest {

	private EmployeeService employeeService;

	@BeforeEach
	void setUp() {
		employeeService = new EmployeeService(
				mock(EmployeeMapper.class),
				mock(DepartmentMapper.class),
				mock(CommonCodeMapper.class));
	}

	@Test
	void handleDataIntegrityViolation_社員番号UNIQUEなら画面エラーに変換する() {
		DataIntegrityViolationException ex = wrapPostgresConstraint("employee_employee_code_key");
		Errors errors = newErrors();

		boolean handled = employeeService.handleDataIntegrityViolation(ex, errors);

		assertTrue(handled);
		assertTrue(errors.hasGlobalErrors());
		assertEquals("登録内容が重複しています。入力内容を確認してください。",
				errors.getGlobalError().getDefaultMessage());
	}

	@Test
	void handleDataIntegrityViolation_メールUNIQUEなら画面エラーに変換する() {
		DataIntegrityViolationException ex = wrapPostgresConstraint("employee_email_key");
		Errors errors = newErrors();

		boolean handled = employeeService.handleDataIntegrityViolation(ex, errors);

		assertTrue(handled);
		assertTrue(errors.hasGlobalErrors());
	}

	@Test
	void handleDataIntegrityViolation_外部キー制約なら変換しない() {
		DataIntegrityViolationException ex = wrapPostgresConstraint("fk_employee_department");
		Errors errors = newErrors();

		boolean handled = employeeService.handleDataIntegrityViolation(ex, errors);

		assertFalse(handled);
		assertFalse(errors.hasErrors());
	}

	@Test
	void handleDataIntegrityViolation_主キー制約なら変換しない() {
		DataIntegrityViolationException ex = wrapPostgresConstraint("employee_pkey");
		Errors errors = newErrors();

		boolean handled = employeeService.handleDataIntegrityViolation(ex, errors);

		assertFalse(handled);
		assertFalse(errors.hasErrors());
	}

	@Test
	void handleDataIntegrityViolation_制約名を特定できない場合は変換しない() {
		DataIntegrityViolationException ex = new DataIntegrityViolationException("no postgres cause");
		Errors errors = newErrors();

		boolean handled = employeeService.handleDataIntegrityViolation(ex, errors);

		assertFalse(handled);
		assertFalse(errors.hasErrors());
	}

	private static Errors newErrors() {
		return new BeanPropertyBindingResult(new EmployeeForm(), "employeeForm");
	}

	private static DataIntegrityViolationException wrapPostgresConstraint(String constraintName) {
		String serverMessage = "SERROR\0C23505\0n" + constraintName + "\0Mduplicate key value";
		PSQLException psqlException = new PSQLException(new ServerErrorMessage(serverMessage));
		return new DataIntegrityViolationException("integrity violation", psqlException);
	}
}
