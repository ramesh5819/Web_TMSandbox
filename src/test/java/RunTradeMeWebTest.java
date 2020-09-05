

import TradeMeUtils.TradeMeUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.io.IOException;
import java.util.Date;

@RunWith(Cucumber.class)


@CucumberOptions(
        strict = true,

        features = "classpath:features",

        glue = "TradeMeStepDefs",
        monochrome = false,

        plugin = { "html:target/cucumber-html-report",
                "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
                "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" }
        ,tags = {"@usedcars1"}

)
public class RunTradeMeWebTest {

    @BeforeClass
    public static void initiateTestRun() {

    }

    @AfterClass
    public static void finalizeTestRun() throws IOException {

    }
}