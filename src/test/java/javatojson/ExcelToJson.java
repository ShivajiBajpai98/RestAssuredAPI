package javatojson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelToJson {
    public static void main(String[] args) {
        // Convert Excel to JSON and print the result

        JSONArray jsonArray = convertExcelToJson();
        System.out.println(jsonArray);

    }

    public static JSONArray convertExcelToJson() {
        // Path to the Excel file
        String filePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "petswagger.xlsx";

        // Name of the sheet to read
        String sheetName = "Sheet1";

        // Create a JSON array to hold the converted data
        JSONArray jsonArray = new JSONArray();

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            // Create a Workbook instance from the Excel file
            Workbook workbook = WorkbookFactory.create(inputStream);

            // Get the desired sheet from the workbook
            Sheet sheet = workbook.getSheet(sheetName);

            // Get the header row
            Row headerRow = sheet.getRow(0);

            // Iterate over each row starting from the second row
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                // Convert the row to a Map representing key-value pairs
                Map<String, Object> rowData = convertRowToMap(row, headerRow);

                // Add the Map to the JSON array
                jsonArray.put(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static Map<String, Object> convertRowToMap(Row row, Row headerRow) {
        // Create a LinkedHashMap to hold the cell values for the row
        Map<String, Object> rowData = new LinkedHashMap<>();

        // Iterate over each cell in the row
        for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
            Cell cell = row.getCell(cellIndex);
            Cell headerCell = headerRow.getCell(cellIndex);

            // Get the key from the header cell
            String key = headerCell.getStringCellValue().trim();
            DataFormatter df=new DataFormatter();
            // Get the value from the current cell
            Object value = df.formatCellValue(cell);

            // Add the key-value pair to the Map
            rowData.put(key, value);
        }

        return rowData;
    }

    public static Object getCellValue(Cell cell) {
        // Retrieve the cell value based on its type
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return "Unknown cell type";
        }
    }
}
