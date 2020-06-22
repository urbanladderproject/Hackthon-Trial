package ts_01;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pageobjects.Bookshelf;
import pageobjects.Url_navigating_homepage;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.Report;

public class Bookshelf_search_without_Storagetype extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;
	
	Excelutils excel = new Excelutils();

	
	
	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}
	
	
	//search for bookshelf without mentioning the storage type in the search bar
	@Test
	public void search_without_Storagetype() throws Exception{
		
		Report report = new Report();
		
		log = reports.createTest("Test Bookshelf");
		
		report.startBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		Url_navigating_homepage homepage = PageFactory.initElements(driver, Url_navigating_homepage.class);
		
		report.select(log, "Search for Bookshelf");
		
		log.pass(MarkupHelper.createLabel("Bookshelf is searched successfully", ExtentColor.GREEN));
		
		homepage.clearpage();
		
		System.out.println("Search Data");
		
		homepage.searchText("Search Data");

		homepage.searchbutton();
		
		Bookshelf bookshelf = PageFactory.initElements(driver, Bookshelf.class);
		
		String searched_data = bookshelf.get_searched_title();
		
		Assert.assertTrue(searched_data.contains("Bookshelf"));
		
		bookshelf.stockdetails();
		
		bookshelf.storage_dropdown();
		
		bookshelf.select_storagetype("Storage Type");
		
		bookshelf.getresult("Price",browsertype);
		
		report.display(log, "Bookshelf storage type was selected");
		
		log.pass(MarkupHelper.createLabel("Bookshelf storage type was selected successfully", ExtentColor.GREEN));
		
		report.closeBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));

	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Bookshelf didnot search for invalid storage Test: SUCCESS");
		else
			excel.reportToExcel("Bookshelf search without storage type Test: FAILURE");
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Bookshelf search without storage type Test: ENDED");
		// close the driver

		driver.quit();
	}


}
