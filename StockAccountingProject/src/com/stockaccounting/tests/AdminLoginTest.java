package com.stockaccounting.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdminLoginTest {

	public static void main(String[] args) 
	{
		System.setProperty("webdriver.cjrome.driver", "D:\\naveen\\StockAccountingProject\\commonJarFiles\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://wbapp.qedge.com/login.php");

	}


}
