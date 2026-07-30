package io.github.ysmkotm.practicalappstarter.form;

/**
 * Bean Validation のグループ定義です。
 */
public final class ValidationGroups {

	private ValidationGroups() {
	}

	/**
	 * 登録時のバリデーショングループです。
	 */
	public interface Create {
	}

	/**
	 * 更新時のバリデーショングループです。
	 */
	public interface Update {
	}
}
