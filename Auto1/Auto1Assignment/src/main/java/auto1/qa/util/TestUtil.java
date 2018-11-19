package auto1.qa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtil
{
	static final int PAGE_LOAD_TIMEOUT = 20;
	static final int IMPLICIT_WAIT = 5;
	
	public WebDriver getWebDriverIntance(String browserName) throws Exception 
	{
		WebDriver driver;
		String executingDirPath = System.getProperty("user.dir");
		
		switch(browserName.toUpperCase()) 
		{
			case "CHROME":
				System.setProperty("webdriver.chrome.driver", executingDirPath + "\\resources\\chromedriver.exe");				
				driver = new ChromeDriver();				
				break;
							
			default: throw new Exception("Invalid browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;
		
	}	

	public static void clickByJS(WebDriver wd, WebElement element) {
		
		JavascriptExecutor executor = (JavascriptExecutor)wd;
		executor.executeScript("arguments[0].click();", element);
	}

	public static void waitForPageToLoad(WebDriver driver)
	{
		new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
}