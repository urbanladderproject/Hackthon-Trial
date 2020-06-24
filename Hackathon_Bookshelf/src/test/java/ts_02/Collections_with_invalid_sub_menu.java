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

public class Collections_with_invalid_sub_menu extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;
	
	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}
	
	
	//rejects to display a different sub menu other than 'Being at Home'
	@Test
	public void valid_main_menu_and_invalid_submenu () throws Exception{
		
		Report report = new Report();
		
		log = reports.createTest("Test for valid main menu and invalid submenu");
		
		report.startBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		Display_Collections collections = PageFactory.initElements(driver, Display_Collections.class);
		
		report.select(log, "Different sub menu test");
		
		log.pass(MarkupHelper.createLabel("The different sub menu options are not displayed", ExtentColor.GREEN));
		
		collections.collection_click("Menu to be selected");
		
		collections.getdata("Invalid sub-menu to be selected");

		report.display(log, "A different main menu was tried to be selected and was failed");
		
		log.pass(MarkupHelper.createLabel("A different sub menu was tried to be selected and was failed successfully", ExtentColor.GREEN));
		
		report.closeBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));

	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Different sub menu Test: SUCCESS");
		else
			excel.reportToExcel("Different sub menu Test: FAILURE");
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Different sub menu Test: ENDED");
		// close the driver

		driver.quit();
	} 

}
