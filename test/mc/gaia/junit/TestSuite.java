package mc.gaia.junit;

import mc.gaia.junit.search.validate.RegionSetValidatorTest;
import mc.gaia.junit.search.validate.RegionValidatorTest;
import mc.gaia.junit.search.validate.SearchValidatorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RegionValidatorTest.class, RegionSetValidatorTest.class, SearchValidatorTest.class })
public class TestSuite {

}