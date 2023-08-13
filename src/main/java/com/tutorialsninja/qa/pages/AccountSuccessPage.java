package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	
	WebDriver driver;
	public AccountSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='row']//div[@id='content']//p")
	private WebElement accountSuccessPageheading;
	
	public String retriveAccountSuccessPageHeading() {
		String accountSuccessPageHeadingText=accountSuccessPageheading.getText();
		return accountSuccessPageHeadingText;
	}
}
