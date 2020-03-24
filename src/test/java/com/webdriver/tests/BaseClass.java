package com.webdriver.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class BaseClass {

 public WebDriver driver;
 Boolean runSafari = false;

@BeforeSuite
 public void setupTest() {
	
	if(runSafari) {
		System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
		driver = new SafariDriver();
	} else {
		 System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
	     
	     ChromeOptions options = new ChromeOptions();
	     Map<String, Object> prefs = new HashMap<String, Object>();
	     prefs.put("profile.managed_default_content_settings.javascript", 1);
	     options.setExperimentalOption("prefs", prefs);
	     options.addArguments("--disable-web-security");
	     
	     driver = new ChromeDriver(options);
	     driver.manage().window().setSize(new Dimension(1150, 650));
	}
 }

 @AfterSuite
 public void tearDownTest() {
     driver.quit();
 }
}