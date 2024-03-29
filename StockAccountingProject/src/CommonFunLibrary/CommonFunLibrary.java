package CommonFunLibrary;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;


public class CommonFunLibrary 
{
 WebDriver driver;
public static WebDriver startBrowser(WebDriver driver) throws Exception 
{
if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Firefox"))
{
driver=new FirefoxDriver();	
}
else
	if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
	
	{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\naveen.k\\Downloads\\chromedriver.exe");
	driver = new ChromeDriver();
	}
	else
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("IE")) 
		{
		System.setProperty("webdriver.ie.driver","CommonJarFiles/IEDriverserver.exe");	
		}
	return driver;
	}



//openAplication


public static void openApplication(WebDriver driver) throws Throwable
{
	driver.get(PropertyFileUtil.getValueForKey("URL"));
	driver.manage().window().maximize();
	
}
    

//ClickAction

public static void clickAction(WebDriver driver,String locatorType,String locatorValue)
{
	if(locatorType.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(locatorValue)).click();
	}else
		if(locatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorValue)).click();
		}
		else
			if(locatorType.equalsIgnoreCase("cssselector"))
			{
				driver.findElement(By.cssSelector(locatorValue)).click();
			}
}



//typeAction

public static void typeAction(WebDriver driver,String locatorType,String locatorValue,String data)
{
if(locatorType.equalsIgnoreCase("id"))
{
	driver.findElement(By.id(locatorValue)).clear();
	driver.findElement(By.id(locatorValue)).sendKeys(data);
}
else 
	if(locatorType.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(locatorValue)).clear();
		driver.findElement(By.name(locatorValue)).sendKeys(data);
	}
	else
	
		if(locatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorValue)).clear();
			driver.findElement(By.xpath(locatorValue)).sendKeys(data);
		}
		
		}
		//close Browser
		public static void closBrowser(WebDriver driver)
		{
			driver.close();
		}


//wait 
public static void waitForElement(WebDriver driver,String locatorType,String locatorValue,String waittime)
{
	WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(waittime));
	if (locatorType.equalsIgnoreCase("id")) 
	{
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
	}
	else
		if (locatorType.equalsIgnoreCase("name"))
		{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));	
		}
		else
			if (locatorType.equalsIgnoreCase("xpath"))
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			}
}
public static void captureData(WebDriver driver,String locatorType,String locatorValue) throws Throwable
{
	String data="";
	if (locatorType.equalsIgnoreCase("id"))
	{
	data=driver.findElement(By.id(locatorValue)).getAttribute("value");
	}
	else
		if (locatorType.equalsIgnoreCase("name"))
		{
		data=driver.findElement(By.name(locatorValue)).getAttribute("value");
		}
		else
			if (locatorType.equalsIgnoreCase("xpath"))
			{
			data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
			}
	FileWriter fw=new FileWriter("D:\\naveen\\StockAccountingProject\\CaptureData\\Data.txt");
	BufferedWriter bw=new BufferedWriter(fw);
	bw.write(data);
	bw.flush();
	bw.close();
}
//close Appp
public static void closeApp(WebDriver driver)
{
	driver.close();
}

//mouseclick
public static void stockCategories(WebDriver driver) throws Throwable
{
	Thread.sleep(2000);
	Actions action=new Actions(driver);
	WebElement element=driver.findElement(By.xpath("//*[@id='mmi_a_stock_items']/a[2]"));
	action.moveToElement(element).perform();
}

//pagedown
public static void pageDown(WebDriver driver)
{
	Actions action=new Actions(driver);
	action.sendKeys(Keys.PAGE_DOWN).perform();
}

public static void tableValidation(WebDriver driver,String colNum) throws Throwable 
{
	FileReader fr=new FileReader("D:\\naveen\\StockAccountingProject\\CaptureData\\Data.txt");
	BufferedReader br =new BufferedReader(fr);
	String exp_data=br.readLine();
	//System.out.println(exp_data);
	int colnum1=Integer.parseInt(colNum);
	if (driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).isDisplayed())
	{
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
	}
	else
	{
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel"))).click();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
	}
	WebElement webtable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path")));
	List<WebElement> rows=webtable.findElements(By.tagName("tr"));
	for (int i = 1; i <=rows.size(); i++) 
	{
	String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();	
	System.out.println(act_data);
	Assert.assertEquals(exp_data,act_data);
	break;
	}
}
//GENERATE DATE 
public static String  generateDate()
{
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
	return sdf.format(date);
}

//table validation of StockCategories
public static void tableValidation1(WebDriver driver,String exp_data) throws Throwable
{
	if (driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).isDisplayed())
	{
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
	}
	else
	{
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel"))).click();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
	}
	WebElement webtable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path1")));
	List<WebElement> rows=webtable.findElements(By.tagName("tr"));
	for (int i = 1; i <=rows.size(); i++)
	{
	String act_data=driver.findElement(By.xpath("//*[@id='tb1_a_stock_categorieslist']/tbody/tr["+i+"]/td[4]/div/span/span")).getText();
	System.out.println(act_data);
	Assert.assertEquals(exp_data,act_data);
	break;
	}
}	
}





