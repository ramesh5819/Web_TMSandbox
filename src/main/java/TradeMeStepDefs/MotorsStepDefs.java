package TradeMeStepDefs;

import TradeMePages.TradeMeBase;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class MotorsStepDefs extends TradeMeBase {

    @When("^I choose \"([^\"]*)\" under Motors$")
    public void i_choose_under_Motors(String subCategory) throws Throwable {
        tradeMeMotorsPage.chooseSubCategoryAs(subCategory);
    }

    @Then("^I should see at least one listing available$")
    public void i_should_see_at_least_one_listing_available() throws Throwable {
        tradeMeMotorsPage.checkIfListingsMoreThan(1);
    }

    @Then("^I should see that \"([^\"]*)\" category exists$")
    public void i_should_see_that_Kia_category_exists(String make) throws Throwable {
        tradeMeMotorsPage.validateExistanceOf(make);
    }

    @When("^I choose a used car$")
    public void i_choose_a_used_car() throws Throwable {
        tradeMeMotorsPage.clickUsedCar();
    }

}
