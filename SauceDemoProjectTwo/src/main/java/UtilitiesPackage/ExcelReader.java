package UtilitiesPackage;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
	public Object[][] readTestData(String filePath, String sheetName) {
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows][cols];

            for (int i = 1; i <= rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    DataFormatter formatter = new DataFormatter();
                    data[i - 1][j] = formatter.formatCellValue(cell);
                }
            }

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }
}

	

