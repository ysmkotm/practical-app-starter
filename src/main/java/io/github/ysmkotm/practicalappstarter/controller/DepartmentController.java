package io.github.ysmkotm.practicalappstarter.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.ysmkotm.practicalappstarter.entity.Department;
import io.github.ysmkotm.practicalappstarter.form.DepartmentSearchForm;
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
}
