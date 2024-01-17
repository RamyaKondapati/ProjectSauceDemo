package PageObjectModelPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UtilitiesPackage.BaseUtilities;
import UtilitiesPackage.Reusable;

public class Cartpage extends BaseUtilities {
	WebDriver driver;
	Reusable reuse;

	public Cartpage(WebDriver driverhere) {
		super(driverhere);
		this.driver = driverhere;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "add-to-cart-sauce-labs-backpack")
	WebElement addtocartBackpack;
	@FindBy(xpath = "//div[@class='inventory_list']//button[text()='Add to cart']")
	List<WebElement> addToCart_Buttons;
	private By addToCart_Button = By.xpath("//div[@class='inventory_list']//button[text()='Add to cart']");

	@FindBy(xpath = "//button[text()='Remove']")
	WebElement remove_Button;
	@FindBy(xpath = "//div[@class='inventory_item']//button[text()='Remove']")
	List<WebElement> listOfRemoveBtn;
	
	@FindBy(xpath="//span[@class='shopping_cart_badge']") WebElement countOfCart;
	@FindBy(xpath="//div[@class='cart_item']") WebElement cartItemsInTable;
    @FindBy(xpath="//span[@class='title']") WebElement yourCart;
    @FindBy(xpath="//span[text()='Checkout: Your Information']") WebElement checkoutYourInformation;
    @FindBy(xpath="//a[@class='shopping_cart_link']") WebElement shoppingCartLink;
    @FindBy(name="continue-shopping") WebElement continueShoppingBtn;
    @FindBy(name="checkout") WebElement checkoutBtn;

	public List<WebElement> verifyAll_AddToCartButton() {
		System.out.println(addToCart_Buttons.size());
		return addToCart_Buttons;
	}

	public void shoppingCart()
	{
		shoppingCartLink.click();
		actualAndExpectedEquals(yourCart, "Your Cart", "Your Cart not displayed");
		continueShoppingBtn.click();
		driver.navigate().back();
		checkoutBtn.click();
		
	}
	
	
	
	public void add_AllProducts_To_Cart() throws InterruptedException {

		List<WebElement> addToCartList = verifyAll_AddToCartButton();
		for (WebElement cart : addToCartList) {
			WebElement addToCart_Button = cart;
			visibilityOfElementLocated(cart, 40);
			waitForElementToBeClickable(cart, 40).click();
			Thread.sleep(2000);
			String addedToCart = addToCart_Button.getText();
			System.out.println("Added product to cart -- " + addedToCart);
			Thread.sleep(2000);
			reuse = new Reusable(driver);
			reuse.innerTextEquals(remove_Button, addedToCart);
			System.out.println("All products Verified");
		}
	}

	public void clickAllAddToCartButtons() throws InterruptedException {
		driver.findElements((By) addToCart_Button).forEach(WebElement::click);
		Thread.sleep(3000);
		System.out.println("Add product to cart -- " + addToCart_Buttons);
		System.out.println("All Verified");
	}

	public void testAddToCartForAllProducts() throws InterruptedException {
		List<WebElement> addToCartButtons = verifyAll_AddToCartButton();
		for (WebElement button : addToCartButtons) {
			button.click();
		}
		System.out.println("Verified all AddToCart Buttons For AllProducts");
		elementAvailable(remove_Button, true);
		
		// Assertions
	    int actualCartItemCount = getCartItemCount();
	    int expectedCartItemCount = listOfRemoveBtn.size();

	    System.out.println("Actual Cart Item Count: " + actualCartItemCount);
	    System.out.println("Expected Cart Item Count: " + expectedCartItemCount);

	    Assert.assertEquals(actualCartItemCount, expectedCartItemCount,
	            "Number of items in the cart should match the number of 'Add to Cart' buttons clicked.");

	}
	
	// Assert.assertTrue(isCartNotEmpty(), "Cart should not be empty after adding items.");
	private boolean isCartNotEmpty() {
		
	    return cartItemsInTable.isDisplayed();
	}

	
	private int getCartItemCount() {
		String cartItemCountText = countOfCart.getText();
		 try {
		        return Integer.parseInt(cartItemCountText);
		    } catch (NumberFormatException e) {
		        return 0;
		    }
	}
	
}



