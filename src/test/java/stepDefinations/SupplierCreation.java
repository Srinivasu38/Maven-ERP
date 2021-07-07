package stepDefinations;

import org.openqa.selenium.WebDriver; 
import CommunFunLibrary.FunctionLibrary;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SupplierCreation {
	WebDriver driver;
	@When("^Open Browser$")
	public void open_Browser() throws Throwable {
	   driver=FunctionLibrary.startBrowser();
	}

	@When("^Open Application$")
	public void open_Application() throws Throwable {
	   FunctionLibrary.openApplication(driver);
	}

	@When("^Wait For Username$")
	public void wait_For_Username() throws Throwable {
	    FunctionLibrary.waitForElement(driver, "name", "username", "10");
	}

	@When("^Enter Username$")
	public void enter_Username() throws Throwable {
	    FunctionLibrary.typeAction(driver, "name", "username", "admin");
	}

	@When("^Wait For Password$")
	public void wait_For_Password() throws Throwable {
		FunctionLibrary.waitForElement(driver, "cssSelector", "#password", "10");
	}

	@When("^Enter Password$")
	public void enter_Password() throws Throwable {
	   FunctionLibrary.typeAction(driver, "cssSelector", "#password", "master");
	}

	@When("^Wait For Login$")
	public void wait_For_Login() throws Throwable {
		FunctionLibrary.waitForElement(driver, "id", "btnsubmit", "10");
	}

	@When("^Click On Login$")
	public void click_On_Login() throws Throwable {
	   FunctionLibrary.clickAction(driver, "id", "btnsubmit");
	}

	@When("^Wait For Supplier$")
	public void wait_For_Supplier() throws Throwable {
		FunctionLibrary.waitForElement(driver, "xpath", "//body/div[2]/div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]", "10");
	}

	@When("^Click On Supplier$")
	public void click_On_Supplier() throws Throwable {
		FunctionLibrary.clickAction(driver, "xpath", "//body/div[2]/div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]");
	}

	@When("^Wait For AddButton$")
	public void wait_For_AddButton() throws Throwable {
		FunctionLibrary.waitForElement(driver, "xpath", "(//span[@data-caption='Add'])[1]", "10");
	}

	@When("^Click On AddButton$")
	public void click_On_AddButton() throws Throwable {
		FunctionLibrary.clickAction(driver, "xpath", "(//span[@data-caption='Add'])[1]");
	}

	@When("^Wait For SupplierNumber$")
	public void wait_For_SupplierNumber() throws Throwable {
		FunctionLibrary.waitForElement(driver, "xpath", "//input[@id='x_Supplier_Number']", "10");
	}

	@Then("^Capture Data$")
	public void capture_Data() throws Throwable {
	    FunctionLibrary.captureData(driver, "xpath", "//input[@id='x_Supplier_Number']");
	}

	@When("^Wait For SupplierName$")
	public void wait_For_SupplierName() throws Throwable {
	    FunctionLibrary.waitForElement(driver, "id", "x_Supplier_Name", "10");
	}

	@When("^Enter in \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void enter_in_with_and(String testdata, String locatortype, String locatorvalue) throws Throwable {
	   FunctionLibrary.typeAction(driver, locatortype, locatorvalue, testdata);
	}

	@When("^Wait For Add Button$")
	public void wait_For_Add_Button() throws Throwable {
		FunctionLibrary.waitForElement(driver, "id", "btnAction", "10");
	}

	@When("^Click On Add Button after adding notes$")
	public void click_On_Add_Button_after_adding_notes() throws Throwable {
		FunctionLibrary.clickAction(driver, "id", "btnAction");
	}

	@When("^Wait For Ok Button$")
	public void wait_For_Ok_Button() throws Throwable {
		FunctionLibrary.waitForElement(driver, "xpath", "//button[text()='OK!']", "10");
	}

	@When("^Click On Ok Button with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void click_On_Ok_Button_with_and(String locatortype, String locatorvalue) throws Throwable {
		FunctionLibrary.clickAction(driver, "xpath", "//button[text()='OK!");
	}

	@When("^Wait For Alert$")
	public void wait_For_Alert() throws Throwable {
		FunctionLibrary.waitForElement(driver, "xpath", "//button[@class='ajs-button btn btn-primary']", "10");
	}

	@When("^Click On Alert$")
	public void click_On_Alert() throws Throwable {
		FunctionLibrary.clickAction(driver, "xpath", "//button[@class='ajs-button btn btn-primary']");
	}

	@Then("^user validate the supplier table$")
	public void user_validate_the_supplier_table() throws Throwable {
	    FunctionLibrary.supTableValidation(driver, "6");
	}

	@When("^user closes the browser$")
	public void user_closes_the_browser() throws Throwable {
	   driver.close();
	}



}
