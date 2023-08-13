package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends Base {
	
	WebDriver driver;
	SearchPage searchpage;
	HomePage homepage;
	
	@BeforeMethod
	public void setup()
	{
		loadPropertiesFile();   			// this method will call the method of parent class same method.
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		homepage=new HomePage(driver);
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct()
	{
		searchpage=homepage.searchForAProduct(dataProp.getProperty("validProduct"));
		/*
		homepage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		searchpage = homepage.clickOnSearchButton();
		*/
		Assert.assertTrue(searchpage.dispalyStatusOfHPValidProduct(),"valid product HP is not displayed in search result");
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct()
	{
		searchpage=homepage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		/*
		homepage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		searchpage=homepage.clickOnSearchButton();
		*/		
		//String actualSearchMessage=searchpage.retriveNoProductMessageText();
		Assert.assertEquals(searchpage.retriveNoProductMessageText(), dataProp.getProperty("noProductTextInSearchResult"),"no product message in search result is not displayed");
	}
	
	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct()
	{
		
		searchpage=homepage.clickOnSearchButton();
				
		//String actualSearchMessage=searchpage.retriveNoProductMessageText();
		Assert.assertEquals(searchpage.retriveNoProductMessageText(), dataProp.getProperty("noProductTextInSearchResult"),"no product message in search result is not displayed");
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
