package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.annotations.BeforeMethod;

public class AmazonHooks {


    @BeforeMethod
    @Before()
    public void setup_browser(Scenario sc) {

        System.out.println("Inside AmazonHooks");
    }

}
