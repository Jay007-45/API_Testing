package stepdefinitions;

import com.core.dataprovider.JsonDataProvider;
import org.openqa.selenium.WebDriver;

public class BaseClass {

    static JsonDataProvider dataProvider;
    WebDriver driver;

    BaseClass() {
        System.out.println("Inside base");
        dataProvider = JsonDataProvider.getInstance();

    }

}