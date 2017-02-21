package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage extends BasePage {
	public static final String PAGE_URL = "http://alex.academy/exercises/signup/v1/confirmation.php";
	   public static WebDriverWait wait;
	    public static WebDriver _driver;
	    
	    By backButtonLocator =  By.id("id_back_button");
	    By c_emailLocator =  By.id("id_email_conf");
	    By c_phoneLocator =  By.id("id_phone_conf");
	    By c_lNameLocator =  By.id("id_lname_conf");
	    By c_fNameLocator =  By.id("id_fname_conf");
	    
	    By c_termsLocator =  By.id("id_terms_conf");
	    By c_stateLocator =  By.id("id_state_conf");
	    By c_genderLocator =  By.id("id_gender_conf");
	 
	    
	protected ConfirmationPage() {
		super(PAGE_URL);
	}
	
	public  boolean verify_submit_form (String first_name, String last_name, String email, String phone, 
			boolean isAgreedToTerms, String gender, String state, WebDriver driver) throws InterruptedException {
		  boolean isAllmatch = false;  
		  String c_fname =  getElementText(c_fNameLocator, driver);
		  String c_lname =  getElementText(c_lNameLocator, driver);
		  String c_phone =  getElementText(c_phoneLocator, driver);
		  String c_email =  getElementText(c_emailLocator, driver);
		  String c_gender = getElementText(c_genderLocator, driver);
		  String c_agreed = getElementText(c_termsLocator, driver);
		  String c_state =  getElementText(c_stateLocator, driver);
		  //verify_confirmation(first_name,last_name,email,phone);
		  String strAgreed = isAgreedToTerms ? "Agreed" : "";
		  //
/*		  System.out.println(first_name+","+c_fname);
		  System.out.println(last_name+","+c_lname);
		  System.out.println(email+","+c_email);
		  System.out.println(phone+","+c_phone);
		  System.out.println(strAgreed+","+c_agreed);
		  System.out.println(gender+","+c_gender);
		  System.out.println(state+","+c_state);*/
		  ///
		  if((first_name.equals(c_fname.trim())) && 
			 (last_name.equals(c_lname.trim())) && 
			 (email.equals(c_email.trim())) && 
			 (phone.equals(c_phone.trim())) &&
			 (strAgreed.equalsIgnoreCase(c_agreed.trim())) && 
			 (gender.equalsIgnoreCase(c_gender.trim())) &&
			 (state.equals(c_state))
				  ) 
		  {isAllmatch = true;}
		 
		  return isAllmatch;
		  
	} // **end
		
}
