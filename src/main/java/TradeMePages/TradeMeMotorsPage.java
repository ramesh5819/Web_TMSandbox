package TradeMePages;

import TradeMeUtils.TradeMeUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class TradeMeMotorsPage extends TradeMeBase {
    final WebDriver driver;

    @FindBy(id = "SiteHeader_SiteTabs_SubNavMotors_LinkUsedCars")
    public static WebElement usedCarsLink;

    @FindBy(xpath = ".//li[@class='tmm-search-card-list-view']")
    public static List<WebElement> listOfUsedCars;

    @FindBy(xpath = ".//a[text()=\"Kia\"]")
    public static WebElement kiaCategory;

    public TradeMeMotorsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseSubCategoryAs(String subCategory) {
        switch (subCategory){
            case "UsedCars":{
                clickUsedCarsLink();
                waitForPageToBeReady();
                break;
            }
        }
    }

    private void clickUsedCarsLink() {
        TradeMeUtils.waitHighlightandClick(usedCarsLink);
        waitForPageToBeReady();
    }

    public void checkIfListingsMoreThan(int min) {
        assertTrue("The number of listings are lesser than expected",listOfUsedCars.size()>=min);
    }

    public void validateExistanceOf(String make) {
        switch (make){
            case "Kia":{
                assertTrue(TradeMeUtils.isElementPresent(kiaCategory));
                break;
            }
        }
    }

    public void clickUsedCar() {
        Random random = new Random();
        //The below piece of code enables the scenario to click a different used car every
//        WebElement currentUsedCar = listOfUsedCars.get(random.nextInt(listOfUsedCars.size()-1));
        WebElement currentUsedCar = listOfUsedCars.get(8);
        TradeMeUtils.waitHighlightandClick(currentUsedCar);
        waitForPageToBeReady();
    }
}
