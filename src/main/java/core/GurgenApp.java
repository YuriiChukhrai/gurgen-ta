package core;

import static core.Utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.asserts.SoftAssert;

import ru.yandex.qatools.allure.annotations.Step;

public class GurgenApp {

	private String appVersion;

	private String numberOfTurnsExpected;
	private String minNumberDicesExpected;
	private String maxNumberDicesExpected;

	private String gurgenOutPut;
	private final List<String[]> resultTurns = new ArrayList<>();
	private final SoftAssert softAssert = new SoftAssert();

	private final String numberOfTurnsRegEx = "(?:Number\\sof\\sturns:\\s?)(\\d{1,8})";
	private final String minNumberDicesRegEx = "(?:Minimum\\snumber\\sof\\sdices:\\s?)(\\d{1,8})";
	private final String maxNumberDicesRegEx = "(?:Maximum\\snumber\\sof\\sdices:\\s?)(\\d{1,8})";
	private final String resultRegEx = "(?:Dices:\\s?)([\\d\\s]+)(?:Result:\\s?)(\\d{1,3})";
	private final String wronArgsRegEx = "(Wrong\\sarguments)";

	public GurgenApp(final String appVersion, final String numberOfTurnsExpected, final String minNumberDicesExpected,
			final String maxNumberDicesExpected) {
		this.appVersion = appVersion;
		this.numberOfTurnsExpected = numberOfTurnsExpected;
		this.minNumberDicesExpected = minNumberDicesExpected;
		this.maxNumberDicesExpected = maxNumberDicesExpected;
	}

	@Step("Is console out correct")
	public final boolean isOutputCorret() {
		// System.out.println(gurgenOutPut);
		
		boolean status = true;

		final Pattern pattern = Pattern.compile(resultRegEx, Pattern.CASE_INSENSITIVE);
		final Matcher regex = pattern.matcher(gurgenOutPut);

		int count = 0;

		while (regex.find()) {
			resultTurns.add(new String[] { regex.group(1), regex.group(2) });

			softAssert.assertEquals(regex.group(2), getResult(regex.group(1)), String.format("Dices#:%d", count));
			//System.out.println(String.format("Result Expected[%s] / Actual[%s]", regex.group(1), getResult(regex.group(2))));
			++count;
		}

		try {
			softAssert.assertAll();
		} catch (AssertionError e) {
			status = false;
			System.out.println(String.format("Error MSG[%s]", e.getMessage()));
			e.printStackTrace();
		}

		return status;
	}

	@Step("Get Number Of Turns Actual")
	public final String getNumberOfTurnsActual() {
		return regEx(numberOfTurnsRegEx, gurgenOutPut, 1);
	}

	@Step("Get Min Number Dices Actual")
	public final String getMinNumberDicesActual() {
		return regEx(minNumberDicesRegEx, gurgenOutPut, 1);
	}

	@Step("Get Max Number Dices Actual")
	public final String getMaxNumberDicesActual() {
		return regEx(maxNumberDicesRegEx, gurgenOutPut, 1);
	}

	@Step("Execute Gurgen app.")
	public final GurgenApp execGurgenProgram() throws IOException {

		gurgenOutPut = getGurgenOutput(appVersion, numberOfTurnsExpected, minNumberDicesExpected,
				maxNumberDicesExpected);

		return this;
	}

	@Step("Get Error messege")
	public final String getWrongArgument() {
		return regEx(wronArgsRegEx, gurgenOutPut, 1);
	}

	@Step("Get size of actula Dices")
	public final int getResultSizeOut() {
		return resultTurns.size();
	}

	/**********************************************************************************************************************/
	private final String regEx(final String regEx, final String input, int group) {

		String result = null;
		try {
			final Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
			final Matcher regex = pattern.matcher(input);

			if (regex.find()) {
				result = regex.group(group);
			} else {
				System.out.println(String.format("Can't find any match, regEx [%s]", regEx));
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		return result;
	}

	private final String getResult(final String string) {

		int result = 0;

		if (string != null) {

			// Исключение - специальная комбинация: 1,2,3,4,5 = 150 очков
			// if (string.trim().equals("1 2 3 4 5")) {
			if(string != null && string.contains("1")
					&& string.contains("2")
					&& string.contains("3")
					&& string.contains("4")
					&& string.contains("5")) {	
			result = 150;
				System.out.println(String.format("Was found special combination [%s]", string.replaceAll("\n", "")));
			} else {

				for (String oneSide : string.split("\\s")) {

					switch (oneSide.trim()) {
					case "1": {
						result += 10;
						break;
					}
					case "5": {
						result += 5;
						break;
					}
					case "0": {
						System.out.println("Error");
						break;
					}
					}// END switch
				} // END for
			}

		} else {
			System.out.println("Input string is 'null'");
		}
		return Integer.toString(result);
	}

}
