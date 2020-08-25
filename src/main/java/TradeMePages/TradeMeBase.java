package TradeMePages;


import TradeMeUtils.Log;
import TradeMeUtils.TradeMeUtils;
import TradeMeUtils.UserProfile;
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
        InitializePages();
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
            case "saucechrome":
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setCapability("platform", "Windows 10");
//                caps.setCapability("version", "46.0");
                caps.setCapability("version", "latest");
                caps.setCapability("name", "IOP Daily Regression Suite");
                caps.setCapability("extendedDebugging", "true");
                caps.setCapability("screenResolution", "1600x1200");
                caps.setCapability("build", "1.01");
                this.driver = new RemoteWebDriver(new URL(URL), caps);
                this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            case "remotechrome":
                cap = DesiredCapabilities.chrome();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
                driver.manage().window().maximize();
                break;
            case "sauceedge":
                DesiredCapabilities caps1 = DesiredCapabilities.edge();
                caps1.setCapability("platform", "Windows 10");
//                caps.setCapability("version", "46.0");
                caps1.setCapability("version", "latest");
                caps1.setCapability("name", "IOP Daily Regression Suite");
                caps1.setCapability("extendedDebugging", "true");
                caps1.setCapability("screenResolution", "1600x1200");
                caps1.setCapability("build", "1.01");
                this.driver = new RemoteWebDriver(new URL(URL), caps1);
                this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            case "saucefirefox":
                DesiredCapabilities caps2 = DesiredCapabilities.firefox();
                caps2.setCapability("platform", "Windows 10");
//                caps.setCapability("version", "46.0");
                caps2.setCapability("version", "latest");
                caps2.setCapability("name", "IOP Daily Regression Suite");
                caps2.setCapability("extendedDebugging", "true");
                caps2.setCapability("screenResolution", "1600x1200");
                caps2.setCapability("build", "1.01");
                this.driver = new RemoteWebDriver(new URL(URL), caps2);
                this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            case "saucemac":
                DesiredCapabilities caps3 = DesiredCapabilities.safari();
                caps3.setCapability("platform", "macOS 10.13");
                caps3.setCapability("version", "11.1");
                caps3.setCapability("name", "IOP Daily Regression Suite");
                caps3.setCapability("extendedDebugging", "true");
                caps3.setCapability("screenResolution", "1600x1200");
                caps3.setCapability("build", "1.01");
                this.driver = new RemoteWebDriver(new URL(URL), caps3);
                this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                break;
            default:
                fail("Unknown browser");
        }
        return this.driver;
    }

}
