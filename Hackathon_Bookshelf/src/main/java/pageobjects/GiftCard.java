package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GiftCard {
	
	WebDriver driver;
	
	@FindBy(xpath = "//a[contains(text(),'Gift Cards')]")
	WebElement select_giftcard;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/section[1]/ul[1]/li[3]")
	WebElement select_birthdayoranniversary;
	
	@FindBy(id = "ip_2251506436")
	WebElement txt_amount;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/section[2]/div[1]/section[2]/button[1]")
	WebElement next;
	
	@FindBy(name = "recipient_name")
	WebElement receipent_name;
	
	@FindBy(name = "recipient_email")
	WebElement receipent_email;
	
	@FindBy(name = "customer_name")
	WebElement sender_name;
	
	@FindBy(name = "customer_email")
	WebElement sender_email;
	
	@FindBy(name = "customer_mobile_number")
	WebElement sender_phone;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/section[3]/form[1]/button[1]")
	WebElement btn_cnfrm;
	
	public GiftCard(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enter_to_giftcard() {
		select_giftcard.click();
	}
	
	public void select_gifttype() {
		select_birthdayoranniversary.click();
	}
	
	public void enter_amount(String amount) {
		
		amount = amount.substring(0, 4);
		txt_amount.sendKeys(amount);
	}

	public void next_page() {
		next.click();
	}
	
	public void recepient_name(String name) {
		receipent_name.sendKeys(name);
	}
	
	public void recepient_email(String email) {
		receipent_email.sendKeys(email);
	}
	
	public void sender_name(String name) {
		sender_name.sendKeys(name);
	}
	
	public void sender_email(String email) {
		sender_email.sendKeys(email);
	}
	
	public void sender_phone(String phone) {
		sender_phone.sendKeys(phone);
	}
	
	public void confirm() {
		btn_cnfrm.click();
	}
}
