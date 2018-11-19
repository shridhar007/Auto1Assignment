package auto1.qa.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LandingPage extends BasePage 
{

	public LandingPage(WebDriver driver)
	{
		super(driver);	
	}
	
	public LandingPage applyRegistrationFilter() 
	{
	
		//Get All Filters. 
		List<WebElement> filters = localDriver.findElements(By.cssSelector("[class='root___1ZGR8']"));
		for (WebElement element : filters) {

			if(element.getText().startsWith("Erstzulassung") || element.getText().startsWith("registration")) 
			{
				element.click();
				WebDriverWait wt = new WebDriverWait(localDriver, 10);
				WebElement yearDropDown =  wt.until(ExpectedConditions.visibilityOfElementLocated(By.name("yearRange.min")));
				Select yearDropDownVals = new Select(yearDropDown);
				yearDropDownVals.selectByVisibleText("2015");
				return new LandingPage(localDriver);
			}			
		}
		
		return null;
	}

	public boolean assertRegistrationFilter()
	{
		boolean returnVal = false;
		WebDriverWait wt = new WebDriverWait(localDriver, 15);
		WebElement appliedFilter = wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='button___3BISJ filterButton___3dGDs btn btn-default']"))); 				
		returnVal = appliedFilter.isDisplayed();
		return returnVal;
	}

	public LandingPage applyDecendingPriceFilter()
	{
		WebDriverWait wt = new WebDriverWait(localDriver, 15);
		WebElement sortByFilter =  wt.until(ExpectedConditions.visibilityOfElementLocated(By.name("sort")));
		Select sortByFilterVals = new Select(sortByFilter);
		sortByFilterVals.selectByIndex(2);
		return new LandingPage(localDriver);		
	}

	public boolean verifySearchResultsFilterByRegistration()
	{
		boolean returnVal = false;
		boolean staleElement = true;
		ArrayList<String> mmYYYYVals = new ArrayList<String>();
		
		for(int i = 0 ; i<5 && staleElement; i++) 
		{		
			try {
				//Get the search table.
				WebElement searchTable = localDriver.findElement(By.cssSelector("[data-qa-selector='ad-items']"));;
		 				
				//Get all rows in table 
				List<WebElement> searchTableRows = searchTable.findElements(By.cssSelector("[class='item___T1IPF']"));
				
				//Get vehicle specification from result tiles. 
				for (WebElement result : searchTableRows) 
				{		
					WebElement specTile = result.findElement(By.cssSelector("[data-qa-selector='spec-list']"));
					
					//Get all the li elements from specification tile. 			
					List<WebElement> specValues = specTile.findElements(By.tagName("li"));			
					
					//Get Registration specification.
					WebElement yearField = specValues.get(0);					
					mmYYYYVals.add(yearField.getText().replace("•", "").replaceAll("\\s+",""));
				}
				staleElement = false;
				
			} catch (StaleElementReferenceException e) {
				
				staleElement = true;
			}
			
		}
		
		for (Iterator<String> iterator = mmYYYYVals.iterator(); iterator.hasNext();) 
		{
			String str = (String) iterator.next();
			int yearVal = Integer.parseInt(str.split("/")[1]);
			
			if(yearVal >=2015)
				returnVal = true;
			else
			{
				returnVal = false;
				return returnVal;
			}
		}
				
		return returnVal;
	}
	
	public boolean verifySortingByDecendingPrice() 
	{
		boolean returnVal = false;
		boolean staleElement = true;
		ArrayList<Float> priceArrayList = new ArrayList<Float>();
		
		for(int i = 0 ; i<5 && staleElement; i++) 
		{		
			try 
			{
				//Get the search table.
				WebElement searchTable = localDriver.findElement(By.cssSelector("[data-qa-selector='ad-items']"));;
		 				
				//Get all rows in table 
				List<WebElement> searchTableRows = searchTable.findElements(By.cssSelector("[class='item___T1IPF']"));
				
				//GetPrice
				for(WebElement rows : searchTableRows)
				{
					WebElement price = rows.findElement(By.cssSelector("[data-qa-selector='price']"));
					String priceTxt = price.getText().replace("€", "").replaceAll("\\s+","");
					priceArrayList.add(Float.parseFloat(priceTxt));
				}
				
				staleElement = false;
			}
			
			catch (StaleElementReferenceException e) 
			{			
				staleElement = true;
			}
		}
		
		for (int i = 0; i < priceArrayList.size()-1 ; i++) 
		{
			if(priceArrayList.get(i) >= priceArrayList.get(i+1))
			{
				returnVal = true;
				continue;
			}				
			else 
			{
				System.out.println("First: " + priceArrayList.get(i) + " Second: " +priceArrayList.get(i+1));
				returnVal = false;
				break;
			}
		}
		
		return returnVal;
	}
}
