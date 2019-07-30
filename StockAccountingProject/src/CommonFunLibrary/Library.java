package CommonFunLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Library 
{
	WebDriver driver;
	String res;
	//app launch
public String LaunchApp(String url)
{

System.setProperty("webdriver.chrome.driver","commonJarFiles/chromedriver.exe");
driver=new ChromeDriver();
driver.get(url);
driver.manage().window().maximize();
//validation
if (driver.findElement(By.id("username")).isDisplayed())
{
	res="Pass";
}
else
{
	res="Fail";
}
return res;
}
}

//app Launch
