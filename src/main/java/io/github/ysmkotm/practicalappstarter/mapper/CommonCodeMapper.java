package io.github.ysmkotm.practicalappstarter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.github.ysmkotm.practicalappstarter.entity.CommonCode;

/**
 * 共通コードマスタの Mapper です。
 */
public interface CommonCodeMapper {

	/**
	 * 指定したコード種別の未削除コードを表示順昇順で取得します。
	 *
	 * @param codeType コード種別
	 * @return 共通コード一覧
	 */
	List<CommonCode> findByCodeType(String codeType);

	/**
	 * コード種別とコード値で未削除の共通コードを1件取得します。
	 *
	 * @param codeType コード種別
	 * @param code コード値
	 * @return 共通コード。存在しない、または論理削除済みの場合は {@code null}
	 */
	CommonCode findByCodeTypeAndCode(
			@Param("codeType") String codeType,
			@Param("code") String code);

	/**
	 * コード種別とコード値で共通コードを1件取得します（論理削除済みを含む）。
	 *
	 * @param codeType コード種別
	 * @param code コード値
	 * @return 共通コード。存在しない場合は {@code null}
	 */
	CommonCode findByCodeTypeAndCodeIncludingDeleted(
			@Param("codeType") String codeType,
			@Param("code") String code);
}
