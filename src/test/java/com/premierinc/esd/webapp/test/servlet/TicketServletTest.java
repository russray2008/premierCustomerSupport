package com.premierinc.esd.webapp.test.servlet;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TicketServletTest {
	
	  private String baseUrl;
	  private WebDriver driver;
	  private ScreenshotHelper screenshotHelper;
	  
	  //@Before
	  public void openBrowser() {
	    baseUrl = System.getProperty("webdriver.base.url");
	    driver = new ChromeDriver();
	    driver.get(baseUrl);
	    screenshotHelper = new ScreenshotHelper();
	  }
	  
	  @BeforeTest
	  public void setupSelenium(){
		  //Start the browser (Chrome for now)
		 // DesiredCapabilities capabilities = new DesiredCapabilities();
		  //capabilities.setJavascriptEnabled(true);
		  //WebDriver driver = new RemoteWebDriver(capabilities);
		  
		  System.setProperty("webdriver.chrome.driver", "/Users/rray/.m2/repository/org/seleniumhq/selenium/selenium-chrome-driver/2.45.0/selenium-chrome-driver-2.45.0.jar");
		  
		  //Capabilities actionalCapabilities = ((RemoteWebDriver) driver).getCapabilities();
		  driver = new ChromeDriver();
		  
		  //This add implicit timeouts to the driver (instead of clickAndWait())
		  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  }
	  
	 // @After
	  public void saveScreenshotAndCloseBrowser() throws IOException {
	    screenshotHelper.saveScreenshot("screenshot.png");
	    driver.quit();
	  }
	  
	  @AfterTest
	  public void closeSelenium(){
		  //Shutdown the browser
		  driver.quit();
	  }
	  
	  @Test
	  public void testSearch() throws IOException{
		  driver.navigate().to("http://www.goggle.com/xhtml");
		  String searchTerm = "Java";
		  //Clear the search box and type seachTerm
		  driver.findElement(By.cssSelector("#s")).clear();
		  driver.findElement(By.cssSelector("#s")).sendKeys(searchTerm);
		  
		  //Click on the magnifying glass
		  driver.findElement(By.cssSelector("#searchSubmit")).click();
		  
		  //Get search term for search results, to check if match the given search
		  String termInTitle = driver.findElement(By.cssSelector("archive-title>String")).getText();
		  
		  screenshotHelper.saveScreenshot("screenshot.png");
		  
		  assertEquals(termInTitle, searchTerm, "Search term not found in search results");
    }
	  
	  private class ScreenshotHelper {
		  
		    public void saveScreenshot(String screenshotFileName) throws IOException {
		      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		      FileUtils.copyFile(screenshot, new File(screenshotFileName));
		    }
	 }


}
