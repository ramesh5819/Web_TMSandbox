package TradeMeUtils;

import TradeMePages.TradeMeBase;
import TradeMeStepDefs.TradeMeStepDefs;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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
}
