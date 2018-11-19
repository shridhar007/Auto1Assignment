package auto1.qa.testautomation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import auto1.qa.pages.LandingPage;
import auto1.qa.util.TestUtil;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinition 
{

	WebDriver testCaseDriver;
	LandingPage landingPageObj;
	
	@Before
	public void initializeDriver() throws Exception 
	{
		testCaseDriver = new TestUtil().getWebDriverIntance("Chrome");		
	}

	@Given("^user can navigate to www.autohero.com/de/search/$")
	public void navigateToAuto1() throws InterruptedException 
	{
		testCaseDriver.get("https://www.autohero.com/de/search/");		
	}
	
	@Then("^user applies first registration filter FROM 2015$")
	public void applyRegistrationFilterFrom()
	{
		landingPageObj = new LandingPage(testCaseDriver);
		landingPageObj = landingPageObj.applyRegistrationFilter();
	}
	
	@Then("^user applies filter$")
	public void applyFilter()
	{		
		//Assert that filters is applied
		Assert.assertEquals(true,landingPageObj.assertRegistrationFilter());
	}
	
	@Then("^user sorts cars by price decending$")
	public void sortCarsByPriceDecending()
	{
		landingPageObj = landingPageObj.applyDecendingPriceFilter();
	}
	
	@And("^all cars are filtered by registration from 2015 onwards$")
	public void verifyCarsFilterByRegistration() 
	{
		Assert.assertEquals(true, landingPageObj.verifySearchResultsFilterByRegistration());
	}
	
	@And("^all cars are sorted by price decending$")
	public void verifyCarsSortedByDecendingPrice() 
	{
		Assert.assertEquals(true, landingPageObj.verifySortingByDecendingPrice());
	}
		
	@After
	public void cleanUpCode() 
	{
		testCaseDriver.close();
		testCaseDriver.quit();
	}
}
