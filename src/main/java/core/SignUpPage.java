package core;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.*;

public class SignUpPage extends BasePage {
	public static final String PAGE_URL = "http://alex.academy/exercises/signup/v1/";
    public static WebDriverWait wait;
    public static WebDriver _driver;
    
    By quotesLocator =  By.id("id_quotes");
    By emailLocator =  By.id("id_email");
    By phoneLocator =  By.id("id_phone");
    //By genderLocator =  By.id("id_");
    By lNameLocator =  By.id("id_lname");
    By fNameLocator =  By.id("id_fname");
    By stateLocator =  By.id("id_state");
    
    By imgFacebookLocator =  By.id("id_img_facebook");
    By imgTwitterLocator =  By.id("id_img_twitter");
    By imgFlickrLocator =  By.id("id_img_flickr");
    By imgYoutubeLocator =  By.id("id_img_youtube");
    
    By temperatureLocator =  By.id("id_temperature");
    By weatherIconLocator =  By.id("id_weather_icon");
    By currentLocationLocator =  By.id("id_current_location");
    By submitButtonLocator = By.id("id_submit_button");
    By resetButtonLocator = By.id("id_reset_button");
    
    By timeStampLocator = By.id("timestamp");
    By osBrowserLocator = By.id("os_browser");
    By copyrightLocator = By.id("copyright");
    
    By appTitleLocator = By.id("id_f_title");
    
    By maleRButtonLocator = By.id("id_g_radio_01");
    By femaleRButtonLocator = By.id("id_g_radio_02");
    
    By agreeCheckBoxLocator = By.id("id_checkbox");
    
    By errorLineLocator = By.id("ErrorLine");
    


	protected SignUpPage() {
		super(PAGE_URL); 
	}
	
	 public static SignUpPage openSignUpPage(WebDriver driver) {
		 SignUpPage signUpPage = new SignUpPage();
		 _driver = driver;
         Logger l = Logger.getLogger("");
         l.setLevel(Level.OFF);
         driver.get(PAGE_URL); 
	     return signUpPage;
	   }
	 
	   public void setFirstName(String firstName) {
	       // first name
           WebElement firstNameField = _driver.findElement(fNameLocator);
		   firstNameField.clear();
		   firstNameField.sendKeys(firstName);
	   }
	   
	   public void setLastName(String lastName) {
	       // last name
           WebElement lastNameField = _driver.findElement(lNameLocator);
           lastNameField.clear();
           lastNameField.sendKeys(lastName);
	   }
	   
	   public void setEmail(String email) {
	       // email
           WebElement emailField = _driver.findElement(emailLocator);
           emailField.clear();
           emailField.sendKeys(email);
	   }
	   
	   public void setPhoneNumber(String phoneNumber) {
	       // phoneNumber
           WebElement phoneField = _driver.findElement(phoneLocator);
           phoneField.clear();
           phoneField.sendKeys(phoneNumber);
	   }
	   
	   public void clickMaleRadioButton() {
	 		_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 		WebDriverWait wait = new WebDriverWait(_driver, 20);
	 		wait.until(ExpectedConditions.elementToBeClickable(_driver.findElement(maleRButtonLocator)));
	        _driver.findElement(maleRButtonLocator).click();
	 		_driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   }
	   
	   public void clickFemaleRadioButton() {
	 		_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 		WebDriverWait wait = new WebDriverWait(_driver, 20);
	 		wait.until(ExpectedConditions.elementToBeClickable(_driver.findElement(femaleRButtonLocator)));
	        _driver.findElement(femaleRButtonLocator).click();
	 		_driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   }
	   
	    public void clickFacebookImgLink() {
	        WebElement facebookImgLink = _driver.findElement(imgFacebookLocator);
	        facebookImgLink.click();
	    }
	    
	    public void clickTwitterImgLink() {
	        _driver.findElement(imgTwitterLocator).click();
	    }
	    
	    public void clickFlickrImgLink() {
	        _driver.findElement(imgFlickrLocator).click();
	    }
	    
	    public void clickYoutubeImgLink() {
	        _driver.findElement(imgYoutubeLocator).click();
	    }
	    
	    
	    public void clickAgreeTerms(boolean check) {
	    	//  check = true => to check 
	        //  check = false => to uncheck
	    	WebElement checkAgree = _driver.findElement(agreeCheckBoxLocator);
	    	boolean selected_status = checkAgree.isSelected();
	    	if (check != selected_status) { checkAgree.click(); }
	    }
	    
/*	    public ConfirmationPage clickSubmitButton() {
	        WebElement submitButton = _driver.findElement(submitButtonLocator);
	        submitButton.click();
	        return new ConfirmationPage();
	    }*/
	    
	    public void clickSubmitButton() {
	        WebElement submitButton = _driver.findElement(submitButtonLocator);
	        submitButton.click();
	    }
	    
	    public Boolean isQuotesDispayed() {
			WebElement q =  _driver.findElement(quotesLocator); 
			
			String quote = q.getText();
			if (quote != null && quote.length() < 103 && quote.length() > 36) {
				System.out.println("Test Case ID: \t\t" + "quotes"
						+ " - PASSED");
				System.out.println("Quote length: " + quote.length());
				System.out.println("Quote : " + quote);
				System.out.println("=======================================");
				return true;
			} else {
				System.out.println("Test Case ID: \t\t" +  "quotes"
						+ " - FAILED");
				System.out.println("Quote length: " + quote.length());
				System.out.println("Quote : " + quote);
				System.out.println("=======================================");
				return false;
			}
			
	    }
	    
	    public String getCity() {
	    	return getElementText(currentLocationLocator);
	    }
	    
	    public String getTemperature() {
	    	return getElementText(temperatureLocator);
	    }
	    
	    public String getOS() {
	    	String os_browser = getElementText(osBrowserLocator);
	    	String regex = "(.*)(?:&)(.*)";
	    	Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(os_browser); 
			m.find();
			String currentOS = m.group(1);

	    	return currentOS;
	    }
	    
	    public String getBrowser() {
	    	String os_browser = getElementText(osBrowserLocator);
	    	String[] splittedOSBrowser = os_browser.split("&");
			String currentBrowser = splittedOSBrowser[1].trim();
	    	return currentBrowser;
	    }
	    
	    public String getTimeStamp() {
	    	return getElementText(timeStampLocator);
	    }
	    

	    //    By timeStampLocator = By.id("timestamp");
	    //By osBrowserLocator = By.id("os_browser"); //macOS 10.12 & Chrome 56
	    
	 // for String content = quote, temperature, current_city, copyright, todays_day, os_browser	
	 	  public String getElementText(By locator) {
	 		_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 		WebDriverWait wait = new WebDriverWait(_driver, 15);
	 		wait.until(ExpectedConditions.visibilityOf(_driver.findElement(locator)));
	 	  	String content_text = _driver.findElement(locator).getText();
	 	  	_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 	  	return content_text;
	 	  	} //getElementText ** end
	 	  
	 	 public String verify_link(String substr_title) throws InterruptedException{
	 		//clickFacebookImgLink();

	 		_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); 		
	 		ArrayList<String> allTabs = new ArrayList<String>(_driver.getWindowHandles());
	 		

	 		WebDriverWait wait = new WebDriverWait(_driver, 20);
	 		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	 		_driver.switchTo().window(allTabs.get(1));
	 		wait.until(ExpectedConditions.titleContains(substr_title));
	 		String pageTitle = _driver.getTitle();	
	 		
	 		_driver.close();

	 		_driver.switchTo().window(allTabs.get(0));
	 		wait.until(ExpectedConditions.titleContains("Welcome to Sign Up"));
	 		_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 		return pageTitle;
	 		
	 	 } // 
	 	 
	 	public String verify_error_handling(String fname, String lname, String email, String phone) throws InterruptedException{
	 		if (fname.trim() != "") setFirstName(fname);
	 		if (lname.trim() != "") setLastName(lname);
	 		if (email.trim() != "") setEmail(email);
	 		if (phone.trim() != "") setPhoneNumber(phone);
	 		clickSubmitButton();
	 		String errorText = getElementText(errorLineLocator).trim();
	 		return errorText;
	 	}	
	 	
	 	public void selectState(String state) {
	 		//WebElement stateList = _driver.findElement(stateLocator);
	 		_driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); 
	 		WebDriverWait wait = new WebDriverWait(_driver, 20);
	 		wait.until(ExpectedConditions.elementToBeClickable(_driver.findElement(stateLocator)));	 		
	 		Select states = new Select(_driver.findElement(stateLocator));
	 		states.selectByValue(state);
	 		_driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 	}
	 	  
	 	public String verify_confirmation(String fname, String lname, String email, String phone) throws InterruptedException{
//	 		ConfirmationPage cp = new ConfirmationPage();
	 		ConfirmationPage cp = submit_form(fname, lname, email, phone);
	 		String pageTitle = cp.get_page_application_title(cp.appTitleLocator, _driver);
	 		return pageTitle;
	 	}	   
	   
	 	public ConfirmationPage submit_form(String fname, String lname, String email, String phone) throws InterruptedException{
	 		setFirstName(fname);
	 		setLastName(lname);
	 		setEmail(email);
	 		setPhoneNumber(phone);
	 		clickSubmitButton();
	 		return new ConfirmationPage();
	 	}

	 	
	 	public boolean submit_full_form(String fname, String lname, String email, String phone, boolean isAgreedToTerms, String gender, String state ) 
	 			throws InterruptedException {
	 		setFirstName(fname);
	 		setLastName(lname);
	 		setEmail(email);
	 		setPhoneNumber(phone);
	 		clickAgreeTerms(isAgreedToTerms);
	 		if (gender.equalsIgnoreCase("male")) {clickMaleRadioButton();}
	 		//else {clickFemaleRadioButton();}
	 		else if (gender.equalsIgnoreCase("female")) {clickFemaleRadioButton();}
	 		selectState(state);
	 		clickSubmitButton();
	 		ConfirmationPage cp = new ConfirmationPage();	
	 		//boolean status = cp.verify_submit_form(fname, lname, email, phone, _driver);
	 		boolean status = cp.verify_submit_form(fname, lname, email, phone, isAgreedToTerms, gender, state, _driver);
	 		return status; 
	 	}
}

