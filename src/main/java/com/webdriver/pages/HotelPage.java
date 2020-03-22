package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HotelPage {
	private final WebDriver driver;
	
	By firstName = By.id("firstname");
	By lastName = By.id("lastname");
	By totalPrice = By.id("totalprice");
	By depositPaid = By.id("depositpaid");
	By checkin = By.id("checkin");
	By checkout = By.id("checkout");
	By deleteBooking = By.xpath("//input[@type='button'][@value='Delete']");
	
	By save = By.xpath("//input[@type='button'][@onclick='createBooking()']");

	public HotelPage(WebDriver driver) {
        this.driver = driver;
    }

	public void openHotelPage(String hotelWebsiteUrl) throws InterruptedException{
		driver.navigate().to(hotelWebsiteUrl);
		driver.findElement(firstName).isDisplayed();
	}
	
	public boolean isPageLoaded(String url, String pageTitle) throws InterruptedException{
		openHotelPage(url);
		return driver.getTitle().contains(pageTitle);
	}

	public boolean openPageIfNotLoaded(String url, String pageTitle) throws InterruptedException{
		if(!driver.getTitle().contains("Hotel booking form")) {
			openHotelPage(url);
		}
		return driver.getTitle().contains("Hotel booking form");
	}
	
	public boolean createBooking(String _firstName, String _lastName, String _totalPrice, String _deposit, String _checkin, String _checkout) throws InterruptedException {
		driver.findElement(firstName).sendKeys(_firstName);
		driver.findElement(lastName).sendKeys(_lastName);
		driver.findElement(totalPrice).sendKeys(_totalPrice);
		WebElement deposit = driver.findElement(depositPaid);
		Select selectDeposit = new Select(deposit);
		selectDeposit.selectByVisibleText(_deposit);
		driver.findElement(checkin).sendKeys(_checkin);
		driver.findElement(checkout).sendKeys(_checkout);
		driver.findElement(save).click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(deleteBooking));
		return true;
	}
	
	public boolean deleteBooking() throws InterruptedException {
		if(driver.findElement(deleteBooking).isDisplayed()) {
			driver.findElement(deleteBooking).click();
		}
		else return false;
		return true;
	}
}
