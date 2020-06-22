package ts_01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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

public class Bookshelf_search_with_storage_type extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;
	
	Excelutils xL = new Excelutils();

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
		
		log = reports.createTest("Test Open Bookshelf");
		
		report.startBrowser(log);
		
		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		Url_navigating_homepage homepage = PageFactory.initElements(driver, Url_navigating_homepage.class);
		
		report.select(log, "Search for Open Bookshelf");
		
		log.pass(MarkupHelper.createLabel("Open Bookshelf is searched successfully", ExtentColor.GREEN));
		
		homepage.clearpage();
		
		System.out.println("Search Data");
		
		homepage.searchText("Search with storage");

		homepage.searchbutton();
		
		Bookshelf bookshelf = PageFactory.initElements(driver, Bookshelf.class);
		
		String searched_data = bookshelf.get_searched_title();
		
		Assert.assertTrue(searched_data.contains("Open Bookshelf"));
		
		bookshelf.stockdetails();
				
		bookshelf.getresult("Price",browsertype);
	}

	
}
