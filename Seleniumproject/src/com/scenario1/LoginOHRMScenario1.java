package com.scenario1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class LoginOHRMScenario1 extends ScreenShot
{
	ScreenShot ss = new ScreenShot();
	String fPath = "ExcelData\\Data.csv";
	File file;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	XSSFCellStyle style;
	XSSFFont font;
	int index = 1;
	static WebDriver driver;
		

@Test(dataProvider = "getLoginData")

public void login(String un, String ps) {
	//Passing the data to test case
	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(un);
	driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(ps);
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ss.getScreenshot(driver, "LoginPage");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	Assert.assertTrue(driver.getCurrentUrl().contains("dash"));
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ss.getScreenshot(driver, "LoginPage");
	
	
}
@AfterMethod

public void logout()  {
	
	cell = sheet.getRow(index).getCell(2);
	
	style = wb.createCellStyle();
	font = wb.createFont();
	
	if(driver.getCurrentUrl().contains("dashboard"))
	{
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span/i")).click();
		driver.findElement(By.partialLinkText("Log")).click();
		System.out.println("Login successful");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss.getScreenshot(driver, "LoginPage");
		
		font.setColor(HSSFColorPredefined.GREEN.getIndex());
		font.setBold(true);
		style.setFont(font);
		cell.setCellStyle(style);
		
		cell.setCellValue("Pass");
		ss.getScreenshot(driver, "LoginPage");
		
	}
	else
	{
		System.out.println("Invalid credentials");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss.getScreenshot(driver, "LoginPage");
		
		font.setColor(HSSFColorPredefined.RED.getIndex());
		font.setItalic(true);
		style.setFont(font);
		cell.setCellStyle(style);
		
		cell.setCellValue("Fail");
		
	}
	index++;
}

@DataProvider
public Object[][] getLoginData()   {
	//Reading the data from excel file
	int rows = sheet.getPhysicalNumberOfRows();
	String[][]loginData = new String[rows-1][2];
	
	for(int i = 0; i < rows-1; i++)
	{
		row = sheet.getRow(i+1);
		for(int j = 0; j < 2; j++)
		{
			cell = row.getCell(j);
			loginData[i][j] = cell.getStringCellValue();
		}
	}
	return loginData;
}

@BeforeTest 
public void beforeTest() throws IOException {
	file = new File(fPath);
	fis = new FileInputStream(file);
	wb = new XSSFWorkbook(fis);
	sheet = wb.getSheetAt(0);
	
	fos = new FileOutputStream(file);	
	
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	
}

@AfterTest
public void afterTest() throws IOException {
	wb.write(fos);
	wb.close();
	fis.close();
	
	driver.close();
}

}