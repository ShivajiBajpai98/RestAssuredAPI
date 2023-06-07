package javatojson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

 class JsonToExcelConverter2 {
    public static void main(String[] args) {
        String jsonFilePath = "src/test/java/javatojson/payload.json";
        String excelFilePath = "output3.xlsx";

        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and convert to Java object (Map)
            Map<String, Object> jsonData = objectMapper.readValue(new File(jsonFilePath), Map.class);

            // Print all values
            System.out.println("Printing Java Object:");
            printAllValues(jsonData);

            // Convert Java object to Excel
            Map<String, Object> excelData = convertJsonToExcel(jsonData);

            // Print Excel data
            System.out.println("Printing Excel Data:");
            printExcelData(excelData);

            // Save Excel data to file
            createExcelFile(excelData, excelFilePath);

            System.out.println("Excel file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> convertJsonToExcel(Map<String, Object> jsonData) {
        Map<String, Object> excelData = new LinkedHashMap<>();

        // Extract header row from JSON object keys
        List<String> headerRow = new ArrayList<>(jsonData.keySet());

        // Create row data
        Map<String, Object> rowData = createRowData(jsonData, headerRow);

        excelData.put("Sheet1", rowData);

        return excelData;
    }

    public static Map<String, Object> createRowData(Map<String, Object> jsonData, List<String> headerRow) {
        Map<String, Object> rowData = new LinkedHashMap<>();

        for (String key : headerRow) {
            Object value = jsonData.get(key);
            rowData.put(key, value);
        }

        return rowData;
    }

    public static void printAllValues(Map<String, Object> jsonData) {
        for (Map.Entry<String, Object> entry : jsonData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + ": " + value);
        }
    }

    public static void printExcelData(Map<String, Object> excelData) {
        for (Map.Entry<String, Object> entry : excelData.entrySet()) {
            String sheetName = entry.getKey();
            Map<String, Object> rowData = (Map<String, Object>) entry.getValue();
            for (Map.Entry<String, Object> dataEntry : rowData.entrySet()) {
                String key = dataEntry.getKey();
                Object value = dataEntry.getValue();
                System.out.printf("%s: %s\t", key, value);
            }
            System.out.println();
        }
    }

    public static void createExcelFile(Map<String, Object> excelData, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        Map<String, Object> rowData = (Map<String, Object>) excelData.get("Sheet1");

        // Create header row
        Row headerRow = sheet.createRow(0);
        int cellIndex = 0;
        for (String key : rowData.keySet()) {
            Cell cell = headerRow.createCell(cellIndex++);
            cell.setCellValue(key);
        }

        // Create data row
        Row dataRow = sheet.createRow(1);
        cellIndex = 0;
        for (Object value : rowData.values()) {
            Cell cell = dataRow.createCell(cellIndex++);
            if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Number) {
                cell.setCellValue(((Number) value).doubleValue());
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value);
            }
        }

        // Write workbook to file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
