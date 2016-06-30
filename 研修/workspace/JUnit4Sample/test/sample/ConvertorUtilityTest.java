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
public class ConvertorUtilityTest {

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
	 * {@link jp.co.junit4.sample.ConvertorUtility#nullToBlank(java.lang.String)}
	 * のためのテスト・メソッド。(nullをブランクに)
	 */
	@Test
	public void nullToBlank_01() {
		System.out.println("nullToBlank_01");
		assertEquals("", ConvertorUtility.nullToBlank(null));
	}

	/**
	 * {@link jp.co.junit4.sample.ConvertorUtility#nullToBlank(java.lang.String)}
	 * のためのテスト・メソッド。(ブランクをブランクに)
	 */
	@Test
	public void nullToBlank_02() {
		System.out.println("nullToBlank_02");
		assertEquals("", ConvertorUtility.nullToBlank(""));
	}

	/**
	 * {@link jp.co.junit4.sample.ConvertorUtility#nullToBlank(java.lang.String)}
	 * のためのテスト・メソッド。(文字列はそのまま)
	 */
	@Test
	public void nullToBlank_03() {
		System.out.println("nullToBlank_03");
		assertEquals("xyz", ConvertorUtility.nullToBlank("xyz"));
	}

	/**
	 * {@link jp.co.junit4.sample.ConvertorUtility#concatHyphen(java.lang.String, java.lang.String)}
	 * のためのテスト・メソッド。(null+null)
	 */
	@Test
	public void concatHyphen_01() {
		System.out.println("concatHyphen_01");
		assertEquals("-", ConvertorUtility.concatHyphen(null, null));
	}

	/**
	 * {@link jp.co.junit4.sample.ConvertorUtility#concatHyphen(java.lang.String, java.lang.String)}
	 * のためのテスト・メソッド。(null+文字列)
	 */
	@Test
	public void concatHyphen_02() {
		System.out.println("concatHyphen_02");
		assertEquals("-xyz", ConvertorUtility.concatHyphen(null, "xyz"));
	}

	/**
	 * {@link jp.co.junit4.sample.ConvertorUtility#concatHyphen(java.lang.String, java.lang.String)}
	 * のためのテスト・メソッド。(文字列+null)
	 */
	@Test
	public void concatHyphen_03() {
		System.out.println("concatHyphen_03");
		assertEquals("abc-", ConvertorUtility.concatHyphen("abc", null));
	}

	/**
	 * {@link jp.co.junit4.sample.ConvertorUtility#concatHyphen(java.lang.String, java.lang.String)}
	 * のためのテスト・メソッド。(文字列+文字列)
	 */
	@Test
	public void concatHyphen_04() {
		System.out.println("concatHyphen_04");
		assertEquals("abc-xyz", ConvertorUtility.concatHyphen("abc", "xyz"));
	}
}