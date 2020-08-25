package TradeMeStepDefs;

import TradeMePages.TradeMeBase;
import TradeMePages.TradeMeHomePage;
import TradeMeUtils.Log;
import TradeMeUtils.TradeMeUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TradeMeStepDefs extends TradeMeBase {
    public static Scenario myScenario;

    @Before
    public void start() {
        Setup();
        DOMConfigurator.configure("log4j.xml");
        Log.info("Logs Initiated");
    }

    @Before
    public void embedScreenshotStep(Scenario scenario) {
        myScenario = scenario;
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        String rawFeatureName = scenario.getId().split(";")[0].replace("-", " ").replace("service tests", "");
    }

    @After
    public void tearDown(){

    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshotAndQuit(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + getDriver().getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }

        try {
            TradeMeHomePage.clickSignout();
        } catch (Exception e) {
            Log.info("Do nothing for now.");
        }


        Log.info("Driver shutting down.");
        getDriver().close();
        getDriver().quit();
    }
}
