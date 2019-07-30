package com.stockaccounting.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchApp 
{
	String res;
	public static void main(String[] args) throws InterruptedException 
	{
	System.setProperty("webdriver.chrome.driver","commonJarFiles/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
		
   driver.get("http://webapp.qedge.com/login.php");
   driver.findElement(By.id("username")).clear();
   driver.findElement(By.id("username")).sendKeys("admin");
   driver.findElement(By.id("password")).clear();
   driver.findElement(By.id("password")).sendKeys("master");
   driver.findElement(By.id("btnsubmit")).click();
   driver.findElement(By.id("logout")).click();
    driver.findElement(By.xpath("//*[text()='OK!']")).click();
   
	}
		
	}		
	


