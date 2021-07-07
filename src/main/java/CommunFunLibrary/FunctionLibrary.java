package CommunFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;
	
	//method start browser
	public static WebDriver startBrowser() throws Throwable {
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\software\\seleniumproject\\ERP_StockAccounting\\CommunDrivers\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\software\\seleniumproject\\ERP_StockAccounting\\CommunDrivers\\IEDriverServer.exe");
			driver=new ChromeDriver();
		}
		else 
		{
			Reporter.log("Browser value is not matching ",true);
		}
		return driver;
	}
// method for login
	public static void openApplication(WebDriver driver) throws Throwable {
		driver.get(PropertyFileUtil.getValueForKey("Url"));
	}
	
	//method for wait for element
	public static void waitForElement(WebDriver driver , String LocatorType, String LocatorValue,String waititme)
	{
		WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waititme));
		if(LocatorType.equalsIgnoreCase("name"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("cssSelector"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(LocatorValue)));
		}
			
}
	//method for type action

	public static void typeAction(WebDriver driver,String locatortype ,String locatorvalue,String TestData)
	{
		if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(TestData);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(TestData);
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(TestData);
		}
		else if(locatortype.equalsIgnoreCase("cssSelector"))
		{
			driver.findElement(By.cssSelector(locatorvalue)).clear();
			driver.findElement(By.cssSelector(locatorvalue)).sendKeys(TestData);
		}
	}
	//method for click action
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue) throws Throwable
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			Thread.sleep(2000);
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			Thread.sleep(2000);
			driver.findElement(By.name(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("cssSelector"))
		{
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(locatorvalue)).click();
		}
	}
	//method for validateTitle
	public static void validateTitle(WebDriver driver,String Exp_title)
	{}
	
	//method for close browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}

//method for captureData
	public static void captureData(WebDriver driver,String locatortype,String locatorvalue) throws Throwable
	{
		String supplierNum="";
		if(locatortype.equalsIgnoreCase("id"))
		{
			supplierNum=driver.findElement(By.id(locatorvalue)).getAttribute("value");
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			supplierNum=driver.findElement(By.name(locatorvalue)).getAttribute("value");
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		
		{
			supplierNum=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
		}
		//create textfile and write into it
		FileWriter fw=new FileWriter("D:\\software\\seleniumproject\\ERP_StockAccounting\\CaptureData\\supnumber.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(supplierNum);
		bw.flush();
		bw.close();
				
	}
	//table validation for supplier
	public static void supTableValidation(WebDriver driver,String column) throws Throwable
	{
		//read data from notepad
		FileReader fr=new FileReader("./CaptureData/supnumber.txt");
		BufferedReader br=new BufferedReader(fr);
		//read the notepad data and store into one varible
		String Exp_supnumber=br.readLine();
		System.out.println(Exp_supnumber);
		//string to int
		int colNum=Integer.parseInt(column);
		//if search textbox is not display then click search panel
		
		if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox"))).isDisplayed())
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("serach-panel"))).click();
		Thread.sleep(5000);
		//clear search testbox
		WebElement searchtextbox=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox")));
		searchtextbox.clear();
		searchtextbox.sendKeys(Exp_supnumber);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-button"))).click();
		Thread.sleep(2000);
		
		WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Webtable.path")));
		//count no of rows in a table
		List<WebElement>rows=table.findElements(By.tagName("tr"));
		for(int i=1;i<rows.size();i++)
		{
			//captural suppler number
			String ActualSnumber=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colNum+"]/div/span/span")).getText();
			Thread.sleep(5000);
			Assert.assertEquals(ActualSnumber, Exp_supnumber);
			System.out.println(ActualSnumber+"         "+Exp_supnumber);
		}
	}
		//method for mouse click
		public  static  void stockCategories(WebDriver driver)throws Throwable
		{
			Actions ac = new Actions(driver);
			ac.moveToElement(driver.findElement(By.linkText("Stock Items"))).perform();
			Thread.sleep(2000);
			ac.moveToElement(driver.findElement(By.xpath("(//a[text()='Stock Categories'])[2]"))).click().perform();
		}
		//method for sttableValidation
		public static void sttableValidation(WebDriver driver,String TestData) throws Throwable
		{

			if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox"))).isDisplayed())
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("serach-panel"))).click();
			Thread.sleep(5000);
			//clear search testbox
			WebElement searchtextbox=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-textbox")));
			searchtextbox.clear();
			searchtextbox.sendKeys(TestData);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-button"))).click();
			Thread.sleep(2000);
			
			WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Webtable.path1")));
			//count no of rows in a table
			List<WebElement>rows=table.findElements(By.tagName("tr"));
			for(int i=1;i<rows.size();i++)
			{
				String actualdata=driver.findElement(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr[1]/td[4]/div/span/span")).getText();
				Thread.sleep(5000);
				Reporter.log(actualdata+"        "+TestData);
				Assert.assertEquals(actualdata, TestData);
				break;
			}
			}
			//method for date generate
			public static String generateDate()
			{
				Date date = new Date();
				DateFormat df = new SimpleDateFormat("YYYY_MM_dd hh_mm_ss");
				return df.format(date);
			}
			
	}
	
