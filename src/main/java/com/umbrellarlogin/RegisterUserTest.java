package com.umbrellarlogin;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RegisterUserTest {

  FirefoxDriver driver = null;
  
  @BeforeClass
  public void beforeClass() {
	  
	  System.setProperty("webdriver.gecko.driver", "C:\\Applications\\Workspace\\UmbrellarProject\\src\\main\\resources\\webdrivers\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.get("https://react-redux-registration-login-example.stackblitz.io/login");
  }

 @Test
  public void testLogin() throws InterruptedException {
// Open Register User Web Page
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElementByLinkText("Register").click();
// Register a User
	driver.findElementByName("firstName").sendKeys("Shilpa");
	driver.findElementByName("lastName").sendKeys("Munnanuri");
	driver.findElementByName("username").sendKeys("smunnanuri");
	driver.findElementByName("password").sendKeys("auckland0622");
	driver.findElementByXPath("//*[@name='password']/following::*[2]").click();
	
	WebDriverWait wait = new WebDriverWait(driver,100);
	wait.until(ExpectedConditions.stalenessOf(driver.findElementByName("firstName")));
	
	
// Login using newly created User
	driver.findElementByName("username").sendKeys("smunnanuri");
	driver.findElementByName("password").sendKeys("auckland0622");
	driver.findElementByXPath("//*[text()='Register']/preceding-sibling::*").click();

// Verify User Details
	
	String text = driver.findElement(By.tagName("h1")).getText();
	Assert.assertTrue(text.contains("Shilpa"), "Text found");
	String text1 = driver.findElement(By.tagName("li")).getText();
	Assert.assertTrue(text1.contains("Shilpa Munnanuri"), "pass");

// Logout and Quit using afterclass
	driver.findElementByLinkText("Logout").click();
}

  
  @AfterClass
  public void afterClass() {
    driver.quit();
  }

}
