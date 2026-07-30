package io.github.ysmkotm.practicalappstarter.form;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class EmployeeFormValidationTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void create_必須項目が未入力の場合はエラーになる() {
		EmployeeForm form = new EmployeeForm();

		Set<ConstraintViolation<EmployeeForm>> violations = validator.validate(form, ValidationGroups.Create.class);

		assertFalse(violations.isEmpty());
		assertTrue(hasPropertyPath(violations, "employeeCode"));
		assertTrue(hasPropertyPath(violations, "employeeName"));
		assertTrue(hasPropertyPath(violations, "email"));
		assertTrue(hasPropertyPath(violations, "departmentId"));
		assertTrue(hasPropertyPath(violations, "hireDate"));
		assertTrue(hasPropertyPath(violations, "statusCode"));
	}

	@Test
	void create_必須項目が入力されている場合はエラーにならない() {
		EmployeeForm form = validForm();

		Set<ConstraintViolation<EmployeeForm>> violations = validator.validate(form, ValidationGroups.Create.class);

		assertTrue(violations.isEmpty());
	}

	@Test
	void update_社員番号が未入力でもエラーにならない() {
		EmployeeForm form = validForm();
		form.setEmployeeCode(null);

		Set<ConstraintViolation<EmployeeForm>> violations = validator.validate(form, ValidationGroups.Update.class);

		assertFalse(hasPropertyPath(violations, "employeeCode"));
	}

	@Test
	void update_必須項目が未入力の場合はエラーになる() {
		EmployeeForm form = new EmployeeForm();

		Set<ConstraintViolation<EmployeeForm>> violations = validator.validate(form, ValidationGroups.Update.class);

		assertFalse(violations.isEmpty());
		assertFalse(hasPropertyPath(violations, "employeeCode"));
		assertTrue(hasPropertyPath(violations, "employeeName"));
		assertTrue(hasPropertyPath(violations, "email"));
	}

	private static EmployeeForm validForm() {
		EmployeeForm form = new EmployeeForm();
		form.setEmployeeCode("EMP000099");
		form.setEmployeeName("テスト太郎");
		form.setEmail("test@example.com");
		form.setDepartmentId(1L);
		form.setHireDate(LocalDate.of(2020, 4, 1));
		form.setStatusCode("ACTIVE");
		return form;
	}

	private static boolean hasPropertyPath(Set<ConstraintViolation<EmployeeForm>> violations, String propertyPath) {
		return violations.stream().anyMatch(v -> propertyPath.equals(v.getPropertyPath().toString()));
	}
}
