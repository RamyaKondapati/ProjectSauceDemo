package PageObjectModelPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import UtilitiesPackage.BaseUtilities;
import UtilitiesPackage.Reusable;

public class Inventorypage extends BaseUtilities {

	WebDriver driver;
	Reusable reuse;
	Cartpage cart;

	// BaseUtilities baseUtilities;
	public Inventorypage(WebDriver driverhere) {
		super(driverhere);
		this.driver = driverhere;
		// this.baseUtilities = new BaseUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='inventory_item_name ']")
	List<WebElement> allProducts;
	// inventory_item_name inventory_item_img
	@FindBy(xpath = "//div[@class='inventory_item_price']")
	List<WebElement> allProductsPrice;
	@FindBy(xpath = "//a[@id='item_0_img_link']")
	WebElement bikeLight_Img;
	@FindBy(xpath = "//a[@id='item_0_title_link']")
	WebElement bikeLight_namelink;
	@FindBy(xpath = "//a[@id='item_1_img_link']")
	WebElement boltTshirt_Img;
	@FindBy(xpath = "//a[@id='item_1_title_link']")
	WebElement boltTshirt_namelink;
	@FindBy(xpath = "//a[@id='item_2_img_link']")
	WebElement onesie_Img;
	@FindBy(xpath = "//a[@id='item_2_title_link']")
	WebElement onesie_namelink;
	@FindBy(xpath = "//a[@id='item_3_img_link']")
	WebElement tShirtRed_Img;
	@FindBy(xpath = "//a[@id='item_3_title_link']")
	WebElement tShirtRed_namelink;
	@FindBy(xpath = "//a[@id='item_4_img_link']")
	WebElement backpack_Img;
	@FindBy(xpath = "//a[@id='item_4_title_link']")
	WebElement backpack_namelink;
	@FindBy(xpath = "//a[@id='item_5_img_link']")
	WebElement fleeceJacket_Img;
	@FindBy(xpath = "//a[@id='item_5_title_link']")
	WebElement fleeceJacket_namelink;
	@FindBy(name = "back-to-products")
	WebElement backToProducts_link;
	@FindBy(xpath = "//div[@class='inventory_details_name large_size']")
	WebElement inventoryDetails;
	@FindBy(id = "react-burger-menu-btn")
	WebElement menu_Button;
	@FindBy(id = "inventory_sidebar_link")
	WebElement allItems_link;
	@FindBy(id = "about_sidebar_link")
	WebElement about_sidebar_link;
	@FindBy(id = "logout_sidebar_link")
	WebElement logout_sidebar_link;
	@FindBy(id = "reset_sidebar_link")
	WebElement resetAppState;
	@FindBy(id = "react-burger-cross-btn")
	WebElement menuCrossBtn;

	@FindBy(xpath = "//span[@class='shopping_cart_badge']")
	boolean countOfCart;
	@FindBy(xpath = "//select[@class='product_sort_container']")
	WebElement productsFilterDropDown;

	

	public List<WebElement> verifyAllproducts() {
		System.out.println(allProducts.size());
		return allProducts;
	}

	public void clickAndVerifyAllProducts() throws InterruptedException {

		List<WebElement> productList = verifyAllproducts();
		for (int i = 0; i < productList.size(); i++) {
			WebElement product = productList.get(i);
			String producttext = product.getText();
			System.out.println("Clicking on product -- " + (i + 1) + producttext);
			visibilityOfElementLocated(product, 10);
			waitForElementToBeClickable(product, 10).click();
			reuse = new Reusable(driver);
			reuse.innerTextEquals(inventoryDetails, producttext);
			Thread.sleep(1000);
			waitForElementToBeClickable(backToProducts_link, 10).click();
			Thread.sleep(1000);
			reuse.urlEquals("https://www.saucedemo.com/inventory.html");
		}
		System.out.println("All products Verified");
	}
//	 public List<String> getFilteredItemNames() {
//	     
//	        return allProducts.stream().map(WebElement::getText).collect(Collectors.toList());
//	    }

	public void clickOnMenuButton() throws InterruptedException {
		visibilityOfElementLocated(menu_Button, 10);
		waitForElementToBeClickable(menu_Button, 10).click();
Thread.sleep(2000);
	}

	public void clickOnAllItemsLink() {
		visibilityOfElementLocated(allItems_link, 10);
		waitForElementToBeClickable(allItems_link, 10).click();
	}

	public void clickOnAboutSidebar_link() {
		visibilityOfElementLocated(about_sidebar_link, 10);
		waitForElementToBeClickable(about_sidebar_link, 10).click();
		driver.navigate().back();
	}

	public void clickOnMenuCrossBtn() {
		visibilityOfElementLocated(menuCrossBtn, 10);
		waitForElementToBeClickable(menuCrossBtn, 10).click();

	}

	public void clickOnLogout() {
		visibilityOfElementLocated(logout_sidebar_link, 10);
		waitForElementToBeClickable(logout_sidebar_link, 10).click();

	}

	public void resetAppState() {
		visibilityOfElementLocated(resetAppState, 10);
		waitForElementToBeClickable(resetAppState, 10).click();

		isFalse(countOfCart, "reset app state");
	}

	public void click_Verify_FilterDropDown() throws IOException, InterruptedException {

		verifyItemNamesSorting("Name (Z to A)");
		verifyItemNamesSorting("Name (A to Z)");
		verifyPriceSorting("Price (low to high)");
		verifyPriceSorting("Price (high to low)");
		System.out.println("Filter products is Successfull");

		//driver.switchTo().alert().accept();
	}

	public void verifyItemNamesSorting(String filterOption) throws InterruptedException, IOException {
		try {
		visibilityOfElementLocated(productsFilterDropDown, 10);
		Select select = new Select(productsFilterDropDown);
	   // String filterOption = props.getProperty("nameZtoA");
		
		select.selectByVisibleText(filterOption);
		Thread.sleep(4000);
		// Get the list of item names from the page
		List<String> actualItemNames = new ArrayList<>();
		for (WebElement itemElement : allProducts) {
			actualItemNames.add(itemElement.getText());
		}
		// Create a copy of the actual item names and sort it
		List<String> expectedItemNames = new ArrayList<>(actualItemNames);
		if (filterOption.contains("Name (Z to A)")) {
			Collections.sort(expectedItemNames, Collections.reverseOrder());
		} else {
			Collections.sort(expectedItemNames);
		}
		// Verify that the items are sorted as expected
		SoftAssert soft = new SoftAssert();
				soft.assertEquals(actualItemNames, expectedItemNames,
						"Items are not sorted correctly after applying the filter");
		 System.out.println(actualItemNames +" "+ expectedItemNames);
		 soft.assertAll();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void verifyPriceSorting(String filterOption) throws InterruptedException, IOException {
		//Properties props = propsMathod();
		visibilityOfElementLocated(productsFilterDropDown, 15);
		Select select = new Select(productsFilterDropDown);
	   // String filterOption = props.getProperty("nameZtoA");
		
		select.selectByVisibleText(filterOption);
		Thread.sleep(4000);
				List<Double> actualItemsPrice = new ArrayList<>();
		for (WebElement priceElement : allProductsPrice) {
			actualItemsPrice.add(Double.parseDouble(priceElement.getText().replace("$", "")));
		}
		List<Double> expectedItemsPrice = new ArrayList<>(actualItemsPrice);
		if (filterOption.contains("Price (low to high)")) {
			Collections.sort(expectedItemsPrice);
		} else {
			Collections.sort(expectedItemsPrice, Collections.reverseOrder());
		}
		 System.out.println(actualItemsPrice +" "+ expectedItemsPrice);
		
		// Verify that the price are sorted as expected
		Assert.assertEquals(actualItemsPrice, expectedItemsPrice,
				"Price are not sorted correctly after applying the filter");
	}

}
