package SleniumDemo;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class handlingAlart {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Set the ChromeDriver path
       // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize the WebDriver and WebDriverWait
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open the DemoBlaze website
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
    }

   @Test(priority = 1)
    public void testRegister() {
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username"))).sendKeys("TestUser123");
        driver.findElement(By.id("sign-password")).sendKeys("Test@123");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

        // Wait for alert and verify
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "Sign up successful.");
        driver.switchTo().alert().accept();
    }

    @Test(priority = 2)
    public void testLogin() {
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("TestUser123");
        driver.findElement(By.id("loginpassword")).sendKeys("Test@123");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        // Verify user is logged in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        String welcomeText = driver.findElement(By.id("nameofuser")).getText();
        Assert.assertTrue(welcomeText.contains("Welcome"), "Login failed!");
    }

    @Test(priority = 3)
    public void testNavigation() {
        // Navigate to Phones category
        driver.findElement(By.linkText("Phones")).click();
        Assert.assertTrue(driver.findElement(By.className("card-title")).isDisplayed(), "Phones page not loaded!");

        // Navigate to Laptops category
        driver.findElement(By.linkText("Laptops")).click();
        Assert.assertTrue(driver.findElement(By.className("card-title")).isDisplayed(), "Laptops page not loaded!");

        // Navigate to Monitors category
        driver.findElement(By.linkText("Monitors")).click();
        Assert.assertTrue(driver.findElement(By.className("card-title")).isDisplayed(), "Monitors page not loaded!");
    }

    @Test(priority = 4)
    public void testSearchFunctionality() {
        // Search for a product (no search bar available; navigate to item)
        driver.findElement(By.linkText("Laptops")).click();
        driver.findElement(By.linkText("Sony vaio i5")).click();

        // Verify product details page
        WebElement productName = driver.findElement(By.className("name"));
        Assert.assertEquals(productName.getText(), "Sony vaio i5", "Product details page not loaded!");
    }

    @Test(priority = 5)
    public void testAddToCart() {
        driver.findElement(By.linkText("Add to cart")).click();

        // Wait for and accept alert
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        // Verify item in the cart
        driver.findElement(By.id("cartur")).click();
        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Sony vaio i5']")));
        Assert.assertNotNull(cartItem, "Item not added to cart!");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}

/*public class handlingAlart {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://demoqa.com/alerts");
		
		Alert alt;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].click()", driver.findElement(By.id("alertButton")));
		alt = driver.switchTo().alert();		//Take you on Alert
		System.out.println(alt.getText());
		alt.accept();		//Click on Ok button
		
		js.executeScript("arguments[0].click()", driver.findElement(By.id("timerAlertButton")));
		Thread.sleep(5000);
		alt = driver.switchTo().alert();
		System.out.println(alt.getText());
		alt.accept();
		
		js.executeScript("window.scrollBy(0, 400)", "");
		
		driver.findElement(By.id("confirmButton")).click();
		alt = driver.switchTo().alert();
		System.out.println(alt.getText());
		alt.dismiss();
		System.out.println(driver.findElement(By.id("confirmResult")).getText());
		
		driver.findElement(By.id("promtButton")).click();
		alt = driver.switchTo().alert();
		alt.sendKeys("Nayana");
		alt.accept();
		System.out.println(driver.findElement(By.id("promptResult")).getText());
		
		driver.close();
	}*/


