package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Login extends Base {
	
	public WebDriver driver;
	LoginPage loginpage;
	
	/*public Login()
	{
		super();
	} */   // this constructor calls the super class constructor for load properties file
	
	
	@BeforeMethod
	public void setup()
	{
		loadPropertiesFile();   			// this method will call the method of parent class same method.
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage=new HomePage(driver);
		loginpage=homepage.navigateToLoginPage();		
	}

	@Test (priority=1, dataProvider="validCredentialsSupplyer")
	public void verifyLoginWithValidCredentials(String email,String password)
	{		
		AccountPage accountpage = loginpage.Login(email, password);			
		Assert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your accout information is not displaying");
		
	}
	@DataProvider(name="validCredentialsSupplyer")    // here we gave a name to data provider hence, same name we used in verifyLoginWithValidCredentials method if we don't provide name to data provide then we can direct give the method name in the above method
	public Object[][] supplyTestData() {
		
		Object[][] data=Utilities.getTestDataFromExcel("Login");
		/*Object[][] data= {{"rajeshdemoaccount@gmail.com","God@1010"},
				{"asishdemoaccount@gmail.com","Good@1010"},
				{"prakashdemoaccount@gmail.com","Goood@1010"}};*/
		return data;
	}
	@Test (priority=2)
	public void verifyLoginWithInvalidCredentials()
	{	
		loginpage.Login(Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
				
		//String actualWarningMessage=loginpage.retriveEmailPasswordNotMatchingWarningMessageText();
		//String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
		// above code removed and update directly inside assert
		
		Assert.assertEquals(loginpage.retriveEmailPasswordNotMatchingWarningMessageText(),dataProp.getProperty("emailPasswordNoMatchWarning"));
		//		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");
		
	}
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{	
		loginpage.Login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
				
		//String actualWarningMessage=loginpage.retriveEmailPasswordNotMatchingWarningMessageText();
		//String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");	
		
		Assert.assertEquals(loginpage.retriveEmailPasswordNotMatchingWarningMessageText(),dataProp.getProperty("emailPasswordNoMatchWarning"));
		//		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");
		
	}
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{	
		// priority 1, 2 and 3 has optimized the code and line reduced
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		
		//String actualWarningMessage=loginpage.retriveEmailPasswordNotMatchingWarningMessageText();
		//String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");	
		
		Assert.assertEquals(loginpage.retriveEmailPasswordNotMatchingWarningMessageText(),dataProp.getProperty("emailPasswordNoMatchWarning"));
		//		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");

	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials()
	{		
		loginpage.clickOnLoginButton();
		
		//String actualWarningMessage=loginpage.retriveEmailPasswordNotMatchingWarningMessageText();
		//String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");	
		
		Assert.assertEquals(loginpage.retriveEmailPasswordNotMatchingWarningMessageText(),dataProp.getProperty("emailPasswordNoMatchWarning"));
		//		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warning message is not displayed");

	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
