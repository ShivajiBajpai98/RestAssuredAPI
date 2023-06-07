package javatojson;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelToObjectConverter {
    public static void main(String[] args) {
        String excelFilePath = "src/test/resources/petswagger.xlsx";

        try {
            // Read Excel file and convert to Java object
            Map<String, Object> javaObject = convertExcelToObject(excelFilePath);

            // Print all values
            printAllValues(javaObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> convertExcelToObject(String filePath) throws IOException {
        Map<String, Object> javaObject = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            // Get header row
            Row headerRow = sheet.getRow(0);

            // Iterate through each column in the header row
            Iterator<Cell> headerCellIterator = headerRow.cellIterator();
            while (headerCellIterator.hasNext()) {
                Cell headerCell = headerCellIterator.next();
                int columnIndex = headerCell.getColumnIndex();
                String headerValue = headerCell.getStringCellValue();

                // Get corresponding column values in each data row
                Map<String, Object> columnData = new HashMap<>();
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row dataRow = sheet.getRow(rowIndex);
                    Cell dataCell = dataRow.getCell(columnIndex);

                    // Convert cell value to appropriate Java object type
                    Object cellValue;
                    switch (dataCell.getCellType()) {
                        case STRING:
                            cellValue = dataCell.getStringCellValue();
                            break;
                        case NUMERIC:
                            cellValue = dataCell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            cellValue = dataCell.getBooleanCellValue();
                            break;
                        default:
                            cellValue = null;
                            break;
                    }

                    // Add column value to the column data map
                    columnData.put(Integer.toString(rowIndex), cellValue);
                }

                // Add column data to the Java object
                javaObject.put(headerValue, columnData);
            }
        }

        return javaObject;
    }

    public static void printAllValues(Map<String, Object> javaObject) {
        for (Map.Entry<String, Object> entry : javaObject.entrySet()) {
            String columnName = entry.getKey();
            Map<String, Object> columnData = (Map<String, Object>) entry.getValue();

            System.out.println(columnName + ":");
            for (Map.Entry<String, Object> dataEntry : columnData.entrySet()) {
                String rowIndex = dataEntry.getKey();
                Object cellValue = dataEntry.getValue();

                System.out.println("\tRow " + rowIndex + ": " + cellValue);
            }
        }
    }
}
