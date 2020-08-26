package TradeMeStepDefs;

import TradeMePages.TradeMeBase;
import TradeMeUtils.TradeMeUtils;
import cucumber.api.java.en.Given;

public class HomeStepDefs extends TradeMeBase {

    @Given("^I launch Trade me home page$")
    public void i_launch_Trade_me_home_page() throws Throwable {
        System.out.println("===============================>>>"+TradeMeUtils.getConfigValue("url"));
        tradeMeHomePage.getPage(TradeMeUtils.getConfigValue("url"));
        waitForPageToBeReady();
    }

    @Given("^I select \"([^\"]*)\" category$")
    public void i_select_category(String category) throws Throwable {
        tradeMeHomePage.chooseCategoryAs(category);
    }

}
