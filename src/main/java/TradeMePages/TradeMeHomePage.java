package TradeMePages;

import TradeMeUtils.TradeMeUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TradeMeHomePage extends TradeMeBase{
    final WebDriver driver;

    @FindBy(id = "SearchTabs1_MotorsLink")
    public static WebElement motorsCategoryButton;

    public TradeMeHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseCategoryAs(String category) {
        switch (category) {
            case "Motors":{
                chooseMotorsCategory();
                break;
            }

        }
    }

    private void chooseMotorsCategory() {
        TradeMeUtils.waitHighlightandClick(motorsCategoryButton);
        waitForPageToBeReady();
    }

    public static void clickSignout() {
        //Write code to click Sign out
    }
}
