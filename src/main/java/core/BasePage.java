package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class BasePage {
     //static final String baseUrl="http://alex.academy/exercises/signup/v1/";
     //static final String baseUrl="http://alex.academy/exercises/";
 	 //static String _browser;// = "chrome"; // "HtmlUnit" "Firefox" "Safari" 
 	 static String currentPage; 
 	 
 	By appTitleLocator =  By.id("id_f_title");
 	 
 	   protected BasePage(String pageUrl) {
 	        this.currentPage = pageUrl;

 	    }
 	 
 	   public String getCurrentPage() {
 		  return this.currentPage;
 		  //return baseUrl + this.currentPage;
 	   }
 	   
 	  public String get_page_application_title (By locator, WebDriver driver) {
 			String apptitle = driver.findElement(locator).getText();			
 			return apptitle;	
 	  } // get_page_application_title  END	


 	  public String getElementText(By locator, WebDriver driver) {
 		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
 		WebDriverWait wait = new WebDriverWait(driver, 15);
 		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
 	  	String content_text = driver.findElement(locator).getText();
 	  	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 	  	return content_text;
 	  } //getElementText ** end

 	/*  //String link = id_img_facebook, id_img_twitter, id_img_flickr, id_img_youtube
 	  public static String verify_link (String content) throws IOException, InterruptedException{
 	  	Thread.sleep(1000);	
 	  	Browsers.driver.findElement(By.id(content)).click();
 	  	Browsers.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  	Thread.sleep(1500);
 	  	
 	  	ArrayList<String> allTabs = new ArrayList<String>(Browsers.driver.getWindowHandles());
 	  	Browsers.driver.switchTo().window(allTabs.get(1));
 	  	Thread.sleep(1000);
 	  	String content_test = Browsers.driver.getTitle();
 	  	Browsers.driver.close();
 	  	Browsers.driver.switchTo().window(allTabs.get(0));
 	  	if (Browsers.driver != null) {Browsers.driver.quit();}
 	  	return content_test;} // verify_link END


 	  public static String verify_error_handling (String content) throws IOException{
 	  	Browsers.driver.findElement(By.id("id_submit_button")).click();
 	  	String err_xpath=".//*[@id='ErrorLine']";
 	  	String content_test = Browsers.driver.findElement(By.xpath(err_xpath)).getText();
 	  	//String title = verify_page_application_title("id_f_title");
 	  	if (Browsers.driver != null) {Browsers.driver.quit();}
 	  	return content_test;} // verify_content END
*/

// 	   protected WebDriver getBrowser() {
// 		  return BrowserFactory.getBrowser(_browser);
// 	   }
 	   
 	   


// 	
// 	public static String verify_page_application_title (String pageTitle) {
// 		String content_test = driver.findElement(By.id(content)).getText();			
// 		if (Browsers.driver != null) {Browsers.driver.quit();}
// 		return content_test;} // verify_page_application_title  END	



  
     
     
}
