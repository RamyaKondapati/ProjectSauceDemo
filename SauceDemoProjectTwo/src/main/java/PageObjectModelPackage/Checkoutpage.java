package PageObjectModelPackage;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilitiesPackage.BaseUtilities;

import UtilitiesPackage.Reusable;

public class Checkoutpage extends BaseUtilities {
	WebDriver driver;
	Reusable reuse;

	public Checkoutpage(WebDriver driverhere) {
		super(driverhere);
		this.driver = driverhere;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Checkout: Your Information']")
	WebElement chechoutYourInfoText;
	@FindBy(name = "cancel")
	WebElement cancelBtn;
	@FindBy(name = "continue")
	WebElement continueBtn;
	@FindBy(name = "checkout")
	WebElement checkoutBtn;
	@FindBy(id = "first-name")
	WebElement firstnName_Tb;
	@FindBy(id = "last-name")
	WebElement lastName_Tb;
	@FindBy(id = "postal-code")
	WebElement postalCode_Tb;
	@FindBy(name = "finish")
	WebElement finishBtn;
	@FindBy(xpath = "//span[text()='Checkout: Overview']")
	WebElement CheckoutOverviewText;
	@FindBy(xpath = "//div[@class='error-message-container error']")
	WebElement errorMessageElement;
	@FindBy(xpath = "//h2[text()='Thank you for your order!']")
	WebElement thankUMsg;
	@FindBy(xpath = "//button[text()='Back Home']")
	WebElement backToHome;
	@FindBy(xpath="//div[@class='summary_subtotal_label']")
	WebElement itemTotal;
	@FindBy(xpath="//div[@class='inventory_item_price']")
	List<WebElement> itemPrice;

	public void clickOnCancelBtn() {
		cancelBtn.click();
		reuse.urlEquals("https://www.saucedemo.com/cart.html");
		waitForElementToBeClickable(checkoutBtn, 10).click();
		reuse.innerTextEquals(CheckoutOverviewText, "Checkout: Overview");
	}

	public void enterFirstName(String firstName) {

		firstnName_Tb.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {

		lastName_Tb.sendKeys(lastName);
	}

	public void enterPostalCode(String postalCode) {

		postalCode_Tb.sendKeys(postalCode);
	}

	public String getErrorMessage() {
		visibilityOfElementLocated(errorMessageElement, 6);
		return errorMessageElement.getText().trim();
	}

	public double calculateSumOfProductPrices() {
        List<WebElement> productPrices = itemPrice;

        double sum = 0.0;
        for (WebElement itemPrice : productPrices) {
            String priceText = itemPrice.getText().replace("$", "").trim();
            double price = Double.parseDouble(priceText);
            sum += price;
        }

        return sum;
    }
	
	public void verifyPriceTotal()
	{
	double sumOfProductPrices = calculateSumOfProductPrices();
	String total = itemTotal.getText().replace("Item total: $", "").trim();
	double priceTotal = Double.parseDouble(total);
    assertEquals(priceTotal, sumOfProductPrices, "Price Total is not as expected");

	}
	
	private String getExpectedErrorMessage(String firstName, String lastName, String postalCode) {
		// Your logic to determine the expected error message based on input data
		if (firstName.isEmpty() && lastName.isEmpty() && postalCode.isEmpty()) {
			return "Error: First Name, Last Name, and Postal Code are required";
		} else if (firstName.isEmpty()) {
			return "Error: First Name is required";
		} else if (lastName.isEmpty()) {
			return "Error: Last Name is required";
		} else if (postalCode.isEmpty()) {
			return "Error: Postal Code is required";
		} else {
			// Handle other scenarios if needed
			return getErrorMessage();
		}
	}

	public void fillUserInformation(String firstName, String lastName, String postalCode) {
		System.out.println("fcasvbfnvsdnklf,b");
		// Fill out user information
		firstnName_Tb.sendKeys(firstName);
		lastName_Tb.sendKeys(lastName);
		postalCode_Tb.sendKeys(postalCode);
		String expectedErrorMessage = getExpectedErrorMessage(firstName, lastName, postalCode);
	    String actualErrorMessage = getErrorMessage();
	    // Validate the error message
		assertEquals(actualErrorMessage, expectedErrorMessage,
	            "Error message validation failed for input data: " +
	                    "firstName=" + firstName + ", lastName=" + lastName + ", postalCode=" + postalCode);
	}

	public void clickContinueButton() {

		waitForElementToBeClickable(continueBtn, 10).click();
	}

	public void navigateToCheckout() {
		waitForElementToBeClickable(checkoutBtn, 10).click();

	}

	public void clickOnFinishBtn() {
		waitForElementToBeClickable(finishBtn, 10).click();
		
	}
//	
//	
//	 @DataProvider(name = "checkoutTestData")
//	    public Iterator<Object[]> provideCheckoutTestData() throws IOException {
//	        String filePath = "/SauceDemoProjectTwo/src/main/java/TestDataPackage/ExcelData.xlsx";  // Replace with the actual path to your Excel file
//	        return ExcelUtils.getTestData(filePath);
//	    }
//
//	    @Test(dataProvider = "checkoutTestData")
//	    public void testCheckoutStepOne(String firstName,String lastName, int postalCode ) {
//	        // Perform actions on the Checkout page
//	        enterFirstName((sheet.getRow(6).getCell(1)).toString());
//	        enterLastName(lastName);
//	        enterPostalCode(postalCode);
//	        clickContinueButton();
//
//	        // Validate the error message
//	        String actualErrorMessage = getErrorMessage();
//	        assertEquals(actualErrorMessage, expectedErrorMessage,
//	                "Error message validation failed for input data: " +
//	                        "firstName=" + firstName + ", lastName=" + lastName + ", postalCode=" + postalCode);
//	    }

//	public void testChechoutSteps() throws IOException, InterruptedException {
//		continueBtn.click();
//			FileInputStream fs1 = new FileInputStream(System.getProperty("user.dir") + ("\\src\\main\\java\\TestDataPackage\\ExcelData.xlsx"));
//
//			XSSFWorkbook workbook = new XSSFWorkbook(fs1);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
//			        Row row = sheet.getRow(i);
//			        for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
//			            Cell cell = row.getCell(j);
//			   
//			   
////				// Perform actions on the Checkout page
////			    enterFirstName(firstName);
////			    Thread.sleep(2000);
////			    enterLastName(lastName);
////			    Thread.sleep(2000);
////			    enterPostalCode(postalCode);
////			    Thread.sleep(2000);
//			    String firstName = (row.getCell(j)).toString();
//			    String lastName = (row.getCell(j+1)).toString();
//			   // String lastName = (sheet.getRow(2).getCell(1)).toString();
//			    String postalCode = (row.getCell(j+2)).toString();
//			    
//			    fillUserInformation(firstName, lastName, postalCode);
//			    String expectedErrorMessage = getExpectedErrorMessage(firstName, lastName, postalCode);
//			    String actualErrorMessage = getErrorMessage();
//			    // Validate the error message
////				assertEquals(actualErrorMessage, expectedErrorMessage,
////			            "Error message validation failed for input data: " +
////			                    "firstName=" + firstName + ", lastName=" + lastName + ", postalCode=" + postalCode);
//			  //  isTrue(true, actualErrorMessage);
//				 
//
//				}
//				}
//		
//      
//      //  driver.navigate().forward();
//        visibilityOfElementLocated(finishBtn, 10); 
//
//		
//		}

	public void checkOutForm() throws InterruptedException {
		continueBtn.click();
		
		// Perform actions on the Checkout page
		enterFirstName("ramya");
		Thread.sleep(2000);
		enterLastName("Kondapati");
		Thread.sleep(2000);
		enterPostalCode("123456789");
		Thread.sleep(2000);
		
	}

	public void checkoutComplete() throws InterruptedException {
		Thread.sleep(2000);
		reuse = new Reusable(driver);
		visibilityOfElementLocated(thankUMsg, 10);
		reuse.innerTextEquals(thankUMsg, "Thank you for your order!");
		reuse.urlEquals("https://www.saucedemo.com/checkout-complete.html");
		waitForElementToBeClickable(backToHome, 10).click();
		reuse.urlEquals("https://www.saucedemo.com/inventory.html");

	}
}
