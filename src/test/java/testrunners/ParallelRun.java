package testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {"pretty", "json:target/Destination/cucumber.json"},
        monochrome = true,
        glue = {"stepdefinitions"},
        tags = "@Smoke or @Regression",
        publish = true,
        features = {"src/test/resources/AppFeatures/"})

public class ParallelRun extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

/*
 * mvn -Denv=dev -Dcucumber.options="@smoke and @fast" test //// Plugin =
 * ,"timeline:test-output-thread/"
 */