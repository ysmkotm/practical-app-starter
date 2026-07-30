package io.github.ysmkotm.practicalappstarter.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.ysmkotm.practicalappstarter.dto.EmployeeListItemDto;
import io.github.ysmkotm.practicalappstarter.entity.CommonCode;
import io.github.ysmkotm.practicalappstarter.entity.Department;
import io.github.ysmkotm.practicalappstarter.entity.Employee;
import io.github.ysmkotm.practicalappstarter.form.EmployeeForm;
import io.github.ysmkotm.practicalappstarter.form.EmployeeSearchForm;
import io.github.ysmkotm.practicalappstarter.form.ValidationGroups;
import io.github.ysmkotm.practicalappstarter.service.EmployeeService;

/**
 * 社員管理画面のリクエストを処理するコントローラです。
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * 社員一覧画面を表示します。
	 *
	 * @param employeeSearchForm 検索条件
	 * @param bindingResult バリデーション結果
	 * @param model モデル
	 * @return 社員一覧画面
	 */
	@GetMapping
	public String showList(
			@Validated @ModelAttribute("employeeSearchForm") EmployeeSearchForm employeeSearchForm,
			BindingResult bindingResult,
			Model model) {

		addEmployeeFormSelectOptions(model, null);

		// 入力値に問題がある場合は検索を実行しない
		if (bindingResult.hasErrors()) {
			model.addAttribute("employees", List.of());
			return "employee/list";
		}

		List<EmployeeListItemDto> employees = employeeService.searchEmployees(employeeSearchForm);
		model.addAttribute("employees", employees);

		return "employee/list";
	}

	/**
	 * 社員登録画面を表示します。
	 *
	 * @param model モデル
	 * @return 社員登録画面
	 */
	@GetMapping("/new")
	public String showCreate(Model model) {

		model.addAttribute("mode", "create");
		model.addAttribute("employeeForm", new EmployeeForm());
		addEmployeeFormSelectOptions(model, null);

		return "employee/form";
	}

	/**
	 * 社員編集画面を表示します。
	 *
	 * @param employeeId 社員ID
	 * @param model モデル
	 * @return 社員編集画面
	 */
	@GetMapping("/{employeeId}/edit")
	public String showEdit(
			@PathVariable("employeeId") Long employeeId,
			Model model) {

		Employee employee = employeeService.findEmployeeById(employeeId);
		if (employee == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		prepareEditFormModel(employeeId, employee, employeeService.toEmployeeForm(employee), model);

		return "employee/form";
	}

	/**
	 * 社員を登録します。
	 *
	 * @param employeeForm 入力 Form
	 * @param bindingResult バリデーション結果
	 * @param model モデル
	 * @param redirectAttributes リダイレクト先への Flash 属性
	 * @return 社員一覧画面、または登録画面
	 */
	@PostMapping
	public String create(
			@Validated(ValidationGroups.Create.class) @ModelAttribute("employeeForm") EmployeeForm employeeForm,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {

		model.addAttribute("mode", "create");
		addEmployeeFormSelectOptions(model, null);

		employeeService.validateMasterReferences(employeeForm, null, bindingResult);
		employeeService.validateUniqueConstraints(employeeForm, null, bindingResult);

		if (bindingResult.hasErrors()) {
			return "employee/form";
		}

		try {
			employeeService.createEmployee(employeeForm);
		} catch (DataIntegrityViolationException ex) {
			if (!employeeService.handleDataIntegrityViolation(ex, bindingResult)) {
				throw ex;
			}
			return "employee/form";
		}

		redirectAttributes.addFlashAttribute("successMessage", "社員を登録しました。");
		return "redirect:/employee";
	}

	/**
	 * 社員を更新します。
	 *
	 * @param employeeId 社員ID
	 * @param employeeForm 入力 Form
	 * @param bindingResult バリデーション結果
	 * @param model モデル
	 * @param redirectAttributes リダイレクト先への Flash 属性
	 * @return 社員一覧画面、または編集画面
	 */
	@PostMapping("/{employeeId}")
	public String update(
			@PathVariable("employeeId") Long employeeId,
			@Validated(ValidationGroups.Update.class) @ModelAttribute("employeeForm") EmployeeForm employeeForm,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {

		Employee employee = employeeService.findEmployeeById(employeeId);
		if (employee == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		applyEmployeeCodeFromDatabase(employeeForm, employee);
		prepareEditFormModel(employeeId, employee, employeeForm, model);

		employeeService.validateMasterReferences(employeeForm, employee, bindingResult);
		employeeService.validateUniqueConstraints(employeeForm, employeeId, bindingResult);

		if (bindingResult.hasErrors()) {
			return "employee/form";
		}

		try {
			if (employeeService.updateEmployee(employeeId, employeeForm) == 0) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		} catch (DataIntegrityViolationException ex) {
			if (!employeeService.handleDataIntegrityViolation(ex, bindingResult)) {
				throw ex;
			}
			return "employee/form";
		}

		redirectAttributes.addFlashAttribute("successMessage", "社員を更新しました。");
		return "redirect:/employee";
	}

	/**
	 * 社員を論理削除します。
	 *
	 * @param employeeId 社員ID
	 * @param redirectAttributes リダイレクト先への Flash 属性
	 * @return 社員一覧画面
	 */
	@PostMapping("/{employeeId}/delete")
	public String delete(
			@PathVariable("employeeId") Long employeeId,
			RedirectAttributes redirectAttributes) {

		if (employeeService.deleteEmployee(employeeId) == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		redirectAttributes.addFlashAttribute("successMessage", "社員を削除しました。");
		return "redirect:/employee";
	}

	private void prepareEditFormModel(
			Long employeeId,
			Employee employee,
			EmployeeForm employeeForm,
			Model model) {

		applyEmployeeCodeFromDatabase(employeeForm, employee);
		model.addAttribute("mode", "edit");
		model.addAttribute("employeeId", employeeId);
		model.addAttribute("employeeForm", employeeForm);
		addEmployeeFormSelectOptions(model, employee);
	}

	/**
	 * 編集時の社員番号を DB の値で Form に設定します。
	 * <p>
	 * 編集画面の社員番号 input は {@code th:field} を使わず {@code name} 属性を付けないため、
	 * 通常の POST では社員番号は送信されません（{@code readonly} ではなく送信対象外）。
	 * 改ざんされたリクエストで社員番号が送られた場合でも、クライアント送信値は信用せず DB の値を正とします。
	 * 更新 SQL でも {@code employee_code} は更新対象外です（{@link EmployeeService#updateEmployee} 参照）。
	 *
	 * @param employeeForm 入力 Form
	 * @param employee DB から取得した社員
	 */
	private void applyEmployeeCodeFromDatabase(EmployeeForm employeeForm, Employee employee) {
		employeeForm.setEmployeeCode(employee.getEmployeeCode());
	}

	private void addEmployeeFormSelectOptions(Model model, Employee currentEmployee) {
		Long currentDepartmentId = currentEmployee != null ? currentEmployee.getDepartmentId() : null;
		String currentStatusCode = currentEmployee != null ? currentEmployee.getStatusCode() : null;

		List<Department> departments = employeeService.findDepartmentsForForm(currentDepartmentId);
		List<CommonCode> employeeStatuses = employeeService.findEmployeeStatusesForForm(currentStatusCode);

		model.addAttribute("departments", departments);
		model.addAttribute("employeeStatuses", employeeStatuses);
	}
}
