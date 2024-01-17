package PageObjectModelPackage;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilitiesPackage.BaseUtilities;
import UtilitiesPackage.Reusable;

public class Homepage_SwagLabs extends BaseUtilities {
	WebDriver driver;
	Reusable reuse;

	public Homepage_SwagLabs(WebDriver driverhere) {
		super(driverhere);
		this.driver = driverhere;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	WebElement username_textBox;
	@FindBy(id = "password")
	WebElement password_textBox;
	@FindBy(name = "login-button")
	WebElement login_Button;
	@FindBy(name = "back-to-products")
	WebElement backToProducts_link;
	@FindBy(xpath="//div[@class='error-message-container error']")
	WebElement lockedOutUserErrorMsg;

	public void standardUser_Login() throws IOException {
		implicitwaitMethod();
		pageLoadTimeOutMethod();
		Properties props = propsMathod();
		String standardUser = props.getProperty("standardUserName");
		String password1 = props.getProperty("passwordForAllUsers");
		visibilityOfElementLocated(username_textBox, 6);
		username_textBox.sendKeys(standardUser);
		visibilityOfElementLocated(password_textBox, 6);
		password_textBox.sendKeys(password1);
		login_Button.click();
		System.out.println("Standard User Login -- Successfull");
		reuse = new Reusable(driver);
		reuse.urlEquals("https://www.saucedemo.com/inventory.html");
		System.out.println("Landed on inventory page");
		
	}

	public void lockedOutUser_Login() throws IOException {
		implicitwaitMethod();
		Properties props = propsMathod();
		String lockedOutUser = props.getProperty("lockedOutUserName");
		String password2 = props.getProperty("passwordForAllUsers");
		visibilityOfElementLocated(username_textBox, 6);
		username_textBox.sendKeys(lockedOutUser);
		visibilityOfElementLocated(password_textBox, 6);
		password_textBox.sendKeys(password2);
		login_Button.click();
		reuse = new Reusable(driver);
		reuse.innerTextEquals(lockedOutUserErrorMsg, "Epic sadface: Sorry, this user has been locked out.");
		System.out.println("LockedOutUser_Login -- Locked");

	}

	public void problemUser_Login() throws IOException {
		implicitwaitMethod();
		Properties props = propsMathod();
		String problemUser = props.getProperty("problemUserName");
		String password3 = props.getProperty("passwordForAllUsers");
		username_textBox.sendKeys(problemUser);
		password_textBox.sendKeys(password3);
		login_Button.click();
		System.out.println("ProblemUser_Login -- Successfull");
	}

	public void performanceGlitchUser_Login() throws IOException {
		implicitwaitMethod();
		Properties props = propsMathod();
		String performance_glitch_user = props.getProperty("performanceGlitchUserName");
		String password4 = props.getProperty("passwordForAllUsers");
		username_textBox.sendKeys(performance_glitch_user);
		password_textBox.sendKeys(password4);
		login_Button.click();
		System.out.println("performanceGlitchUser_Login -- Successfull");
	}

	public void errorUser_Login() throws IOException {
		implicitwaitMethod();
		Properties props = propsMathod();
		String error_user = props.getProperty("errorUserName");
		String password5 = props.getProperty("passwordForAllUsers");
		username_textBox.sendKeys(error_user);
		password_textBox.sendKeys(password5);
		login_Button.click();
		System.out.println("errorUser_Login -- Successfull");
	}

	public void visualUser_Login() throws IOException {
		implicitwaitMethod();
		Properties props = propsMathod();
		String visual_user = props.getProperty("VisualUserName");
		String password6 = props.getProperty("passwordForAllUsers");
		username_textBox.sendKeys(visual_user);
		password_textBox.sendKeys(password6);
		login_Button.click();
		System.out.println("visualUser_Login -- Successfull");
	}

}
