package com.Scenario2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	//constructor
	LoginPage(WebDriver driver)

	{
		this.driver=driver;
		PageFactory.initElements(driver, this);//mandtory
		}
	//Locator
	
	@FindBy (xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")
	WebElement  txt_username;
	
	@FindBy (xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")
	WebElement  txt_password;
	
	@FindBy (xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
	WebElement  btn_login;
	

// Action class
public void setUserName(String user) {
	//driver.findElement(txt_username_loc).sendKeys(user);
	txt_username.sendKeys(user);
	
	
}
public void setPassword(String pwd) {
	//driver.findElement(txt_password_loc).sendKeys(pwd);
	txt_password.sendKeys(pwd);
}
public void clickLoginBtn()
{
	//driver.findElement(btn_login_loc).click();
	btn_login.click();
	}

}


