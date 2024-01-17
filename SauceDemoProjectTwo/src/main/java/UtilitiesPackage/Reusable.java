package UtilitiesPackage;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.Table.Cell;


public class Reusable {
	WebDriver driver;

	public Reusable(WebDriver driverhere)
	{
		this.driver=driverhere;
		PageFactory.initElements(driver,this);
	}

	// Method to verify the actual Url is equal to expected Url
	public void urlEquals(String expectedUrl)
	{
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual Url is not Equal to the expectedUrl ("+expectedUrl+")");

	}
	// Method to verify the innerHtmlText of the single webelement is equal to the expected text
	public void innerTextEquals(WebElement element, String expectedText) {
		String actualText = element.getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualText, expectedText);
		soft.assertAll();
		//System.out.println(actualText);
	}



	 public static Iterator<Object[]> getTestData(String filePath) throws IOException {
	        List<Object[]> testDataList = new ArrayList<>();

	        try (FileInputStream fis = new FileInputStream(filePath);
	             Workbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = (Sheet) workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = sheet.iterator();

	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();

	                List<Object> rowData = new ArrayList<>();
	                while (cellIterator.hasNext()) {
	                    Cell cell = (Cell) cellIterator.next();

	                    switch (((org.apache.poi.ss.usermodel.Cell) cell).getCellType()) {
	                        case STRING:
	                            rowData.add(((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue());
	                            break;
	                        case NUMERIC:
	                            rowData.add(String.valueOf((int) ((org.apache.poi.ss.usermodel.Cell) cell).getNumericCellValue()));
	                            break;
	                        // Add more cases as needed for other cell types

	                        default:
	                            break;
	                    }
	                }

	                testDataList.add(rowData.toArray());
	            }
	        }

	        return testDataList.iterator();
	    }



}
