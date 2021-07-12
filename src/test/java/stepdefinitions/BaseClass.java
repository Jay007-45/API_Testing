package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.core.dataprovider.JsonDataProvider;
import com.sample.generla.Browser;
import com.sample.generla.DriverFactory;
import com.sample.generla.WebDrivers;

public class BaseClass {

	static JsonDataProvider dataProvider;
	WebDriver driver;

	BaseClass() {
		System.out.println("Inside base");
		dataProvider = JsonDataProvider.getInstance();

	}

}