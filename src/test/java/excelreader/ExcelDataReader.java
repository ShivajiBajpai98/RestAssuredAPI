package excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

public class ExcelDataReader {
    public static void main(String[] args) {
        // Path to the Excel file
        String filePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data.xlsx";

        // Name of the sheet to read
        String sheetName = "Sheet1";

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            // Create a Workbook instance from the Excel file
            Workbook workbook = WorkbookFactory.create(inputStream);

            // Get the desired sheet from the workbook
            Sheet sheet = workbook.getSheet(sheetName);

            // Iterate over each row in the sheet
            for (Row row : sheet) {
                // Iterate over each cell in the row
                for (Cell cell : row) {
                    // Retrieve the cell value based on its type
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            System.out.print("Unknown cell type");
                            break;
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
