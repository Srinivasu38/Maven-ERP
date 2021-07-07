package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {
	public static String getValueForKey(String Key) throws Throwable
	{
		Properties configprop=new Properties();
		configprop.load(new FileInputStream("D:\\software\\seleniumproject\\Maven_ERP\\PropertyFile\\Environment.properties"));
		return configprop.getProperty(Key);
		
	}

}
