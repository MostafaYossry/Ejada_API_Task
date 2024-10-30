package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = {"src/test/java/feature"} ,
	glue 	 = {"stepDefin"},
	plugin   = {
			"pretty",									              // Print readable console output
			"html:target/cucumber-reports/cucumber-html-report.html", // HTML report
			},
	monochrome  = true,
	publish 	= true
	)

public class RunnerAPI {
	
}
