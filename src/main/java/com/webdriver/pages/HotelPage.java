package com.webdriver.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class HotelPage {
	private final WebDriver driver;
	
	By firstName = By.id("firstname");
	By lastName = By.id("lastname");
	By totalPrice = By.id("totalprice");
	By depositPaid = By.id("depositpaid");
	By checkin = By.id("checkin");
	By checkout = By.id("checkout");
	By bookings = By.id("bookings");
	
	By save = By.xpath("//input[@type='button'][@onclick='createBooking()']");

	String _selectBookingRow = "//*[contains(text(),'_firstName_')]/parent::div/following-sibling::div/*[contains(text(),'_lastName_')]/parent::div/following-sibling::div/*[contains(text(),'_totalPrice_')]/parent::div/following-sibling::div/*[contains(text(),'_deposit_')]/parent::div/following-sibling::div/*[contains(text(),'_checkin_')]/parent::div/following-sibling::div/*[contains(text(),'_checkout_')]/parent::div/parent::div";
	String _searchElement = "//*[contains(text(),'_element_')];";
	String _deleteBooking = "//input[@type='button'][@value='Delete'][@onclick='deleteBooking(_ID_)']";
			
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
	
	// Returns Booking ID if details match
	
	public String findBooking(Map<String, String> bookingData) {
		List<WebElement> elems = driver.findElements(
				By.xpath(_selectBookingRow
						.replace("_firstName_", bookingData.get("_firstName_"))
						.replace("_lastName_", bookingData.get("_lastName_"))
						.replace("_totalPrice_", bookingData.get("_totalPrice_"))
						.replace("_deposit_", bookingData.get("_deposit_"))
						.replace("_checkin_", bookingData.get("_checkin_"))
						.replace("_checkout_", bookingData.get("_checkout_"))
						)
				);
		if(elems.size()>0) {
			System.out.print("ELEMENT ID: " + elems.get(0).getAttribute("id"));
			return elems.get(0).getAttribute("id");
		}
		return null;
	}

	public String createBooking(Map<String, String> bookingData) throws InterruptedException {
		driver.findElement(firstName).clear();
		driver.findElement(firstName).sendKeys(bookingData.get("_firstName_"));
		driver.findElement(lastName).clear();
		driver.findElement(lastName).sendKeys(bookingData.get("_lastName_"));
		driver.findElement(totalPrice).clear();
		driver.findElement(totalPrice).sendKeys(bookingData.get("_totalPrice_"));
		WebElement deposit = driver.findElement(depositPaid);
		Select selectDeposit = new Select(deposit);
		selectDeposit.selectByVisibleText(bookingData.get("_deposit_"));
		driver.findElement(checkin).clear();
		driver.findElement(checkin).sendKeys(bookingData.get("_checkin_"));
		driver.findElement(checkout).clear();
		driver.findElement(checkout).sendKeys(bookingData.get("_checkout_"));
		driver.findElement(save).click();
		Thread.sleep(10000);
		return findBooking(bookingData);
	}
	
	public boolean deleteBooking(String ID) throws InterruptedException {
		WebElement deleteBookingElem = driver.findElement(By.xpath(_deleteBooking.replace("_ID_", ID)));
		if(deleteBookingElem.isDisplayed()) {
			deleteBookingElem.click();
			Thread.sleep(5000);
		}
		else return false;
		return true;
	}
}
