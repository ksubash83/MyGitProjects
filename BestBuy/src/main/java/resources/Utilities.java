package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utilities {
	
	private WebDriver driver;
	private Properties prop;
	private String chromeDriverPath="C:\\\\Users\\\\ksuba\\\\Work\\\\chromedriver_win32\\\\chromedriver.exe";
	
	public Utilities() throws IOException {
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop=new Properties();

		prop.load(fis);
	}

	
	public WebDriver invokeBrowser() throws IOException {
				
		String browsername=prop.getProperty("browser");
		
		if(browsername.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver=new ChromeDriver();
			
			return driver;
			
		}
			
		return null;
		
	}
	
	public String getHostUrl() {
		
		String hostUrl=prop.getProperty("HostUrl");
		return hostUrl;
	}
	
}
