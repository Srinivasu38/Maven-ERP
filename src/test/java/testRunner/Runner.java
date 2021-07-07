package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"Feature"},
glue= {"stepDefinations"},
tags= {"@Supplierdata"},monochrome = true,
plugin = {"com.cucumber.listener.ExtentCucumberFormatter:Reports/Supplier.html","pretty", "html:target/cucumber-reports"})


public class Runner {

}
