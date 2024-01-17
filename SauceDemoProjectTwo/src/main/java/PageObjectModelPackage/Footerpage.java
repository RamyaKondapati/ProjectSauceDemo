
package PageObjectModelPackage;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilitiesPackage.BaseUtilities;
import UtilitiesPackage.Reusable;

public class Footerpage extends BaseUtilities {
	WebDriver driver;
	Reusable reuse;

	public Footerpage(WebDriver driverhere) {
		super(driverhere);
		this.driver = driverhere;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='footer_copy']") WebElement footerText;
	@FindBy(xpath = "//a[text()='Twitter']")
	WebElement twitterIcon;
	@FindBy(xpath = "//a[text()='Facebook']")
	WebElement facebook;
	@FindBy(xpath = "//a[text()='LinkedIn']")
	WebElement linkedin;
	

	 public void testFooterText() {
	        String expectedFooterText = "© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";
	        visibilityOfElementLocated(footerText, 10);
	        actualAndExpectedEquals(footerText, expectedFooterText,"Footer text does not match." );
	    }

	 public void clickOnSocialIcons() throws InterruptedException 
	 {
		 socialMediaIcons(facebook); 
		// isTrue(driver.getTitle().contains("facebook"), "FaceBook page is not opened.");
		 visibilityOfElementLocated(twitterIcon, 10);
		 socialMediaIcons(twitterIcon);
		 //isTrue(driver.getTitle().contains("twitter"), "Twitter page is not opened.");
		 socialMediaIcons(linkedin);
		// isTrue(driver.getTitle().contains("LinkedIn"), "LinkedIn page is not opened.");
		 
	 }

	 
	 public void socialMediaIcons(WebElement sIcon) throws InterruptedException
	 {
		 sIcon.click();
		 Set<String> windowHandles = driver.getWindowHandles();
		 for(String handle: windowHandles)
		 {
			 driver.switchTo().window(handle);
			 if(driver.getTitle().contains(handle)) {
				 Thread.sleep(3000);
				 System.out.println("Opened" + handle + "With Title" + driver.getTitle());
			 }
		 }
		 driver.switchTo().window((String)windowHandles.toArray()[0]);
	 }
}
