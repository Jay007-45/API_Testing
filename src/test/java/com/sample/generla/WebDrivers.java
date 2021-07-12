package com.sample.generla;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDrivers {

	public static WebDriver getLocalDriver(Browser browser) {

		switch (browser) {
			case Chrome:
				ChromeOptions chromeOptions = new ChromeOptions();
				// chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-notifications");
				WebDriverManager.chromedriver().setup();
				return new ChromeDriver(chromeOptions);

			case Firefox:
				WebDriverManager.firefoxdriver().setup();
				return new FirefoxDriver();

			case InternetExplorer:
				WebDriverManager.iedriver().setup();
				return new InternetExplorerDriver();

			case Opera:
				WebDriverManager.operadriver().setup();
				return new OperaDriver();
				
			case Edge:
				WebDriverManager.edgedriver().setup();
				return new EdgeDriver();

			default:
				throw new RuntimeException("Invalid browser attribute!.. Check the browser value..");

		}

	}

	public static WebDriver getLocalGridDriver(Browser browser, URL localGridUrl) {

		DesiredCapabilities capabilities;

		switch (browser) {
			case Chrome:
				capabilities = DesiredCapabilities.chrome();
				capabilities.setPlatform(Platform.LINUX);
				capabilities.setCapability("screenResolution", "1280x720");
				return new RemoteWebDriver(localGridUrl, capabilities);

			case Firefox:
				capabilities = DesiredCapabilities.firefox();
				capabilities.setPlatform(Platform.LINUX);
				capabilities.setCapability("screenResolution", "1280x720");
				return new RemoteWebDriver(localGridUrl, capabilities);

			case InternetExplorer:
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setPlatform(Platform.LINUX);
				capabilities.setCapability("screenResolution", "1280x720");
				return new RemoteWebDriver(localGridUrl, capabilities);

			case Opera:
				capabilities = DesiredCapabilities.operaBlink();
				capabilities.setPlatform(Platform.LINUX);
				capabilities.setCapability("screenResolution", "1280x720");
				return new RemoteWebDriver(localGridUrl, capabilities);

			case Safari:
				capabilities = DesiredCapabilities.safari();
				capabilities.setPlatform(Platform.LINUX);
				capabilities.setCapability("screenResolution", "1280x720");
				return new RemoteWebDriver(localGridUrl, capabilities);

			default:
				throw new RuntimeException("Invalid browser attribute!.. Check the browser value..");

		}

	}
}