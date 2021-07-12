package stepdefinitions;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.parser.ParseException;

import com.core.dataprovider.JsonDataProvider;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.MacBook;

public class SearchSteps extends BaseClass {

	MacBook data;

	@Before()
	public void setup_browser(Scenario sc) throws ParseException, URISyntaxException, IOException {

	}

	@Given("I have a search field on Amazon Page")
	public void i_have_a_search_field_on_amazon_page() {
		System.out.println("Step 1: I am on search page");

	}

	@When("^I search for a product with name (.*) and price (\\d+)$")
	public void i_search_for_a_product_with_name_and_price(String productName, Integer price) {
		System.out.println("Step 2: Search the product with name : " + productName + " price: " + price);

	}

	@Then("Product with name {string} should be displayed")
	public void product_with_name_should_be_displayed(String productName) {
		System.out.println("Step 3: product " + productName + " is displayed");

	}

	@Then("Order id is {int} and username is {string}")
	public void order_id_is_and_username_is(Integer int1, String string) {

	}

}
