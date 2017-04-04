package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserFactory {
	
	static WebDriver driver;

	//public static void setWebDriver(String browser) throws IOException {
        //Logger logger = Logger.getLogger("");
       // logger.setLevel(Level.OFF);
        String driverPath = ""; // project folder with browsers drivers
	
	public static WebDriver getBrowser(String browserName) {
		browserName = browserName.toLowerCase();
		
		if(browserName.equals("chrome"))
			return getChromeInstance();
		if(browserName.equals("safari"))
			return getSafariInstance();
		if(browserName.equals("htmlunit"))
			return getHtmlUnitInstance();
		else
			return getFFInstance();
	}

    
	private static FirefoxDriver getFFInstance() {
		System.setProperty("webdriver.gecko.driver", "./src/main/resources/webdrivers/mac/geckodriver.sh");
		FirefoxDriver driver = new FirefoxDriver();
 		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 		 driver.manage().window().maximize();
 		 return driver;
		//return new FirefoxDriver();
	}
	
	private static ChromeDriver getChromeInstance() {
     	System.setProperty("webdriver.chrome.driver","./src/main/resources/webdrivers/mac/chromedriver");
      	System.setProperty("webdriver.chrome.silentOutput", "true");
      	ChromeOptions option = new ChromeOptions();
      	option.addArguments("-start-fullscreen"); 
      	ChromeDriver driver = new ChromeDriver(option);
      	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
		
/*		System.setProperty("webdriver.chrome.driver", "resources//chromedriver");
		return new ChromeDriver();*/
	}
	
	private static SafariDriver getSafariInstance() {
      	SafariOptions options = new SafariOptions();
      	options.setUseCleanSession(true);
      	options.setPort(55555);
      	SafariDriver driver = new SafariDriver(options);
      	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      	driver.manage().window().maximize();
		return driver;
		
		//return new SafariDriver();
	}
	
	private static HtmlUnitDriver getHtmlUnitInstance() {
		HtmlUnitDriver driver = new HtmlUnitDriver();
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		return driver;
	}
	
    public void setBrowser(WebDriver driver) {
        this.driver = driver;
    }
}
