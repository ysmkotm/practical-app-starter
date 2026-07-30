package io.github.ysmkotm.practicalappstarter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.github.ysmkotm.practicalappstarter.dto.EmployeeListItemDto;
import io.github.ysmkotm.practicalappstarter.entity.Employee;
import io.github.ysmkotm.practicalappstarter.form.EmployeeSearchForm;

/**
 * 社員マスタの Mapper です。
 */
public interface EmployeeMapper {

	/**
	 * 検索条件に合致する社員一覧を取得します。
	 *
	 * @param form 検索条件
	 * @return 社員一覧
	 */
	List<EmployeeListItemDto> searchEmployees(EmployeeSearchForm form);

	/**
	 * 社員IDで社員を1件取得します。
	 *
	 * @param employeeId 社員ID
	 * @return 社員。存在しない、または論理削除済みの場合は {@code null}
	 */
	Employee findEmployeeById(Long employeeId);

	/**
	 * 社員番号の重複件数を取得します。
	 *
	 * @param employeeCode 社員番号
	 * @param excludeEmployeeId 除外する社員ID（更新時）。登録時は {@code null}
	 * @return 未削除レコードの件数
	 */
	int countByEmployeeCode(
			@Param("employeeCode") String employeeCode,
			@Param("excludeEmployeeId") Long excludeEmployeeId);

	/**
	 * メールアドレスの重複件数を取得します。
	 *
	 * @param email メールアドレス
	 * @param excludeEmployeeId 除外する社員ID（更新時）。登録時は {@code null}
	 * @return 未削除レコードの件数
	 */
	int countByEmail(
			@Param("email") String email,
			@Param("excludeEmployeeId") Long excludeEmployeeId);

	/**
	 * 社員を登録します。
	 *
	 * @param employee 登録する社員
	 * @return 登録件数
	 */
	int insertEmployee(Employee employee);

	/**
	 * 社員を更新します。
	 *
	 * @param employee 更新する社員
	 * @return 更新件数
	 */
	int updateEmployee(Employee employee);

	/**
	 * 社員を論理削除します。
	 *
	 * @param employeeId 社員ID
	 * @return 更新件数
	 */
	int logicalDeleteEmployee(Long employeeId);
}
