package UtilitiesPackage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseUtilities {

	static WebDriver driver;
	WebDriverWait wait;

	// Properties props;
	public BaseUtilities(WebDriver driverhere) {
		BaseUtilities.driver = driverhere;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public Properties propsMathod() throws IOException {
		Properties props = new Properties();
		File propFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\TestDataPackage\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return props;
	}

	public static void implicitwaitMethod() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	public static void pageLoadTimeOutMethod() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	}

	public List<WebElement> visibilityOfElementLocated(WebElement ele, int i) {

		return wait.until(ExpectedConditions.visibilityOfAllElements(ele));

	}

	public WebElement waitForElementToBeClickable(WebElement product, int i) {

		return wait.until(ExpectedConditions.elementToBeClickable(product));
	}
	public void actualAndExpectedEquals(WebElement eleLocator,String expectedValue,String me)
	{
		String actualValue = eleLocator.getText();
	
		assertEquals(actualValue, expectedValue);
		System.out.println(actualValue+" = "+expectedValue );
	}

	// Method to verify whether the element is available in the application or not
	public void elementAvailable(WebElement element, boolean expectedValue) {
		boolean actualValue = element.isDisplayed();
		assertEquals(actualValue, expectedValue);
		System.out.println(actualValue);
	}
	public void isFalse(boolean f,String message) {
	
		assertFalse(f);
		System.out.println(f);
	}
	public void isTrue(boolean t,String msg) {
		
		assertTrue(t);
		System.out.println(msg);
	}

}
