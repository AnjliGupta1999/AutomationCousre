package SleniumDemo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.WebElement;

public class D01LaunchingBroswer {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("dash"));
		List<WebElement> lt = driver.findElements(By.tagName("a"));
		
		int sizelt = lt.size();
		System.out.println(sizelt);
		for(int i = 1;i <sizelt;i++) {
			WebElement a = lt.get(i);
			String c= a.getText();
			System.out.println(c);
		}
		driver.close();
	}

}
