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
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    //protected static final String WEB_SERVER = System.getProperty("WEB_SERVER", "http://alex.academy/exercises/signup/v1/");
    protected static final String BROWSER = System.getProperty("BROWSER", "chrome");
//    protected static final boolean REMOTE_DRIVER = Boolean.valueOf(System.getProperty("REMOTE_DRIVER", "false"));
//    protected static final String SELENIUM_HOST = System.getProperty("SELENIUM_HOST", "localhost");
//    protected static final int SELENIUM_PORT = Integer.valueOf(System.getProperty("SELENIUM_PORT", "4444"));

    public static WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setupWebDriver() throws MalformedURLException {
        setupLocalDriver();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private void setupLocalDriver() {
       driver = BrowserFactory.getBrowser(BROWSER);
    }

    

//    @BeforeMethod(alwaysRun = true)
//    public void loadWebApplication() {
//        driver.get(WEB_SERVER);
//    }

/*    @AfterMethod(alwaysRun = true)
    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }*/

    @AfterTest(alwaysRun = true)
    public void suiteTearDown() {
        driver.quit();
    }
}


