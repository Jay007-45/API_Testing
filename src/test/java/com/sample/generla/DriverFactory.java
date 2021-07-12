package com.sample.generla;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	private DriverFactory() {
	}

	// Singleton Desing Pattern
	private static DriverFactory instance = new DriverFactory();

	public static DriverFactory getInstance() {
		return instance;
	}

	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	// Factory Design Pattern
	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver webdriver) {
		driver.set(webdriver);
	}

	public void closeDriver() {
		driver.get().close();
		driver.remove();
	}
}
