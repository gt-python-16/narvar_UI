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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpTest extends BaseTest{
	
	String correct_fname = "Maxwell";
	String correct_lname = "Smart";
	String correct_email = "agent86@gmail.com";
	String correct_phone = "240 555-1212";
	
	String invalid_fname = "a";
	String invalid_lname = "6";
	String invalid_email = "agent86.gmail.com";
	String invalid_phone = "240q1212";
	
	//true, "Female", "California"
	boolean agreedToTerms = true;
	String gender = "Female";
	String state = "California";
	
	String title_sign_up_expected = "Welcome to Sign Up v1";
	String appTitle_expected = "Sign Up";
	//String title_facebook_expected = "Facebook - Log In or Sign Up";
	String error_empty_fname_expected   = "Please enter First Name";
	String error_invalid_fname_expected = "Invalid First Name: [a-zA-Z,.'-]{3,30}";
	String error_empty_lname_expected   = "Please enter Last Name";
	String error_invalid_lname_expected = "Invalid Last Name: [a-zA-Z,.'-]{3,30}";
	String error_empty_email_expected   = "Please enter Email Address";
	String error_invalid_email_expected = "Invalid Email Address: xx[min2]@x[min1].xx[2-6]";
	String error_empty_phone_expected   = "Please enter Phone Number";
	String error_invalid_phone_expected = "Invalid Phone Number: should be 10 digits";
	


    @Test   // 01
    public void test_01_verify_content_quotes(){
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);

    	Boolean isQuotesDispayed = signUpPage.isQuotesDispayed();
    	Assert.assertTrue(isQuotesDispayed);
    	//assertThat(isQuotesDispayed, is(true));
    	
    }

    @Test    // 02
    public void test_02_verify_content_current_city(){
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	String expectedCity ="San Francisco, CA";
        String currentCity = signUpPage.getCity();
    	assertThat(currentCity, is(expectedCity));  	
    }
    
    @Test    // 03
    public void test_03_verify_content_weather(){
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	String expectedWeather ="64 ℉";
        String currentWeather = signUpPage.getTemperature();
    	assertThat(currentWeather, is(expectedWeather));  	
    }
    
    @Test     // 04
    public void test_04_verify_content_date(){
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	DateFormat df = new SimpleDateFormat("MMMM d, yyyy");
    	Calendar calobj = Calendar.getInstance();
        //System.out.println(df.format(calobj.getTime()));
    	String expectedDate = df.format(calobj.getTime());
        String currentDate = signUpPage.getTimeStamp().toString().trim();
    	assertThat(currentDate, is(expectedDate));  	
    } 
    
    @Test     // 05
    public void test_05_verify_content_os(){
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	
    	String expectedOs_name = System.getProperty("os.name").toLowerCase().trim();
    	if (expectedOs_name.contains("mac")) {expectedOs_name = "macos";}
    	String expectedOs_version = System.getProperty("os.version").trim();
    	String[] arr_version = expectedOs_version.split("\\.");
    	//System.out.println(expectedOs_version);
    	//System.out.println(arr_version.length);
    	if (arr_version.length >2) {expectedOs_version = arr_version[0]+"."+arr_version[1];}
        String expectedOs = expectedOs_name+" "+expectedOs_version;
        String currentOs = signUpPage.getOS().toLowerCase().trim();
    	assertThat(currentOs, is(expectedOs));  	
    } 
    
    @Test     // 06
    public void test_06_verify_content_browser(){
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	
    	String expectedBrowser = getBrowserAndVersion();
        String currentBrowser = signUpPage.getBrowser().toLowerCase().trim();
    	assertThat(currentBrowser, is(expectedBrowser));  	
    }
    
    @Test     // 07
    public void test_07_verify_link_facebook() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	signUpPage.clickFacebookImgLink();   	
    	String expectedTitle = signUpPage.verify_link("Facebook");
    	assertThat(expectedTitle, containsString("Facebook"));  	
    }
    
    @Test     // 08
    public void test_08_verify_link_twitter() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	signUpPage.clickTwitterImgLink();  	
    	String expectedTitle = signUpPage.verify_link("Twitter");
    	assertThat(expectedTitle, containsString("Twitter"));  	
    }
    
    @Test     // 09
    public void test_09_verify_link_flickr() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	signUpPage.clickFlickrImgLink();  	
    	String expectedTitle = signUpPage.verify_link("Flickr");
    	assertThat(expectedTitle, containsString("Flickr"));  	
    }
    
    @Test     // 10
    public void test_10_verify_link_youtube() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	signUpPage.clickYoutubeImgLink(); 	
    	String expectedTitle = signUpPage.verify_link("YouTube");
    	assertThat(expectedTitle, containsString("YouTube"));  	
    }
   
    @Test     // 11
    public void test_11_verify_submit_form() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	boolean isConfirmed = signUpPage.submit_full_form(correct_fname, correct_lname, correct_email, 
    			correct_phone, agreedToTerms, gender, state);
        Assert.assertTrue(isConfirmed);
 	
    }
    
    @Test     // 12
    public void test_12_verify_error_handling_first_name() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	
    	String expectedErrorMessage = signUpPage.verify_error_handling(invalid_fname, "", "", "");
    	assertThat(expectedErrorMessage, is(error_invalid_fname_expected));  	
    }
 
    @Test     // 13
    public void test_13_verify_error_handling_last_name() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	
    	String expectedErrorMessage = signUpPage.verify_error_handling(correct_fname, invalid_lname, "", "");
    	assertThat(expectedErrorMessage, is(error_invalid_lname_expected));  	
    }
    
    @Test     // 14
    public void test_14_verify_error_handling_email() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	
    	String expectedErrorMessage = signUpPage.verify_error_handling(correct_fname, correct_lname, "", "");
    	assertThat(expectedErrorMessage, is(error_empty_email_expected));  	
    }
    
    @Test     // 15
    public void test_15_verify_error_handling_phone() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);
    	
    	String expectedErrorMessage = signUpPage.verify_error_handling(correct_fname, correct_lname, correct_email, invalid_phone);
    	assertThat(expectedErrorMessage, is(error_invalid_phone_expected));  	
    }
    
    @Test     // 16
    public void test_16_verify_confirmation_page() throws InterruptedException{
    	SignUpPage signUpPage = SignUpPage.openSignUpPage(driver);

    	String expectedPageTitle = signUpPage.verify_confirmation(correct_fname, correct_lname, correct_email, correct_phone);
    	assertThat(expectedPageTitle, is("Confirmation"));  	
    }
    
	@AfterTest
	public void tearDown() throws Exception {
		if(driver != null) {
			driver.close();
			driver.quit();
		}
	}
	
	public static String getBrowserAndVersion() {
	    //By osBrowserLocator = By.id("os_browser"); //macOS 10.12 & Chrome 56
		String browser_version = null;
		String browsername = BROWSER;
		// This block to find out IE Version number
		String uAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
		if ("internet explorer".equalsIgnoreCase(browsername)) {
			
			System.out.println(uAgent);
			//uAgent return as "MSIE 8.0 Windows" for IE8
			if (uAgent.contains("MSIE") && uAgent.contains("Windows")) {
				browser_version = uAgent.substring(uAgent.indexOf("MSIE")+5, uAgent.indexOf("Windows")-2);
			} else if (uAgent.contains("Trident/7.0")) {
				browser_version = "11.0";
			} else {
				browser_version = "0.0";
			}
		} else
		{
			String useragentregex = "(?:"+browsername +")(?:\\s)?\\/(?:\\s)?(\\d+)";
			//Browser version for Firefox and Chrome
			browser_version = "";// .split(".")[0];
		    Matcher m_browser = Pattern.compile(useragentregex, Pattern.CASE_INSENSITIVE).matcher(uAgent);
		    m_browser.find();
		    browser_version  = m_browser.group(1);
		}
		//String browserversion = browser_version.substring(, browser_version.indexOf("."));
		return browsername + " " + browser_version;
	}

}

