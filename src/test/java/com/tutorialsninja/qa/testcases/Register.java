package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

public class Register extends Base {
	WebDriver driver;
	RegisterPage registerpage;
	AccountSuccessPage accountsuccesspage;
	
	@BeforeMethod
	public void setup()
	{
		loadPropertiesFile();   			// this method will call the method of parent class same method.
		driver=initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage=new HomePage(driver);
		registerpage=homepage.navigateToRegisterPage();
				
	}
	
	@Test(priority = 1)
	public void verifyRegisterAccountWithMandatoryField()
	{	
		String pass=Utilities.generateRandomPassword();
		accountsuccesspage=registerpage.registerWithMandatoryFields(Utilities.generateRandomFirstName(),Utilities.generateRandomLasName(),Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), pass);
		/*registerpage.enterFirstName(Utilities.generateRandomFirstName());
		registerpage.enterLastName(Utilities.generateRandomLasName());
		registerpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		
		String pass=Utilities.generateRandomPassword();
		registerpage.enterPassword(pass);
		registerpage.enterConfirmPassword(pass);
		
		registerpage.selectPrivaryPolicy();
		accountsuccesspage = registerpage.clickOnContinueButtone(); */
		
		
		//String actualSuccessHeading=accountsuccesspage.retriveAccountSuccessPageHeading();
		Assert.assertEquals(accountsuccesspage.retriveAccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success message not displayed");
	}
	@Test(priority = 2)
	public void verifyRegisterAccountWithAllField()
	{	
		String pass=Utilities.generateRandomPassword();
		accountsuccesspage=registerpage.registerWithAllFields(Utilities.generateRandomFirstName(),Utilities.generateRandomLasName(),Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), pass);
		/*registerpage.enterFirstName(Utilities.generateRandomFirstName());
		registerpage.enterLastName(Utilities.generateRandomLasName());
		registerpage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerpage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		
		String pass=Utilities.generateRandomPassword();
		registerpage.enterPassword(pass);
		registerpage.enterConfirmPassword(pass);
		
		registerpage.selectYesNewsLetterOption();
		registerpage.selectPrivaryPolicy();
		accountsuccesspage=registerpage.clickOnContinueButtone();*/
		
		//String actualSuccessHeading=accountsuccesspage.retriveAccountSuccessPageHeading();
		Assert.assertEquals(accountsuccesspage.retriveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success message not displayed");	}
	
	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress()
	{	
		String pass=Utilities.generateRandomPassword();
		accountsuccesspage=registerpage.registerWithAllFields(Utilities.generateRandomFirstName(),Utilities.generateRandomLasName(),prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), pass);

		/*registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmailAddress(prop.getProperty("validEmail"));
		registerpage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.selectYesNewsLetterOption();
		registerpage.selectPrivaryPolicy();
		registerpage.clickOnContinueButtone();*/
				
		//String actualWarning=registerpage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(registerpage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"warning message for duplicate email address not displayed");
	}
	
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutProvidingAnyDetails()
	{		
		registerpage.clickOnContinueButtone();
		
		Assert.assertTrue(registerpage.displayStatusOfWarningMessage(dataProp.getProperty("privacyPalicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"), dataProp.getProperty("passwordWaring")),"some warning messages are not matched");
		
		/*
		
		//String actualPrivacyPolicyWarning=registerpage.retrivePrivacyPolicyWarning();
		Assert.assertTrue(registerpage.retrivePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPalicyWarning")),"privacy policy warning message is not displayed");
		
		//String actualFirstNameWarning=registerpage.retriveFirstNameWarning();
		Assert.assertEquals(registerpage.retriveFirstNameWarning(), dataProp.getProperty("firstNameWarning"),"first name warning message is not displayed");
		
		//String actualLastNameWarning=registerpage.retriveLastNameWarning();
		Assert.assertEquals(registerpage.retriveLastNameWarning(), dataProp.getProperty("lastNameWarning"),"last name warning message is not displayed");
		
		//String actualEmailWarning=registerpage.retriveEmailWarning();
		Assert.assertEquals(registerpage.retriveEmailWarning(), dataProp.getProperty("emailWarning"),"email address warning message is not displayed");
		
		//String actualTelephoneWarning=registerpage.retriveTelephoneWarning();
		Assert.assertEquals(registerpage.retriveTelephoneWarning(), dataProp.getProperty("telephoneWarning"),"telephone warning message is not displayed");

		//String actualPasswordWarning=registerpage.retrivePasswordWarning();
		Assert.assertEquals(registerpage.retrivePasswordWarning(), dataProp.getProperty("passwordWaring")," password warning message is not displayed");
		
		*/
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
}
