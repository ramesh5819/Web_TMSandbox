package TradeMePages;


import TradeMeUtils.Log;
import TradeMeUtils.TradeMeUtils;
import TradeMeUtils.UserProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TradeMeBase {
    static protected UserProfile userProfile;
    static protected TradeMeHomePage tradeMeHomePage;

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver WB) {
        this.driver = WB;
    }

    public void initializePages(){
        tradeMeHomePage = PageFactory.initElements(driver, TradeMeHomePage.class);
    }

    public WebDriver Setup() {
        setSystemProperties();
        try {
            InitiateDriver();
            System.out.println("Driver Initiated");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        initializePages();
        return driver;
    }

    private void setSystemProperties() {
        String path = System.getProperty("user.dir") + "/drivers/";
        if (System.getProperty("os.name").contains("Windows 10")) {
            System.setProperty("webdriver.edge.driver", path + "win/MicrosoftWebDriver.exe");
        }
        if (System.getProperty("os.name").contains("Win")) {
            System.setProperty("webdriver.chrome.driver", path + "win/chromedriver.exe");
            System.setProperty("webdriver.ie.driver", path + "win/IEDriverServer.exe");
            System.setProperty("webdriver.gecko.driver", path + "win/geckodriver.exe");
        } else if (System.getProperty("os.name").contains("Mac")) {
            System.out.println("==========================================================================Setting path for  drivers" + System.getProperty("os.name"));
            System.setProperty("webdriver.chrome.driver", path + "mac/chromedriver");
            System.setProperty("webdriver.gecko.driver", path + "mac/geckodriver");
        } else {
            System.setProperty("webdriver.chrome.driver", path + "linux/chromedriver");
            System.setProperty("webdriver.gecko.driver", path + "linux/geckodriver");
        }
    }

    private WebDriver InitiateDriver() throws Exception {
        Log.info("Initiating WebDriver");
        String browser;
        String _browser = System.getProperty("browser");
        if (null != _browser && !_browser.isEmpty()) {
            browser = _browser;
        } else {
            browser = TradeMeUtils.getConfigValue("default.browser");
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                //Creates the driver for Chrome browser
                ChromeOptions option = new ChromeOptions();
                option.setExperimentalOption("useAutomationExtension", false);
                option.addArguments("--disable-infobars");
                option.addArguments("--start-maximized");
                this.driver = new ChromeDriver(option);
                driver.manage().window().maximize();
                break;
            case "edge":
                //Creates the driver for Edge driver
                if (System.getProperty("os.name").contains("Windows 10")) {
                    this.driver = new EdgeDriver();
                    driver.manage().window().maximize();
                } else {
                    fail("Edge not supported on this OS");
                }
                break;
            case "ie":
                //Creates the driver for internet explorer
                if (System.getProperty("os.name").contains("Win")) {
                    DesiredCapabilities cap = new DesiredCapabilities();
                    cap.setCapability("ignoreZoomSetting", true);
                    cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    this.driver = new InternetExplorerDriver(cap);
                    // Set zoom to 100 percent
                    driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
                } else {
                    fail("ie is only supported on windows");
                }
                break;
            case "firefox":
                //Creates the driver for Mozilla Firefox
                DesiredCapabilities cap = DesiredCapabilities.firefox();
                cap.setCapability("marionette", true);
                this.driver = new FirefoxDriver(cap);
                driver.manage().window().maximize();
                break;
            case "safari":
                //Creates the driver for safari web browser
                SafariOptions options = new SafariOptions();
                options.setUseTechnologyPreview(true);
                this.driver = new SafariDriver(options);
                break;
            default:
                fail("Unknown browser");
        }
        return this.driver;
    }

    public void setUserProfile(String profile) {

        String file;
        if (getPageURL().contains("prelive")) {
            file = System.getProperty("user.dir") + "/src/main/resources/userProfiles/" + profile.replace(" ", "") + "Prelive.json";
        } else {
            file = System.getProperty("user.dir") + "/src/main/resources/userProfiles/" + profile.replace(" ", "") + ".json";
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            userProfile = mapper.readValue(new File(file), UserProfile.class);
        } catch (IOException e) {
            fail("User profile doesnt exist");
            System.out.println(e.getMessage());
        }
    }

    public void getPage(String _url) {
        Log.info("Getting URL: " + _url);
        driver.get(_url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public void getScreenShot() {
        try {
            TradeMeUtils.fnScreenshot(getDriver());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
