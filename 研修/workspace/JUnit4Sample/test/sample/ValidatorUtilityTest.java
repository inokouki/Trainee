/**
 *
 */
package sample;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author みっちー
 *
 */
public class ValidatorUtilityTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setUp");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}

	/**
	 * {@link jp.co.junit4.sample.ValidatorUtility#isBlank(java.lang.String)}
	 * のためのテスト・メソッド。(null)
	 */
	@Test
	public void isBlank_01() {
		System.out.println("isBlank_01");
		assertTrue(ValidatorUtility.isBlank(null));
	}

	/**
	 * {@link jp.co.junit4.sample.ValidatorUtility#isBlank(java.lang.String)}
	 * のためのテスト・メソッド。(空白)
	 */
	@Test
	public void isBlank_02() {
		System.out.println("isBlank_02");
		assertTrue(ValidatorUtility.isBlank(""));
	}

	/**
	 * {@link jp.co.junit4.sample.ValidatorUtility#isBlank(java.lang.String)}
	 * のためのテスト・メソッド。(正常)
	 */
	@Test
	public void isBlank_03() {
		System.out.println("isBlank_03");
		assertFalse(ValidatorUtility.isBlank("xxxxx"));
	}

	/**
	 * {@link jp.co.junit4.sample.ValidatorUtility#isInt(java.lang.String)}
	 * のためのテスト・メソッド。(null)
	 */
	@Test
	public void isInt_01() {
		System.out.println("isInt_01");
		assertFalse(ValidatorUtility.isInt(null));
	}

	/**
	 * {@link jp.co.junit4.sample.ValidatorUtility#isInt(java.lang.String)}
	 * のためのテスト・メソッド。(空白)
	 */
	@Test
	public void isInt_02() {
		System.out.println("isInt_02");
		assertFalse(ValidatorUtility.isInt(""));
	}

	/**
	 * {@link jp.co.junit4.sample.ValidatorUtility#isInt(java.lang.String)}
	 * のためのテスト・メソッド。(数値以外)
	 */
	@Test
	public void isInt_03() {
		System.out.println("isInt_03");
		assertFalse(ValidatorUtility.isInt("x"));
	}

	/**
	 * {@link jp.co.junit4.sample.ValidatorUtility#isInt(java.lang.String)}
	 * のためのテスト・メソッド。(正常)
	 */
	@Test
	public void isInt_04() {
		System.out.println("isInt_04");
		assertTrue(ValidatorUtility.isInt("123456"));
	}

	/**
	 * {@link jp.co.junit4.sample.ValidatorUtility#isInt(java.lang.String)}
	 * のためのテスト・メソッド。(正常マイナス値)
	 */
	@Test
	public void isInt_05() {
		System.out.println("isInt_05");
		assertTrue(ValidatorUtility.isInt("-123456"));
	}

	/**
	 * {@link jp.co.junit4.sample.ValidatorUtility#isInt(java.lang.String)}
	 * のためのテスト・メソッド。(int桁あふれ)
	 */
	@Test
	public void isInt_06() {
		System.out.println("isInt_06");
		assertFalse(ValidatorUtility.isInt("12345678901234567890"));
	}
}