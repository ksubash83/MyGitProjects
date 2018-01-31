package com.mycompany.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import resources.Utilities;

public class SearchProducts {
	
	private WebDriver driver;
	private static Logger log=LogManager.getLogger(SearchProducts.class.getName());

	@Test
	public void searchProduct() throws IOException {
		
		Utilities u=new Utilities();		
		HomePage hp=new HomePage();
		
		driver=u.invokeBrowser();
		//implicitly wait 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Implicit wait triggered");
		
		driver.get(u.getHostUrl());
		log.info("Browser has been invoked and link is open");
		
		if(hp.ifPopUp1(driver).isDisplayed()) {
			hp.ifPopUp1(driver).click();
		}
				
		//Enter the text-ipad in the search text box
		hp.searchBestBuy(driver).sendKeys("iPad");
		log.info("Search text iPad has been entered in the text box");
		
		//click on search button
		hp.searchBestBuy(driver).sendKeys(Keys.ENTER);
		log.info("Search results for iPad are returned");
	
		driver.findElement(By.xpath("//*[@id=\"site-control-content\"]/div[5]/div[5]/div/div[1]/div[2]/div[1]/div/div[1]/div/a[1]")).click();	
		log.info("Clicked on ipad pro link");
		
		WebElement ipadWiFiButton=driver.findElement(By.xpath("//*[@id=\"iPad-12-9\"]/div/div[1]/div[2]/a"));
		
		//Explicit wait
		log.info("Explicit wait triggered for clicking on wifi button");
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\\\"iPad-12-9\\\"]/div/div[1]/div[2]/a")));
		
	
		//Code for javascriptexecutor, in case it is required
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ipadWiFiButton);
		log.info("Clicked on wifi link");
		
		//click on add to cart button
		WebElement addToCart=driver.findElement(By.xpath("//*[@id=\"pdp-add-to-cart-button\"]/div/button"));
		//Explicit wait
		log.info("Explicit wait triggered for clicking on add to cart button");
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOf(addToCart));				
		
		addToCart.click();
		log.info("Clicked on add to cart button");
		
		//If protection plan offer opens up, close the pop up
		if(driver.findElement(By.xpath("/html/body/div[12]/div/div/div/div[1]/div/div/div[1]/div/div/div[1]/div")).isDisplayed()) {
			driver.findElement(By.xpath("/html/body/div[12]/div/div/div/button")).click();
		}
		
		//click on cart icon
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div[2]/ul/li[2]/a")).click();
		
		//click on checkout button
		driver.findElement(By.xpath("//*[@id=\"sc-store-availability-target\"]/div/div/span/div/div[3]/div/div[1]/div[1]/div/button")).click();
		log.info("Clicked on Check Out button");
		
		//click on Continue as Guest
		driver.findElement(By.xpath("/html/body/section/main/div[4]/div/div[2]/a")).click();
		
		//Identify shipping radio buttons, click on standard shipping radio button
		List<WebElement> shippingRadios= driver.findElements(By.xpath("//input[@name='fulfillment-options-list__radio_fulfillment_1']"));
		int noOfRadio1=shippingRadios.size();
		log.debug("No. of radio buttons for shipping: "+noOfRadio1);
		
		for(int i=0;i<noOfRadio1;i++) {
			
			if(shippingRadios.get(i).getAttribute("id").contains("Standard"))
				shippingRadios.get(i).click();
		}
		
		//driver.close();
			
		
	}

}
