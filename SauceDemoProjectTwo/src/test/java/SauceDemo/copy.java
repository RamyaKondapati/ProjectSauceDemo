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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjectModelPackage.Cartpage;
import PageObjectModelPackage.Checkoutpage;
import PageObjectModelPackage.Footerpage;
import PageObjectModelPackage.Homepage_SwagLabs;
import PageObjectModelPackage.Inventorypage;
import UtilitiesPackage.ExcelReader;


public class copy{
	
	WebDriver driver;
	Homepage_SwagLabs homepage;
	Inventorypage productspage; 
	Cartpage cartpage;
	
	Footerpage footerpage;
	Checkoutpage checkoutpage;
	
	@BeforeMethod
	public void setup() throws IOException
	{
		
		Properties	props = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\TestDataPackage\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String browser = props.getProperty("browserName");
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge"))
		{
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
	public void tearDown()
	{
		if(driver!=null) 
		{
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
	
	@Test(priority=1)
	public void testStandardUserName() throws IOException, InterruptedException
	{
	//	allusers.loginWith_StandardUserName();
	
		
	}
	@Test(priority=2)
	public void loginWith_LockedOutUserName() throws IOException, InterruptedException
	{
		homepage.lockedOutUser_Login();
		cartpage.testAddToCartForAllProducts();
		
	}
	@Test(priority=3)
	public void loginWith_ProblemUserName() throws IOException, InterruptedException
	{
		homepage.problemUser_Login();
		cartpage.testAddToCartForAllProducts();
	
	}
	@Test(priority=4)
	public void loginWith_PerformanceGlitchUser() throws IOException, InterruptedException
	{
		homepage.performanceGlitchUser_Login();
		//cartpage.testAddToCartForAllProducts();
		//productspage.click_Verify_FilterDropDown();
		footerpage.testFooterText();
        cartpage.shoppingCart();
		//footerpage.clickOnSocialIcons();
       // checkoutpage.testChechoutSteps();
        
        checkoutpage.clickContinueButton();
      //  checkoutpage.fillUserInformation("ramya", "battula", "90009");
        checkoutpage.checkOutForm();
        checkoutpage.clickContinueButton();
        checkoutpage.clickOnFinishBtn();
        checkoutpage.checkoutComplete();
	}
	@Test(priority=5)
	public void loginWith_ErrorUser() throws IOException, InterruptedException
	{
		homepage.errorUser_Login();
		cartpage.testAddToCartForAllProducts();
		
	}
	@Test(priority=5)
	public void loginWith_VisualUser() throws IOException, InterruptedException
	{
		homepage.visualUser_Login();
		//cartpage.testAddToCartForAllProducts();
		productspage.click_Verify_FilterDropDown();
		
		
	}
	
	
}
