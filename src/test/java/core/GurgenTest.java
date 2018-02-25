package core;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import ru.yandex.qatools.allure.annotations.Parameter;

public class GurgenTest {

	@Parameter("Test case title")
	private String testCaseTitle;

	@DataProvider
	public String[][] positiveGurgen0Dp() {

		// Test case name, numberOfTurnsExpected, minNumberDicesExpected,
		// maxNumberDicesExpected

		return new String[][] { new String[] { "Test case name 01", "10", "1", "3" },
				new String[] { "Test case name 02", "1000", "1", "5" } };
	}

	@DataProvider
	public String[][] negGurgen0Dp() {

		// Test case name, numberOfTurnsExpected, minNumberDicesExpected,
		// maxNumberDicesExpected

		return new String[][] { new String[] { "Test case name 01", "10", "1", "0" },
				new String[] { "Test case name 02", "10", "0", "3" },
				new String[] { "Test case name 03", "0", "1", "5" } };
	}

	@Test(dataProvider = "positiveGurgen0Dp")
	public void gurgenTest01(final String testCaseName, final String numberOfTurnsExpected,
			final String minNumberDicesExpected, final String maxNumberDicesExpected) throws IOException {

		testCaseTitle = testCaseName;
		
		final GurgenApp gurgenApp = new GurgenApp("0", numberOfTurnsExpected, minNumberDicesExpected, maxNumberDicesExpected);

		final SoftAssert softAssert = new SoftAssert();

		gurgenApp.execGurgenProgram();

		softAssert.assertEquals(gurgenApp.getNumberOfTurnsActual(), numberOfTurnsExpected, "Number of turns:");
		softAssert.assertEquals(gurgenApp.getMinNumberDicesActual(), minNumberDicesExpected, "Minimum number of dices: ");
		softAssert.assertEquals(gurgenApp.getMaxNumberDicesActual(), maxNumberDicesExpected, "Maximum number of dices: ");
		softAssert.assertEquals(gurgenApp.isOutputCorret(), true);
		softAssert.assertEquals(gurgenApp.getResultSizeOut(), Integer.parseInt(numberOfTurnsExpected), "Number of turns was found: ");
		
		
		softAssert.assertAll();
	}

	@Test(dataProvider = "negGurgen0Dp")
	public void gurgenTest02(final String testCaseName, final String numberOfTurnsExpected,
			final String minNumberDicesExpected, final String maxNumberDicesExpected) throws IOException {

		testCaseTitle = testCaseName;
		
		final GurgenApp gurgenApp = new GurgenApp("0", numberOfTurnsExpected, minNumberDicesExpected, maxNumberDicesExpected);

		gurgenApp.execGurgenProgram();

		assertEquals(gurgenApp.getWrongArgument(), "Wrong arguments", "Wrong arguments:");
	}
}
