package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.*;

/**
 * 'Lou and Gray' TrackingPage
 *
 */
public class Louandgrey extends BasePage {
	public static final String PAGE_URL = "http://qa.narvar.com/louandgrey/tracking/FedEx?tracking_numbers=846902361923544";
    public static WebDriverWait wait;
    public static WebDriver _driver;
    
    By phoneLocator =  By.id("tel");
    By updatePhoneLocator = By.cssSelector("a.sms-redirect-link");  //or By.className("sms-redirect-link");
    By updatedPhoneMessageLocator = By.cssSelector(".sms-header-container>h4");//By.className("updated-success-header");
    By smsSignUpButtonLocator = By.id("sms-signup");
    By errorMessageInvalidPhoneNumberLocator = By.cssSelector(".invalid-sms-number-tooltip>div>div");
    By trackingNumberLocator = By.className("tracking-number");
    By shippingStatusLocator = By.xpath("//div[@class='tracking-status-container']/h2");
    By trackingTimeStampsLocator = By.xpath("//ul[@class='shippingActivityList']/li/div[@class='timestamp']");
    By trackingDescriptionsLocator = By.xpath("//ul[@class='shippingActivityList']/li/div[@class='description-container']");

	protected Louandgrey() {
		super(PAGE_URL); 
	}


	 public static Louandgrey openTrackingPage(WebDriver driver) {
		 Louandgrey trackingPage = new Louandgrey();
		 _driver = driver;
         Logger l = Logger.getLogger("");
         l.setLevel(Level.OFF);
         driver.get(PAGE_URL); 
	     return trackingPage;
	   }
	

	 public String getShippingStatus() {
			_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 		WebDriverWait wait = new WebDriverWait(_driver, 20);
	 		wait.until(ExpectedConditions.visibilityOfElementLocated(shippingStatusLocator));	  	
	 	  	WebElement element = _driver.findElement(shippingStatusLocator);
	 	  	String content = (String) ((JavascriptExecutor) _driver)
	 	  			.executeScript("return arguments[0].innerHTML", element);
	 	  	System.out.println(content);
	 	  	_driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 	  	
	 	  	return content;

		}

		public int getNumberOfShippingRecords() {
			List<WebElement> trackingTimeStamps = _driver.findElements(By.className("timestamp")); 
			//List<WebElement> trackingDescriptions = _driver.findElements(trackingDescriptionsLocator); 
			return trackingTimeStamps.size();
		}
	 
	   public void setPhoneNumber(String phoneNumber) {
	       // phoneNumber
           WebElement phoneField = _driver.findElement(phoneLocator);
           phoneField.clear();
           phoneField.sendKeys(phoneNumber);
	   }

	   
	    public String updatePhoneNumber(String phoneNumber) {
	    	return updatePhone(phoneNumber, updatedPhoneMessageLocator);
	    }
	    
	    
	    public String updatePhoneNumberWithInvalidNumber(String phoneNumber) {
	    	return updatePhone(phoneNumber, errorMessageInvalidPhoneNumberLocator);	
	    }
	    
		public String updatePhone(String phoneNumber, By messageLocator) {    
	    	String confirmMessage = "";
	    	if (isElementHidden(phoneLocator, _driver)) {
	    		_driver.findElement(updatePhoneLocator).click();
	           WebElement phone = _driver.findElement(phoneLocator);
	           phone.clear();
	           phone.sendKeys(phoneNumber);
	           _driver.findElement(smsSignUpButtonLocator).click();
	           confirmMessage = getElementInnerHTML(messageLocator);         
	    	}
	    	return confirmMessage.trim();
		}
		
		// returns alt-text for LOGO image link
	    public String getAltRetailerLogo() {
			WebElement logo =  _driver.findElement(retailerLogoLocator); 
			
			return logo.getAttribute("alt");
			
		}
	    
	    // click on logo image link -> redirect to retailer site -> verify page title  
	    public String verifyLogolink(String substr_title) throws InterruptedException {
	         _driver.findElement(retailerLogoLocator).click();
	         return verify_link(substr_title);
	        
         }   
	   
	    // click on tracking number link -> redirect to shipping company site -> verify page title  
	    public String verifyShippinglink(String substr_title) throws InterruptedException {

	    	 String trackTitle = "";
	    	 //scrolling to make link visible
	    	 _driver.findElement(By.cssSelector("div.tracking-status-container")).click();
	    	 ((JavascriptExecutor)_driver).executeScript("arguments[0].scrollIntoView();", _driver.findElement(trackingNumberLocator));
		 		_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		 		WebDriverWait wait = new WebDriverWait(_driver, 30);
		 		wait.until(ExpectedConditions.visibilityOfElementLocated(trackingNumberLocator));
		 		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.carrier-logo")));
	    	    WebElement trackingNumberLink = _driver.findElement(trackingNumberLocator);
	
		 	  	_driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	        	 trackingNumberLink.click();
	        	 trackTitle = verify_link(substr_title);
	         
	        return trackTitle;
        }   
	   ///////////////

	
	 public String getElementText(By locator) {
	 		_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 		WebDriverWait wait = new WebDriverWait(_driver, 30);
	 		wait.until(ExpectedConditions.elementToBeClickable(_driver.findElement(locator)));
	 		//wait.until(ExpectedConditions.visibilityOf(_driver.findElement(locator)));
	 	  	String content_text = _driver.findElement(locator).getText();
	 	  	_driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 	  	return content_text;
	  } //getElementText ** end
	 	  
	 public String getElementInnerHTML(By locator) {
	 		_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 		WebDriverWait wait = new WebDriverWait(_driver, 20);
	 		wait.until(ExpectedConditions.elementToBeClickable(_driver.findElement(locator)));	  	
	 	  	WebElement element = _driver.findElement(locator);
	 	  	String content = (String) ((JavascriptExecutor) _driver)
	 	  			.executeScript("return arguments[0].innerHTML", element);
	 	  	//System.out.println(content);
	 	  	_driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 	  	
	 	  	return content;
	 	  	
	  } //getElementInnerHTML ** end
	 
	
	 
	  public String verify_link(String substr_title) throws InterruptedException{

	
		 		_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); 		
		 		ArrayList<String> allTabs = new ArrayList<String>(_driver.getWindowHandles());
		 		
	
		 		WebDriverWait wait = new WebDriverWait(_driver, 40);
		 		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		 		_driver.switchTo().window(allTabs.get(1));
		 		wait.until(ExpectedConditions.titleContains(substr_title));
		 		String pageTitle = _driver.getTitle();			 		
		 		//String trackTitle = _driver.findElement(By.className("shipmentTitleBar_label_track_nick")).getAttribute("title");		 		
		 		_driver.close();
		 		wait.until(ExpectedConditions.numberOfWindowsToBe(1));
		 		_driver.switchTo().window(allTabs.get(0));
		 		wait.until(ExpectedConditions.titleContains("louandgrey.narvar.com"));
		 		_driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 		return pageTitle;
		 		
	  } //
	    
	  
	  
}

