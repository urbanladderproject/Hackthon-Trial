package ts_03;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

import pageobjects.GiftCard;
import pageobjects.Url_navigating_homepage;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.Report;
import utils.Screenshots;

public class Giftcard_with_empty_sender_phone extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;

	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}
	
	
	//Tries to fill an empty value in the form
	@Test
	public void empty_sender_phone() throws Exception {
		
		Report report = new Report();

		log = reports.createTest("Test empty sender phone number");

		report.startBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Url_navigating_homepage.clearpage();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		GiftCard gc  = PageFactory.initElements(driver, GiftCard.class);

		report.select(log, "Empty sender phone number");

		log.pass(MarkupHelper.createLabel("Empty sender phone number wasn't accepted", ExtentColor.GREEN));

		gc.enter_to_giftcard("Order type");

		gc.select_gifttype("Occasion");

		gc.enter_amount("Gift Card amount");

		gc.next_page();

		gc.recepient_name("Recepient Name");
		
		gc.recepient_email("Recepient Email");

		gc.sender_name("Sender Name");

		gc.sender_email("Sender Email");

		gc.sender_phone("Empty Sender Phonenumber");

		gc.confirm();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Screenshots.takesnap(driver, "Empty Sender phone",browsertype);

		report.display(log, "Empty sender phone number wasn't accepted");

		log.pass(MarkupHelper.createLabel("Empty sender phone number wasn't accepted", ExtentColor.GREEN));

		report.closeBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));


	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Empty sender phone number accepting Test: SUCCESS",browsertype);
		else
			excel.reportToExcel("Empty sender phone number accepting Test: FAILURE",browsertype);
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Empty sender phone number accepting Test: ENDED",browsertype);
		// close the driver

		driver.quit();
	}

}
