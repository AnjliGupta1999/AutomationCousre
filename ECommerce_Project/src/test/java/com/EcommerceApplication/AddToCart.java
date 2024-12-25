package com.EcommerceApplication;

import javax.print.DocFlavor.BYTE_ARRAY;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class AddToCart {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        // Initialize ChromeDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void signUp() {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.id("signin2")).click(); // Updated locator
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sign-username")));

        driver.findElement(By.id("sign-username")).sendKeys("Sejalgupta1");
        driver.findElement(By.id("sign-password")).sendKeys("Sejal123");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Sign up alert message: " + alert.getText());
        alert.accept();
    }

    @Test(priority = 2)
    public void signIn() {
        driver.findElement(By.id("login2")).click(); // Open login modal
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));

        driver.findElement(By.id("loginusername")).sendKeys("Sejalgupta1");
        driver.findElement(By.id("loginpassword")).sendKeys("Sejal123");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        // Validate login by checking the user greeting
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        System.out.println("Login successful!");
    }

    @Test(priority = 3)
    public void cart() {
        driver.findElement(By.xpath("//a[text()='Phones']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung galaxy s6']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Add to cart']")));
        driver.findElement(By.xpath("//a[text()='Add to cart']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Cart alert message: " + alert.getText());
        alert.accept();

        driver.findElement(By.id("cartur")).click(); // Navigate to cart

        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        driver.findElement(By.id("name")).sendKeys("Anjli Gupta");
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("city")).sendKeys("Mumbai");
        driver.findElement(By.id("card")).sendKeys("1233 2133 2211 3212");
        driver.findElement(By.id("month")).sendKeys("Dec");
        driver.findElement(By.id("year")).sendKeys("2024");
        driver.findElement(By.xpath("//button[text()='Purchase']")).click();

        // Confirm order success
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
        driver.findElement(By.xpath("//button[text()='OK']")).click();
    }

    @Test(priority = 4)
    public void logOut() {
        driver.findElement(By.id("logout2")).click();
        System.out.println("Logged out successfully!");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


    
   
      
      

