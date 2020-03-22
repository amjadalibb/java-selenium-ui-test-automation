package com.webdriver.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.webdriver.pages.HotelPage;

public class TestBookHotel extends TestBase{
	String hotelWebsiteUrl = "http://hotel-test.equalexperts.io/";
	String pageTitle = "Hotel booking form";
	
	HotelPage hotelPage;
	
	@Test
	public void testCreateBooking() throws InterruptedException {
		hotelPage = new HotelPage(this.driver);	
		Assert.assertTrue(hotelPage.isPageLoaded(hotelWebsiteUrl, pageTitle), "Page is not loaded");
		Assert.assertTrue(hotelPage.createBooking("Amjad", "Ali", "100", "false", "2020-05-01", "2020-06-01"), "Page is not loaded");
	}

	@Test
	public void testDeleteBooking() throws InterruptedException {
		hotelPage = new HotelPage(this.driver);	
		Assert.assertTrue(hotelPage.openPageIfNotLoaded(hotelWebsiteUrl, pageTitle), "Page is not loaded");
		Assert.assertTrue(hotelPage.deleteBooking(), "No booking available");
	}
}
