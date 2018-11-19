package auto1.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import auto1.qa.util.TestUtil;

public class BasePage {
	
	protected WebDriver localDriver;
	
	BasePage(WebDriver driver)
	{
		localDriver = driver;
		PageFactory.initElements(localDriver, this);
		TestUtil.waitForPageToLoad(localDriver);		
	}

}