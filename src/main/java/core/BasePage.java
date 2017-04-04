package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * BasicPage
 *
 * common methods used for tracking pages
 *
 */
public class BasePage {
	
 	 static String currentPage; 
 	 
 	By retailerLogoLocator =  By.id("retailer-logo");
 	 
 	   protected BasePage(String pageUrl) {
 	        this.currentPage = pageUrl;

 	    }
 	 
 	   public String getCurrentPage() {
 		  return this.currentPage;
 		  //return baseUrl + this.currentPage;
 	   }
 	   
// 	  public String get_page_application_title (By locator, WebDriver driver) {
// 			String apptitle = driver.findElement(locator).getText();			
// 			return apptitle;	
// 	  } // get_page_application_title  END	


 	  public String getElementText(By locator, WebDriver driver) {
 		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
 		WebDriverWait wait = new WebDriverWait(driver, 15);
 		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
 	  	String content_text = driver.findElement(locator).getText();
 	  	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 	  	return content_text;
 	  } //getElementText ** end

	   public boolean isElementHidden(By locator, WebDriver driver) {
	       //  not isDisplayed
		   //gg.getAttribute("aria-hidden")
           WebElement element = driver.findElement(locator);
           //boolean notHidden = element.isDisplayed();
           boolean hidden = element.getAttribute("aria-hidden").equals("true")?true:false;
           //System.out.println("hidden = "+element.getAttribute("aria-hidden"));
           return hidden;
           
	   }
  
     
     
}
