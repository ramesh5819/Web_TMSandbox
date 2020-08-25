

import SkyUtils.SkyUtils;
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

        glue = "SkyStepDefinitions",
        monochrome = false,

        plugin = { "html:target/cucumber-html-report",
                "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
                "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" }
        ,tags = {"@Web-211"}

)
public class RunSkyWebTest {

    @BeforeClass
    public static void initiateTestRun() {

    }

    @AfterClass
    public static void finalizeTestRun() throws IOException {

    }
}