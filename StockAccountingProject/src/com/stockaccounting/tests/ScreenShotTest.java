package com.stockaccounting.tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ScreenShotTest {

	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\naveen\\StockAccountingProject\\commonJarFiles\\chromedriver.exe");
	WebDriver driver =new ChromeDriver();
	driver.get("Http://Gmail.com");
	driver.manage().window().maximize();
	File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//FileUtils.copyFile(srcFile, "C:\\Users\\Public\\Pictures\\Sample Pictures");
	}

}
