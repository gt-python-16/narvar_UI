package core;


import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LouandgreyPageTest extends BaseTest{
	
	String expectedAltLogo =  "louandgrey";
	String expectedConfirmMessage = "You Are Signed Up To Receive Text Delivery Updates";
	String expectedErrorMessageInvalidPhone = "Please enter a valid phone number.";
	String expectedShippingStatus = "In Transit to Sender";
	String newValidNumber = "6501234567";
	String newInvalidNumber = "650123456";
	

	
  @Test   // 01  verify correct logo: alt text is expectedAltLogo
  public void test_01_verify_retail_logo(){
	Louandgrey louandgrey = Louandgrey.openTrackingPage(driver);
  	String altRetailerLogo = louandgrey.getAltRetailerLogo();
  	assertThat(altRetailerLogo, is(expectedAltLogo));
  	
  }
	
  @Test   // 02  // verify valid phone number is updated
  public void test_02_verify_phone_is_updated() throws InterruptedException{
	Louandgrey louandgrey = Louandgrey.openTrackingPage(driver);
	String confirmUpdate = louandgrey.updatePhoneNumber(newValidNumber);
  	//String expectedErrorMessage = signUpPage.verify_error_handling(correct_fname, correct_lname, correct_email, invalid_phone);
  	assertThat(confirmUpdate.trim().toLowerCase(), is(expectedConfirmMessage.trim().toLowerCase()));  	
  }
	
  @Test     // 03 // verify invalid phone number is not updated, error message is displaying
  public void test_03_verify_error_handling_ivalid_phone() throws InterruptedException{
	Louandgrey louandgrey = Louandgrey.openTrackingPage(driver);
	String confirmUpdate = louandgrey.updatePhoneNumberWithInvalidNumber(newInvalidNumber);
  	//String expectedErrorMessage = signUpPage.verify_error_handling(correct_fname, correct_lname, correct_email, invalid_phone);
  	assertThat(confirmUpdate.trim().toLowerCase(), is(expectedErrorMessageInvalidPhone.trim().toLowerCase()));  	
  }
  
	@Test     // 04 // verify click on logo image link opens partner/retailer page
	public void test_04_verify_partner_link() throws InterruptedException{
		Louandgrey louandgrey = Louandgrey.openTrackingPage(driver); 	
		String expectedTitle = louandgrey.verifyLogolink("Lou & Grey");
		assertThat(expectedTitle, containsString("Lou & Grey"));  	
	}
  
	@Test     // 05 // verify click on tracking number link opens shipping (FedEx) tracking page
	public void test_05_verify_shipping_link() throws InterruptedException{
		Louandgrey louandgrey = Louandgrey.openTrackingPage(driver);  	
		String expectedTitle = louandgrey.verifyShippinglink("FedEx");
		assertThat(expectedTitle, containsString("FedEx")); 	
	}
	
	 @Test    // 06 // verify shipping status
	 public void test_06_verify_shipping_status(){
		Louandgrey louandgrey = Louandgrey.openTrackingPage(driver); 
	    String currentStatus = louandgrey.getShippingStatus();
	  	assertThat(currentStatus, is(expectedShippingStatus));  	
	  }
	 
	 @Test    // 07  // verify number of tracking records
	 public void test_07_verify_number_of_tracking_records(){
		Louandgrey louandgrey = Louandgrey.openTrackingPage(driver); 
	  	int expectedNumberOfRecords = 25;
	    int numberOfRecords = louandgrey.getNumberOfShippingRecords();
	  	assertThat(numberOfRecords, is(expectedNumberOfRecords));  	
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		if(driver != null) {
			driver.close();
			driver.quit();
		}
	}
	
}

