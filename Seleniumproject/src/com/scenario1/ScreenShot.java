package com.scenario1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

/*public class ScreenShot
{
	public static void getScreenshot(WebDriver driver, String name ) {
	  
	
		TakesScreenshot ts=(TakesScreenshot) driver;
	File temp=ts.getScreenshotAs(OutputType.FILE);
	driver.get("https://www.google.com");
	File dest=new File(System.getProperty("D://Eclipse_Workspace//Seleniumproject//ExcelData//ScreenShot")+name+System.currentTimeMillis()+".png");
	try {
		FileHandler.copy(temp,dest);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	driver.close();
		
	}
}*/
public class ScreenShot {
    // Static method to take a screenshot
    public static void getScreenshot(WebDriver driver, String name) {
        // Capture the screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File temp = ts.getScreenshotAs(OutputType.FILE);
        
        //driver.get("https://www.google.com");
        // Define the destination file path
        String filePath = System.getProperty("D:\\Eclipse_Workspace\\Seleniumproject\\ScreenShot") + "/screenshots/" + name + "_" + System.currentTimeMillis() + ".png";
        File dest = new File(filePath);

        try {
            // Copy the temporary screenshot file to the destination
            FileUtils.copyFile(temp, dest);
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
        //driver.close();
    }
}


	

