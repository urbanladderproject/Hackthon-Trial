package ts_03;

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

import pageobjects.GiftCard;
import setup.Environment_Setup;
import utils.Excelutils;
import utils.Report;

public class Giftcard_with_invalid_sender_email extends utils.ExtentReport {

	public static WebDriver driver;

	public static String browsertype;

	Excelutils excel = new Excelutils();

	@BeforeClass(alwaysRun = true)
	@Parameters({ "Browser", "Environment" })
	public void setUp(@Optional("chrome") String browser, @Optional("local") String environment) {
		driver = Environment_Setup.getDriver(browser, environment);
		browsertype = browser;
	}

	//Tries to fill an invalid sender email id
	@Test
	public void invalid_sender_email() throws Exception {
		
		Report report = new Report();

		log = reports.createTest("Test Invalid sender email");

		report.startBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser started successfully", ExtentColor.GREEN));

		GiftCard gc  = PageFactory.initElements(driver, GiftCard.class);

		report.select(log, "Invalid sender email");

		log.pass(MarkupHelper.createLabel("Invalid sender email wasn't accepted", ExtentColor.GREEN));

		gc.enter_to_giftcard("Order type");

		gc.select_gifttype("Occasion");

		gc.enter_amount("Gift Card amount");

		gc.next_page();

		gc.recepient_name("Recepient Name");
		
		gc.recepient_email("Recepient Email");

		gc.sender_name("Sender Name");

		gc.sender_email("Invalid Sender Email");

		gc.sender_phone("Sender Phonenumber");

		gc.confirm();
		
		String warning = gc.Alerts();
		
		Assert.assertTrue(warning.contains("email address"));

		report.display(log, "Invalid sender email wasn't accepted");

		log.pass(MarkupHelper.createLabel("Invalid sender email wasn't accepted", ExtentColor.GREEN));

		report.closeBrowser(log);

		log.pass(MarkupHelper.createLabel("Browser closed successfully", ExtentColor.GREEN));


	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		// System.out.println("after method");
		if (result.isSuccess())
			excel.reportToExcel("Invalid sender email accepting Test: SUCCESS");
		else
			excel.reportToExcel("Invalid sender email accepting Test: FAILURE");
	}

	@AfterClass
	public void closeBrowser() throws IOException {
		excel.reportToExcel("Invalid recepient email accepting Test: ENDED");
		// close the driver

		driver.quit();
	}


}
