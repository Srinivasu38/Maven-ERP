package DriverFactory;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommunFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {
	ExtentReports reports;
	ExtentTest test;
	public static WebDriver driver;
		String inputpath ="D:\\software\\seleniumproject\\Maven_ERP\\TestInput\\HybridTest.xlsx";
				String  outputpath ="D:\\software\\seleniumproject\\Maven_ERP\\TestOutput\\HybridResults.xlsx";
				public void startTest()throws Throwable
				{
					ExcelFileUtil xl= new ExcelFileUtil(inputpath);
							for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
							{
								String moduleStatus="";
								if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("y"))
								{
									String TCModule=xl.getCellData("MasterTestCases", i, 1);
									reports=new ExtentReports("./Reports/"+TCModule+" "+FunctionLibrary.generateDate()+".html");
									for(int j=1;j<=xl.rowCount(TCModule);j++)
									{
										// start test(only test reports )
										test=reports.startTest(TCModule);
										
										//read all description
										String Description=xl.getCellData(TCModule, i, 0);
										String Function_Name=xl.getCellData(TCModule, j, 1);
										String Locator_Type=xl.getCellData(TCModule, j, 2);
										String Locator_Value=xl.getCellData(TCModule, j, 3);
										String Test_Data=xl.getCellData(TCModule, j, 4);
										//calling methods
										try 
										{
										if(Function_Name.equalsIgnoreCase("startBrowser"))
										{
											driver=FunctionLibrary.startBrowser();
											test.log(LogStatus.INFO, Description);
										}
										else if(Function_Name.equalsIgnoreCase("openApplication"))
										{
											FunctionLibrary.openApplication(driver);
											test.log(LogStatus.INFO, Description);
										}
										else if(Function_Name.equalsIgnoreCase("waitForElemen"))
										{
											FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
											test.log(LogStatus.INFO, Description);
										}
										else if(Function_Name.equalsIgnoreCase("typeAction"))
										{
										
											FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
											test.log(LogStatus.INFO, Description);
										}
										else if(Function_Name.equalsIgnoreCase("clickAction"))
										{
								
											FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
											test.log(LogStatus.INFO, Description);
										}
									else if(Function_Name.equalsIgnoreCase("validateTitle"))
										{
									 FunctionLibrary.validateTitle(driver, Test_Data);
									 test.log(LogStatus.INFO, Description);
										}
									else if(Function_Name.equalsIgnoreCase("closeBrowser"))
										{
										FunctionLibrary.closeBrowser(driver);
										test.log(LogStatus.INFO, Description);
										}
										else if(Function_Name.equalsIgnoreCase("captureData"))
										{
										FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
										test.log(LogStatus.INFO, Description);
										}
										else if(Function_Name.equalsIgnoreCase("supTableValidation"))
										{
										FunctionLibrary.supTableValidation(driver, Test_Data);
										test.log(LogStatus.INFO, Description);
										}
										else if(Function_Name.equalsIgnoreCase("stockCategories"))
										{
										FunctionLibrary.stockCategories(driver);
										test.log(LogStatus.INFO, Description);
										}
										else if(Function_Name.equalsIgnoreCase("sttableValidation"))
										{
										FunctionLibrary.supTableValidation(driver, Test_Data);
										test.log(LogStatus.INFO, Description);
										}
										xl.setCellData(TCModule, j, 5, "Pass", outputpath);
										test.log(LogStatus.PASS, Description);
										moduleStatus="true";
										}
									catch (Exception e) {
									System.out.println(e.getMessage());
									xl.setCellData(TCModule, j, 5, "Fail", outputpath);
									test.log(LogStatus.FAIL, Description);
									moduleStatus="False";
									}
										if(moduleStatus.equalsIgnoreCase("True"))
										{
											xl.setCellData("MasterTestCases", i, 3, "Pass", outputpath);
										}
										else
										{
											xl.setCellData("MasterTestCases", i, 3, "Fail", outputpath);
										}
										//end test
										reports.endTest(test);
										reports.flush();
								}
							}
								else
								{
									xl.setCellData("MasterTestCases", i, 3, "Not Executed", outputpath);
								}
					
				
							}
				}

}

