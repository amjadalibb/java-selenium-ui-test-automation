package com.webdriver.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webdriver.pages.HotelPage;

public class TestBookHotel extends BaseClass{
	String hotelWebsiteUrl = "http://hotel-test.equalexperts.io/";
	String pageTitle = "Hotel booking form";
	Map<String, String> bookingData = new HashMap<>();

	HotelPage hotelPage;
	
	public TestBookHotel() {
		bookingData.put("_firstName_", "Johson");
		bookingData.put("_lastName_", "Albert");
		bookingData.put("_totalPrice_", "400");
		bookingData.put("_deposit_", "false");
		bookingData.put("_checkin_", "2020-05-01");
		bookingData.put("_checkout_", "2020-06-01");
	}
	
	@Test(priority=1, description="Valid Scenario to Create Bookings")
	public void testCreateBooking() throws InterruptedException {
	    
		hotelPage = new HotelPage(this.driver);	
		Assert.assertTrue(hotelPage.isPageLoaded(hotelWebsiteUrl, pageTitle), "Page is not loaded");
		String bookingID = hotelPage.createBooking(bookingData);
		Assert.assertTrue(bookingID != null, "Booking failed to create");
	}

	@Test(priority=2, description="Valid Scenario to Delete Booking")
	public void testDeleteBooking() throws InterruptedException {
		hotelPage = new HotelPage(this.driver);	
		Assert.assertTrue(hotelPage.openPageIfNotLoaded(hotelWebsiteUrl, pageTitle), "Page is not loaded");
		String bookingID = hotelPage.findBooking(bookingData);
		if(bookingID != null) {
			Assert.assertTrue(hotelPage.deleteBooking(bookingID), "No booking available");
			Thread.sleep(5000);
			bookingID = hotelPage.findBooking(bookingData);
			Assert.assertTrue(bookingID == null, "Booking is deleted with ID: " + bookingID);
		} else Assert.assertTrue(bookingID != null, "There is no booking to delete");
	}
}
