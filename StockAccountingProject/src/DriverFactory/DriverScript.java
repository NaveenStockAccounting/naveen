package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import CommonFunLibrary.CommonFunLibrary;
import Utilities.ExcelFileUtils;

public class DriverScript
{
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
public  void StartTest() throws Throwable
{
	ExcelFileUtils excel=new ExcelFileUtils();
	for (int i = 1; i <=excel.rowcount("MasterTestCases"); i++)
	{
		String ModuleStatus="";
		if (excel.getData("MasterTestCases",i,2).equalsIgnoreCase("Y"))
		{
			String TCModule=excel.getData("MasterTestCases",i,1);
			
			int rowcount=excel.rowcount(TCModule);
			for (int j = 1; j <=rowcount; j++) 
			{
			String Description=excel.getData(TCModule, j, 0);
			String Object_Type=excel.getData(TCModule, j, 1);
			String Locator_Type=excel.getData(TCModule, j, 2);
			String Locator_Value=excel.getData(TCModule, j, 3);
			String Test_Data=excel.getData(TCModule, j, 4);
			
			//System.out.println(TCModule);
			try
			{
			if (Object_Type.equalsIgnoreCase("startBrowser"))
			{
			driver=CommonFunLibrary.startBrowser(driver);
			}
			if (Object_Type.equalsIgnoreCase("openApplication"))
			{
			CommonFunLibrary.openApplication(driver);
			}
			if (Object_Type.equalsIgnoreCase("typeAction")) 
			{
			CommonFunLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);	
			}
			if (Object_Type.equalsIgnoreCase("clickAction")) 
			{
			CommonFunLibrary.clickAction(driver, Locator_Type,Locator_Value);	
			}
			if (Object_Type.equalsIgnoreCase("waitForElement"))
			{
				CommonFunLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
			}
			if(Object_Type.equalsIgnoreCase("closeBrowser"))
			{
				CommonFunLibrary.closBrowser(driver);
			}
			if(Object_Type.equalsIgnoreCase("captureData"))
			{
				CommonFunLibrary.captureData(driver, Locator_Type, Locator_Value);
			}
			if (Object_Type.equalsIgnoreCase("pageDown"))
			CommonFunLibrary.pageDown(driver);	
			{	
				
			}	
			if (Object_Type.equalsIgnoreCase("closeApp"))
			{
				CommonFunLibrary.closeApp(driver);
			}
			if (Object_Type.equalsIgnoreCase("tableValidation"))
			{
				CommonFunLibrary.tableValidation(driver, Test_Data);
			}
			if (Object_Type.equalsIgnoreCase("stockCategories"))
			{
				CommonFunLibrary.stockCategories(driver);
			}
			if (Object_Type.equalsIgnoreCase("tableValidation1"))
				CommonFunLibrary.tableValidation1(driver, Test_Data);
			{
				
			}
				excel.setdata(TCModule, j, 5, "PASS");
				ModuleStatus="true";
			}
			
			catch (Exception e) 
			{
				File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile,new File("D:\\naveen\\StockAccountingProject\\ScreenShot"+" "+Description+CommonFunLibrary.generateDate()+".png"));
				excel.setdata(TCModule, j, 5, "FAIL");
				ModuleStatus="false";
				break;
			}
		}
			
		if (ModuleStatus.equalsIgnoreCase("true"))
		{
		excel.setdata("MasterTestCases", i, 3, "PASS");	
		}
		else
			if (ModuleStatus.equalsIgnoreCase("false")) 
			{
			excel.setdata("MasterTestCases", i, 3, "FAIL");	
			}
		}
		else
		{
			excel.setdata("MasterTestCases", i, 3, "Not Executed");
		}
		
			}
		}
	
}

