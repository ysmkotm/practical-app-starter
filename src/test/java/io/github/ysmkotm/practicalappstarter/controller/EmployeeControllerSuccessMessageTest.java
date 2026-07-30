package io.github.ysmkotm.practicalappstarter.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import io.github.ysmkotm.practicalappstarter.entity.Employee;
import io.github.ysmkotm.practicalappstarter.form.EmployeeForm;
import io.github.ysmkotm.practicalappstarter.service.EmployeeService;

/**
 * 登録・更新・削除成功時の Flash 成功メッセージ（PRG-CMN-003）を検証します。
 */
@ExtendWith(MockitoExtension.class)
class EmployeeControllerSuccessMessageTest {

	@Mock
	private EmployeeService employeeService;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController(employeeService)).build();
	}

	@Test
	void create_成功時は一覧へリダイレクトし登録メッセージを渡す() throws Exception {
		stubFormOptions(null);
		doNothing().when(employeeService).validateMasterReferences(any(EmployeeForm.class), isNull(), any(BindingResult.class));
		doNothing().when(employeeService).validateUniqueConstraints(any(EmployeeForm.class), isNull(), any(BindingResult.class));
		doNothing().when(employeeService).createEmployee(any(EmployeeForm.class));

		mockMvc.perform(post("/employee")
						.param("employeeCode", "EMP999001")
						.param("employeeName", "テスト太郎")
						.param("email", "test@example.com")
						.param("departmentId", "1")
						.param("hireDate", "2020-04-01")
						.param("statusCode", "ACTIVE")
						.param("remoteWorkFlg", "false"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/employee"))
				.andExpect(flash().attribute("successMessage", "社員を登録しました。"));
	}

	@Test
	void update_成功時は一覧へリダイレクトし更新メッセージを渡す() throws Exception {
		Employee employee = existingEmployee(10L, "EMP000010");
		when(employeeService.findEmployeeById(10L)).thenReturn(employee);
		stubFormOptions(employee);
		doNothing().when(employeeService).validateMasterReferences(any(EmployeeForm.class), eq(employee), any(BindingResult.class));
		doNothing().when(employeeService).validateUniqueConstraints(any(EmployeeForm.class), eq(10L), any(BindingResult.class));
		when(employeeService.updateEmployee(eq(10L), any(EmployeeForm.class))).thenReturn(1);

		mockMvc.perform(post("/employee/10")
						.param("employeeName", "テスト太郎")
						.param("email", "test@example.com")
						.param("departmentId", "1")
						.param("hireDate", "2020-04-01")
						.param("statusCode", "ACTIVE")
						.param("remoteWorkFlg", "false"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/employee"))
				.andExpect(flash().attribute("successMessage", "社員を更新しました。"));
	}

	@Test
	void delete_成功時は一覧へリダイレクトし削除メッセージを渡す() throws Exception {
		when(employeeService.deleteEmployee(10L)).thenReturn(1);

		mockMvc.perform(post("/employee/10/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/employee"))
				.andExpect(flash().attribute("successMessage", "社員を削除しました。"));
	}

	private void stubFormOptions(Employee currentEmployee) {
		Long departmentId = currentEmployee != null ? currentEmployee.getDepartmentId() : null;
		String statusCode = currentEmployee != null ? currentEmployee.getStatusCode() : null;
		when(employeeService.findDepartmentsForForm(departmentId)).thenReturn(List.of());
		when(employeeService.findEmployeeStatusesForForm(statusCode)).thenReturn(List.of());
	}

	private static Employee existingEmployee(Long id, String code) {
		Employee employee = new Employee();
		employee.setEmployeeId(id);
		employee.setEmployeeCode(code);
		employee.setEmployeeName("既存");
		employee.setEmail("existing@example.com");
		employee.setDepartmentId(1L);
		employee.setHireDate(LocalDate.of(2020, 4, 1));
		employee.setStatusCode("ACTIVE");
		employee.setRemoteWorkFlg(false);
		return employee;
	}
}
