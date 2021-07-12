package hooks;

import org.testng.annotations.BeforeMethod;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class AmazonHooks  {


	@BeforeMethod
	@Before()
	public void setup_browser(Scenario sc) {

		System.out.println("Inside AmazonHooks");
	}

}
