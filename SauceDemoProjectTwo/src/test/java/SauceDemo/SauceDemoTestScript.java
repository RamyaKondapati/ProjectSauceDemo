package SauceDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ListenersPackage.CustomTestListeners;
import PageObjectModelPackage.*;

@Listeners(CustomTestListeners.class)
public class SauceDemoTestScript {

	WebDriver driver;
	Homepage_SwagLabs homepage;
	Inventorypage productspage;
	Cartpage cartpage;
	Footerpage footerpage;
	Checkoutpage checkoutpage;

	@BeforeMethod
	public void setup() throws IOException {

		Properties props = new Properties();
		File propFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\TestDataPackage\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String browser = props.getProperty("browserName");
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.get(props.getProperty("url"));
		homepage = new Homepage_SwagLabs(driver);
		productspage = new Inventorypage(driver);
		cartpage = new Cartpage(driver);
		footerpage = new Footerpage(driver);
		checkoutpage = new Checkoutpage(driver);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

//	@BeforeMethod
//	public void openSauceDemoUrl()
//	{
//		homepage = new Homepage_SwagLabs(driver);
//		driver.get("https://www.saucedemo.com/");
//	}
//	public void closeCurrentTab()
//	{
//		if(driver!=null) 
//		{
//		driver.close();
//		}
//	}

	@Test(priority = 1)
	public void loginWith_StandardUserName() throws   IOException, InterruptedException {
		homepage.standardUser_Login();
		productspage.click_Verify_FilterDropDown();
		productspage.clickAndVerifyAllProducts();
		//cartpage.clickAllAddToCartButtons();
		//cartpage.add_AllProducts_To_Cart();
		cartpage.testAddToCartForAllProducts();
		productspage.clickOnMenuButton();
		productspage.clickOnAllItemsLink();
		productspage.clickOnAboutSidebar_link();
		productspage.clickOnMenuButton();
		productspage.clickOnMenuCrossBtn();
		footerpage.testFooterText();
		footerpage.clickOnSocialIcons();
		cartpage.shoppingCart();
		checkoutpage.clickContinueButton();
		// checkoutpage.fillUserInformation("ramya", "battula", "90009");
		checkoutpage.checkOutForm();
		checkoutpage.clickContinueButton();
		checkoutpage.verifyPriceTotal();
		checkoutpage.clickOnFinishBtn();
		checkoutpage.checkoutComplete();
		productspage.clickOnMenuButton();
	    productspage.resetAppState();
		productspage.clickOnLogout();
	}

	@Test(priority = 2)
	public void loginWith_LockedOutUserName() throws IOException, InterruptedException {
		homepage.lockedOutUser_Login();

	}
	@Test(priority = 3)
	public void loginWith_ProblemUserName() throws IOException, InterruptedException {
		homepage.problemUser_Login();
//		productspage.click_Verify_FilterDropDown();
//		productspage.clickAndVerifyAllProducts();
//		cartpage.testAddToCartForAllProducts();
		productspage.clickOnMenuButton();
		productspage.clickOnAllItemsLink();
		productspage.clickOnAboutSidebar_link();
		productspage.clickOnMenuButton();
		productspage.clickOnMenuCrossBtn();
		footerpage.testFooterText();
		footerpage.clickOnSocialIcons();
		cartpage.shoppingCart();
		checkoutpage.clickContinueButton();
		checkoutpage.checkOutForm();
		checkoutpage.clickContinueButton();
		checkoutpage.verifyPriceTotal();
//		checkoutpage.clickOnFinishBtn();
//		checkoutpage.checkoutComplete();
//		productspage.clickOnMenuButton();
//	    productspage.resetAppState();
//		productspage.clickOnLogout();

	}

	@Test(priority = 4)
	public void loginWith_PerformanceGlitchUser() throws IOException, InterruptedException {
		homepage.performanceGlitchUser_Login();
		productspage.click_Verify_FilterDropDown();
		cartpage.testAddToCartForAllProducts();
		productspage.clickOnMenuButton();
		productspage.clickOnAllItemsLink();
		productspage.clickOnAboutSidebar_link();
		productspage.clickOnMenuButton();
		productspage.clickOnMenuCrossBtn();
		footerpage.testFooterText();
		footerpage.clickOnSocialIcons();
		cartpage.shoppingCart();
		checkoutpage.clickContinueButton();
		checkoutpage.checkOutForm();
		checkoutpage.clickContinueButton();
		checkoutpage.verifyPriceTotal();
		checkoutpage.clickOnFinishBtn();
		checkoutpage.checkoutComplete();
		productspage.clickOnMenuButton();
	    productspage.resetAppState();
		productspage.clickOnLogout();
	}

	@Test(priority = 5)
	public void loginWith_ErrorUser() throws IOException, InterruptedException {
		homepage.errorUser_Login();
		productspage.click_Verify_FilterDropDown();
		cartpage.testAddToCartForAllProducts();
		productspage.clickOnMenuButton();
		productspage.clickOnAllItemsLink();
		productspage.clickOnAboutSidebar_link();
		productspage.clickOnMenuButton();
		productspage.clickOnMenuCrossBtn();
		footerpage.testFooterText();
		footerpage.clickOnSocialIcons();
		cartpage.shoppingCart();
		checkoutpage.clickContinueButton();
		checkoutpage.checkOutForm();
		checkoutpage.clickContinueButton();
		checkoutpage.verifyPriceTotal();
//		checkoutpage.clickOnFinishBtn();
//		checkoutpage.checkoutComplete();
//		productspage.clickOnMenuButton();
//	    productspage.resetAppState();
//		productspage.clickOnLogout();

	}

	@Test(priority = 6)
	public void loginWith_VisualUser() throws IOException, InterruptedException {
		homepage.visualUser_Login();
		productspage.click_Verify_FilterDropDown();
		cartpage.testAddToCartForAllProducts();
		productspage.clickOnMenuButton();
		productspage.clickOnAllItemsLink();
		productspage.clickOnAboutSidebar_link();
		productspage.clickOnMenuButton();
		productspage.clickOnMenuCrossBtn();
		footerpage.testFooterText();
		footerpage.clickOnSocialIcons();
		cartpage.shoppingCart();
		checkoutpage.clickContinueButton();
		checkoutpage.checkOutForm();
		checkoutpage.clickContinueButton();
		checkoutpage.verifyPriceTotal();
		checkoutpage.clickOnFinishBtn();
		checkoutpage.checkoutComplete();
		productspage.clickOnMenuButton();
	    productspage.resetAppState();
		productspage.clickOnLogout();

	}

}
