package TradeMeUtils;

import TradeMePages.TradeMeBase;
import TradeMeStepDefs.TradeMeStepDefs;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TradeMeUtils extends TradeMeBase {

    public static String getConfigValue(String key) {
        Properties config = new Properties();

        try {
            String filename = "";
            if (System.getProperty("os.name").contains("Win")) {
                filename = "properties\\config";
            } else {
                filename = "properties/config";
            }
            config.load(new FileInputStream("target/classes/" + filename));
        } catch (Throwable t) {
            System.out.print("Issue loading properties file");
            t.printStackTrace();
        }
        return config.getProperty(key);
    }

    public static void fnScreenshot(WebDriver driver) throws IOException {
            TradeMeStepDefs.myScenario.write("Current Page URL is " + getDriver().getCurrentUrl());
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            TradeMeStepDefs.myScenario.embed(screenshot, "image/png");
    }

    public static void fnHighlightMe(WebDriver driver, WebElement element) throws InterruptedException {
        //Creating JavaScriptExecuter Interface
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int iCnt = 0; iCnt < 2; iCnt++) {
            //Execute javascript
            js.executeScript("arguments[0].style.border='4px groove green'", element);
            Thread.sleep(300);
            js.executeScript("arguments[0].style.border=''", element);
            Thread.sleep(500);
        }
    }

    public static void waitHighlightandClick(WebElement element) {
        waitForElementVisibility(element);
//        try {
//            fnHighlightMe(getDriver(),element);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        waitForAnimation(element);
        waitForElementBeClickable(element);
        element.click();
        if (System.getProperty("browser") == "ie") {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void waitForAnimation(WebElement element) {
        while (element.getClass().getName().toLowerCase().contains("ng-animate")) {
            System.out.println(element.getClass().getName().toLowerCase());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
//            waitForElementVisibility(element);
            WebDriverWait wait = new WebDriverWait(getDriver(), 5);
            wait.until(ExpectedConditions.visibilityOf(element));
            if (element.isDisplayed()
                    || element.isEnabled())
                flag = true;
        } catch (NoSuchElementException e) {
            flag = false;
        } catch (StaleElementReferenceException e) {
            flag = false;
        } catch (Exception e) {
//            System.out.println(e.getMessage());
        }
        return flag;
    }

    public static void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void clearandSendKeysWhenReady(WebElement element, String data) {
        waitForElementVisibility(element);
        element.clear();
        element.sendKeys(data);
    }
}
