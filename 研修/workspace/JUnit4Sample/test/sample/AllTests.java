package sample;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConvertorUtilityTest.class, ValidatorUtilityTest.class})
public class AllTests {
}