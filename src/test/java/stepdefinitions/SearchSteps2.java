package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import pojo.MacBook;
import pojo.TestData;

import java.io.IOException;
import java.net.URISyntaxException;

public class SearchSteps2 extends BaseClass {
    TestData data;

    @Before()
    public void setup_browser(Scenario sc) throws URISyntaxException, IOException, ParseException {

        data = dataProvider.loadDataFromJsonFile("Test", TestData.class);
    }

    @Given("^I have a search$")
    public void i_have_a_search() throws Throwable {
        System.out.println("Data value " + data.getProd().getMacBook().getTest());
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
