package ts_02;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pageobjects.Display_Collections;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.Report;

public class Collections_with_Being_at_Home extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;
	
	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}
	
	@Test
	public void valid_main_and_submenu () throws Exception{
		
		Report report = new Report();
		
		log = reports.createTest("Test Collections with valid sub menu");
		
		report.startBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		Display_Collections collections = PageFactory.initElements(driver, Display_Collections.class);
		
		report.select(log, "List the categories in the submenu - 'Being at Home'");
		
		log.pass(MarkupHelper.createLabel("The submenu 'Being at Home' is displayed successfully", ExtentColor.GREEN));
		
		collections.collection_click("Menu to be selected");
		
		collections.getdata("Sub-menu to be selected");
		
		collections.displaydata();
		
		collections.write_result(browsertype);

		report.display(log, "The submenu 'Being at Home' is displayed successfully");
		
		log.pass(MarkupHelper.createLabel("The submenu 'Being at Home' is displayed successfully", ExtentColor.GREEN));
		
		report.closeBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));

	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("The submenu 'Being at Home' displaying Test: SUCCESS");
		else
			excel.reportToExcel("The submenu 'Being at Home' displaying Test: FAILURE");
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("List the categories in the submenu 'Being at Home' Test: ENDED");
		// close the driver

		driver.quit();
	}



}
