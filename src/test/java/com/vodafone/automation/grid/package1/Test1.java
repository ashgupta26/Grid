package com.vodafone.automation.grid.package1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 {
WebDriver driver;
	@Parameters({"browser","ip"})
	@BeforeTest
	public void bt(String browser, String ip) throws MalformedURLException {
		switch(browser.toLowerCase())
		{
		case "gc": case "chrome":
			/*System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();*/
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			driver = new RemoteWebDriver(new URL(ip+"/wd/hub"), cap);
			break;
		case "ff": case "mozilla": case "firefox":
			/*System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();*/
			DesiredCapabilities cap1 = DesiredCapabilities.firefox();
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap1);
			break;
		case "ie": case "explorer" : 
			/*System.setProperty("webdriver.ie.driver", "driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();*/
			DesiredCapabilities cap2 = DesiredCapabilities.internetExplorer();
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap2);
			break;
		default:
			//System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			//driver = new ChromeDriver();
			DesiredCapabilities cap3 = DesiredCapabilities.chrome();
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap3);
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://magento.com");
	}

	@Test
	public void test1() {
		driver.findElement(By.className("fa-user")).click();
		driver.findElement(By.id("email")).sendKeys("natarajan.ramanathan93@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Welcome123");
		driver.findElement(By.id("send2")).click();
		driver.findElement(By.linkText("Log Out")).click();
	}
	
	@AfterTest
	public void at() {
		driver.quit();
	}
}
