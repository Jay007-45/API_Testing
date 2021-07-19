package com.sample.generla;

import org.openqa.selenium.WebDriver;


public class MainTest {

    public static void main(String[] args) {

        WebDriver driver = WebDrivers.getLocalDriver(Browser.Chrome);
        driver.get("https://www.toolsqa.com/selenium-cucumber-framework/page-object-manager/");

    }

}
