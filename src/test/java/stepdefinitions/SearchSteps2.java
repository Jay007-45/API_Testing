package stepdefinitions;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.net.URISyntaxException;

import com.core.dataprovider.JsonDataProvider;
import com.sample.generla.Browser;
import com.sample.generla.DriverFactory;
import com.sample.generla.WebDrivers;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.MacBook;

public class SearchSteps2 extends BaseClass {
	MacBook data;

	@Before()
	public void setup_browser(Scenario sc) throws URISyntaxException, IOException, ParseException {
//
//		DriverFactory.getInstance().setDriver(WebDrivers.getLocalDriver(Browser.Chrome));
//		DriverFactory.getInstance().getDriver().get("https://www.youtube.com/watch?v=pcVWuz8YEqQ");
	
		data = dataProvider.readRequestValuesFromJSONObject(sc, MacBook.class);
	}

	@Given("^I have a search$")
	public void i_have_a_search() throws Throwable {
		System.out.println("Data value "+data.getTest());
	}

	@When("^I search for a product$")
	public void i_search_for_a_product() throws Throwable {

	}

	@Then("^Product with name$")
	public void product_with_name() throws Throwable {

	}

	@Then("^Order id is 12345$")
	public void order_id_is_12345() throws Throwable {

	}

}
