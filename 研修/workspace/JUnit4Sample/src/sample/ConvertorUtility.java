package sample;

/**
 * 変換ユーティリティクラス
 */
public class ConvertorUtility {

	/**
	 * nullをブランク文字列に変換する
	 *
	 * @param value
	 *            文字列
	 * @return nullをブランク文字列に変換した結果文字列
	 */
	static public String nullToBlank(String value) {
		if (value == null) {
			return "";
		}

		return value;
	}

	/**
	 * 2つの文字列をハイフン区切りで結合
	 *
	 * @param value1
	 *            文字列1
	 * @param value2
	 *            文字列2
	 * @return ハイフン区切りで結合した文字列
	 */
	static public String concatHyphen(String value1, String value2) {
		if (value1 == null) {
			value1 = "";
		}
		if (value2 == null) {
			value2 = "";
		}

		return value1 + "-" + value2;
	}
}