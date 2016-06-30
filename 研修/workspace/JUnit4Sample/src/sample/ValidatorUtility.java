package sample;

/**
 * 検証ユーティリティクラス
 */
public class ValidatorUtility {

	/**
	 * ブランクチェック
	 *
	 * @param value
	 *            値
	 * @return ブランク有無
	 */
	static public boolean isBlank(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * int値チェック
	 *
	 * @param value
	 *            文字列
	 * @return int値判定有無
	 */
	static public boolean isInt(String value) {
		if (isBlank(value)) {
			return false;
		}
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}