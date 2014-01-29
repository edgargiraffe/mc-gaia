package mc.gaia.junit;

import mc.gaia.junit.search.validate.RegionSetValidatorTest;
import mc.gaia.junit.search.validate.RegionValidatorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RegionValidatorTest.class, RegionSetValidatorTest.class })
public class TestSuite {

}