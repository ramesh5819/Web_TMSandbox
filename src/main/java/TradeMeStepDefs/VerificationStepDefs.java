package TradeMeStepDefs;

import TradeMeUtils.TradeMeUtils;
import cucumber.api.java.en.Then;

public class VerificationStepDefs extends TradeMeUtils {

    @Then("^I should be able to see the car details$")
    public void i_should_be_able_to_see_the_car_details() throws Throwable {
        tradeMeUsedCarListingPage.checkVisibilityOfUsedCarDetails();
    }

}
