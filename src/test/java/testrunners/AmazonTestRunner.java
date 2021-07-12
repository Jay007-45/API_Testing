package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/resources/AppFeatures/Search2.feature",
				"src/test/resources/AppFeatures/Search.feature" },
		glue = { "stepdefinitions", "AmazonHooks" },
		tags = "@Smoke or @Regression",
		plugin = { "pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"  }

		
		
		
)
public class AmazonTestRunner {

}
