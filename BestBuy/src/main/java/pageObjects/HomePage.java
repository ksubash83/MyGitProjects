package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	public WebElement searchBestBuy(WebDriver driver) {
		
		WebElement searchBestBuy=driver.findElement(By.xpath("//input[@name='st']"));
		return searchBestBuy;		
	}
	
	public WebElement searchButton(WebDriver driver) {
		
		WebElement searchButton=driver.findElement(By.xpath("//button[@title='search']"));
		return searchButton;		
	}
	
	public WebElement ifPopUp1(WebDriver driver) {
		
		WebElement popUp1=driver.findElement(By.xpath("//button[@class='close']"));
		return popUp1;		
	}
}
