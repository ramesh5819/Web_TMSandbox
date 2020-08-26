package TradeMePages;

import TradeMeUtils.TradeMeUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class TradeMeUsedCarListingPage extends TradeMeBase {
    final WebDriver driver;

    @FindBy(xpath = ".//label[text()=\"Kilometres\"]/../following-sibling::*/span")
    public static WebElement odometer;
    @FindBy(xpath = ".//label[text()=\"Body\"]/../following-sibling::*/span")
    public static WebElement bodyStyle;
    @FindBy(xpath = ".//label[text()=\"Seats\"]/../following-sibling::*/span")
    public static WebElement seats;
    @FindBy(xpath = ".//label[text()=\"Fuel type\"]/../following-sibling::*/span")
    public static WebElement fuelType;
    @FindBy(xpath = ".//label[text()=\"Engine\"]/../following-sibling::*/span")
    public static WebElement engine;
    @FindBy(xpath = ".//label[text()=\"Transmission\"]/../following-sibling::*/span")
    public static WebElement transmission;
    @FindBy(xpath = ".//label[text()=\"Import history\"]/../following-sibling::*/span")
    public static WebElement importHistory;
    @FindBy(xpath = ".//label[text()=\"Registration expires\"]/../following-sibling::*/span")
    public static WebElement registrationExpires;
    @FindBy(xpath = ".//label[text()=\"WoF expires\"]/../following-sibling::*/span")
    public static WebElement wofExpires;
    @FindBy(xpath = ".//label[text()=\"Model detail\"]/../following-sibling::*/span")
    public static WebElement moreDetail;

    public TradeMeUsedCarListingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkVisibilityOfUsedCarDetails() {
        waitForPageToBeReady();
        assertTrue("Odometer reading is not visible",TradeMeUtils.isElementPresent(odometer));
        assertTrue("Body style is not visible",TradeMeUtils.isElementPresent(bodyStyle));
        assertTrue("Number of Seats is not visible",TradeMeUtils.isElementPresent(seats));
        assertTrue("Fuel is not visible",TradeMeUtils.isElementPresent(fuelType));
        assertTrue("Engine type is not visible",TradeMeUtils.isElementPresent(engine));
        assertTrue("Transmission is not visible",TradeMeUtils.isElementPresent(transmission));
        assertTrue("Import History is not visible",TradeMeUtils.isElementPresent(importHistory));
        assertTrue("Registration expiration date is not visible",TradeMeUtils.isElementPresent(registrationExpires));
        assertTrue("Wof Expiry date is not visible",TradeMeUtils.isElementPresent(wofExpires));
        assertTrue("More detail is not visible",TradeMeUtils.isElementPresent(moreDetail));
    }
}
