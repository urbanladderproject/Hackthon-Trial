package pageobjects;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.Excelutils;


public class Display_Collections {

	WebDriver driver;

	//assigns the xpath to the corresponding By classes
	@FindBy(xpath = "/html[1]/body[1]/div[1]/header[1]/div[2]/div[1]/nav[1]/div[1]/ul[1]/li[10]/div[1]/div[1]/ul[1]/li[1]/ul[1]")
	WebElement list_data;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/header[1]/div[2]/div[1]/nav[1]/div[1]/ul[1]/li[10]/span[1]")
	WebElement click_collections;

	Map<String, String> input_data;

	String[] info;


	public Display_Collections(WebDriver driver) throws Exception {
		this.driver = driver;
		this.input_data = Excelutils.readExcelData("test_input_data");
	}

	public void collection_click(String value) {

		value = input_data.get(value);
		
		if(value.equalsIgnoreCase("Collections"))
			click_collections.click();
		else
			System.out.println("You have to select Collections menu but you have tried to select " + value);

	}


	public String[] getdata(String submenu) {

		submenu = input_data.get(submenu);
		
		String Data;
		if(submenu.equalsIgnoreCase("Being at home"))
		{
			Data = list_data.getText();
			info = Data.split("\\r?\\n");
			
		}

		else
			System.out.println("You have to choose the sub menu 'Being at Home', but you have trien to select " + submenu);
		
		return info;
	}

	public void displaydata() {

		int length = info.length;
		System.out.println("BEING AT HOME");
		for(int i=0;i<length;i++) 
		{
			System.out.println(info[i]);
		}		
	}

	public void write_result(String browser) throws Exception {
		String filename = "testresult_" + browser +".xlsx";

		//writes the results to an excel sheet respective to the browser
		Excelutils.writeExcelCollectionsData(info,filename,browser);


		//waits for 30 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.navigate().to("www.urbanladder.com");
	}

}