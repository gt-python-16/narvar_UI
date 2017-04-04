package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    protected static final String BROWSER = System.getProperty("BROWSER", "chrome");

    public static WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setupWebDriver() throws MalformedURLException {
        setupLocalDriver();
    }

    private void setupLocalDriver() {
       driver = BrowserFactory.getBrowser(BROWSER);
    }



    @AfterTest(alwaysRun = true)
    public void suiteTearDown() {
        driver.quit();
    } 
    
}


