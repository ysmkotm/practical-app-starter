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

import io.github.ysmkotm.practicalappstarter.entity.Department;
import io.github.ysmkotm.practicalappstarter.form.DepartmentForm;
import io.github.ysmkotm.practicalappstarter.form.DepartmentSearchForm;
import io.github.ysmkotm.practicalappstarter.form.ValidationGroups;
import io.github.ysmkotm.practicalappstarter.service.DepartmentService;

/**
 * 部署管理画面のリクエストを処理するコントローラです。
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * 部署一覧画面を表示します。
	 *
	 * @param departmentSearchForm 検索条件
	 * @param bindingResult バリデーション結果
	 * @param model モデル
	 * @return 部署一覧画面
	 */
	@GetMapping
	public String showList(
			@Validated @ModelAttribute("departmentSearchForm") DepartmentSearchForm departmentSearchForm,
			BindingResult bindingResult,
			Model model) {

		// 入力値に問題がある場合は検索を実行しない
		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", List.of());
			return "department/list";
		}

		List<Department> departments = departmentService.searchDepartments(departmentSearchForm);
		model.addAttribute("departments", departments);

		return "department/list";
	}

	/**
	 * 部署登録画面を表示します。
	 *
	 * @param model モデル
	 * @return 部署登録画面
	 */
	@GetMapping("/new")
	public String showCreate(Model model) {

		model.addAttribute("mode", "create");
		model.addAttribute("departmentForm", new DepartmentForm());

		return "department/form";
	}

	/**
	 * 部署編集画面を表示します。
	 *
	 * @param departmentId 部署ID
	 * @param model モデル
	 * @return 部署編集画面
	 */
	@GetMapping("/{departmentId}/edit")
	public String showEdit(
			@PathVariable("departmentId") Long departmentId,
			Model model) {

		Department department = departmentService.findDepartmentById(departmentId);
		if (department == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		prepareEditFormModel(departmentId, department, departmentService.toDepartmentForm(department), model);

		return "department/form";
	}

	/**
	 * 部署を登録します。
	 *
	 * @param departmentForm 入力 Form
	 * @param bindingResult バリデーション結果
	 * @param model モデル
	 * @param redirectAttributes リダイレクト先への Flash 属性
	 * @return 部署一覧画面、または登録画面
	 */
	@PostMapping
	public String create(
			@Validated(ValidationGroups.Create.class) @ModelAttribute("departmentForm") DepartmentForm departmentForm,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {

		model.addAttribute("mode", "create");

		departmentService.validateUniqueConstraints(departmentForm, null, bindingResult);

		if (bindingResult.hasErrors()) {
			return "department/form";
		}

		try {
			departmentService.createDepartment(departmentForm);
		} catch (DataIntegrityViolationException ex) {
			if (!departmentService.handleDataIntegrityViolation(ex, bindingResult)) {
				throw ex;
			}
			return "department/form";
		}

		redirectAttributes.addFlashAttribute("successMessage", "部署を登録しました。");
		return "redirect:/department";
	}

	/**
	 * 部署を更新します。
	 *
	 * @param departmentId 部署ID
	 * @param departmentForm 入力 Form
	 * @param bindingResult バリデーション結果
	 * @param model モデル
	 * @param redirectAttributes リダイレクト先への Flash 属性
	 * @return 部署一覧画面、または編集画面
	 */
	@PostMapping("/{departmentId}")
	public String update(
			@PathVariable("departmentId") Long departmentId,
			@Validated(ValidationGroups.Update.class) @ModelAttribute("departmentForm") DepartmentForm departmentForm,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {

		Department department = departmentService.findDepartmentById(departmentId);
		if (department == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		prepareEditFormModel(departmentId, department, departmentForm, model);

		departmentService.validateUniqueConstraints(departmentForm, departmentId, bindingResult);

		if (bindingResult.hasErrors()) {
			return "department/form";
		}

		try {
			if (departmentService.updateDepartment(departmentId, departmentForm) == 0) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		} catch (DataIntegrityViolationException ex) {
			if (!departmentService.handleDataIntegrityViolation(ex, bindingResult)) {
				throw ex;
			}
			return "department/form";
		}

		redirectAttributes.addFlashAttribute("successMessage", "部署を更新しました。");
		return "redirect:/department";
	}

	/**
	 * 部署を論理削除します。
	 *
	 * @param departmentId 部署ID
	 * @param redirectAttributes リダイレクト先への Flash 属性
	 * @return 部署一覧画面
	 */
	@PostMapping("/{departmentId}/delete")
	public String delete(
			@PathVariable("departmentId") Long departmentId,
			RedirectAttributes redirectAttributes) {

		if (departmentService.deleteDepartment(departmentId) == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		redirectAttributes.addFlashAttribute("successMessage", "部署を削除しました。");
		return "redirect:/department";
	}

	/**
	 * 編集画面用の Model を設定します。
	 * <p>
	 * 削除確認ダイアログ用の部署コード・部署名は、編集画面表示時点（DB）の値を正とします（DEP002 §8.5）。
	 * バリデーションエラー再表示時も、未保存の入力値ではなく DB 値を使います。
	 */
	private void prepareEditFormModel(
			Long departmentId,
			Department department,
			DepartmentForm departmentForm,
			Model model) {

		model.addAttribute("mode", "edit");
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("departmentForm", departmentForm);
		model.addAttribute("deleteConfirmDepartmentCode", department.getDepartmentCode());
		model.addAttribute("deleteConfirmDepartmentName", department.getDepartmentName());
	}
}
