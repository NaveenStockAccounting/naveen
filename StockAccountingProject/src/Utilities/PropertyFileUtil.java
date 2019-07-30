package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil 
{

	public static String getValueForKey(String Key) throws IOException, IOException 
	{
	Properties configProperties=new Properties();
	configProperties.load(new FileInputStream(new File("D:\\naveen\\StockAccountingProject\\PropertiesFile\\Environment.properties")));
	return configProperties.getProperty(Key);

}
}