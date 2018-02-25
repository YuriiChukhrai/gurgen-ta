package core;

import static core.Utils.attachText;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class Utils {

	
	@Step("Execute Gurgen: ver.[{0}]")
	public static synchronized String getGurgenOutput(final String appVersion, final String numberOfTurnsExpected, final String minNumberDicesExpected, final String maxNumberDicesExpected) throws IOException {

		final Runtime runtime = Runtime.getRuntime();
		//Process proc = runtime.exec(String.format("%s %s %s %s",filePath, numberOfTurnsExpected, minNumberDicesExpected, maxNumberDicesExpected));
		//BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	     
		final String filePath = String.format(".%1$ssrc%1$stest%1$sresources%1$sgurgen_app%1$sgurgen_%2$s", File.separator, appVersion);
		Process proc = runtime.exec(String.format("%s %s %s %s",filePath, numberOfTurnsExpected, minNumberDicesExpected, maxNumberDicesExpected)); 
		
		final File executableGurgenApp = new File(filePath);
		final StringBuilder collectOutput = new StringBuilder(100); 
		
		/* Set application user permissions to 455 */
		if (!executableGurgenApp.canExecute()) {
			System.out.println(String.format("Gurgen#[%s] was set executable ...", appVersion));
			executableGurgenApp.setExecutable(true);
		}
		
		try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()))) {
			
			/* Read the output from the command */ 
			String lineOutput = null;
			while ((lineOutput = stdInput.readLine()) != null) {
				collectOutput.append(lineOutput).append("\n");
			}
			
			/* Read any errors from the attempted command */
			//System.out.println("Here is the standard error of the command (if any):\n");
			while ((lineOutput = stdError.readLine()) != null) {
				collectOutput.append(lineOutput).append("\n");
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		attachText("Console Out", collectOutput.toString());
		
		return collectOutput.toString();
	}
	
	/* This method make Text attachment for Allure report */
	@Attachment(value = "{0}", type = "text/plain")
	public static synchronized String attachText(final String nameOfAttachment, final String bodyOfMassege) {

		System.out.println("Attached to allure file [" + nameOfAttachment + "].");

		return (bodyOfMassege != null && !bodyOfMassege.isEmpty() ? bodyOfMassege : "Empty Content");
	}
}
