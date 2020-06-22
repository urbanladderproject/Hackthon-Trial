package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Display_Collections {
	
	WebDriver driver;
	
	//assigns the xpath to the corresponding By classes
	@FindBy(xpath = "/html[1]/body[1]/div[1]/header[1]/div[2]/div[1]/nav[1]/div[1]/ul[1]/li[10]/div[1]/div[1]/ul[1]/li[1]/ul[1]")
	WebElement list_data;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/header[1]/div[2]/div[1]/nav[1]/div[1]/ul[1]/li[10]/span[1]")
	WebElement click_collections;
	
	
	public Display_Collections(WebDriver driver) {
		this.driver = driver;
	}
	
	public void collection_click() {
		click_collections.click();
	}
	
	
	public String[] getdata() {
		
		String Data = list_data.getText();
		String[] info = Data.split("\\r?\\n");
		return info;
		
	}
	
	public void displaydata(String[] data) {
		
		int length = data.length;
		System.out.println("BEING AT HOME");
		for(int i=0;i<length;i++) 
		{
			System.out.println(data[i]);
		}		
	}

}