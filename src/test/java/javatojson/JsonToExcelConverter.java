package javatojson;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JsonToExcelConverter {
    public static void main(String[] args) {
        String jsonFilePath = "src/test/java/petstoreswagger/finalproject/payload.json";
        String excelFilePath = "output.xlsx";

        // Convert JSON to Excel
        Map<String, Object> excelData = convertJsonToExcel(jsonFilePath);

        // Print Excel data
        printExcelData(excelData);

        // Save Excel data to file
        createExcelFile(excelData, excelFilePath);

        System.out.println("Excel file generated successfully.");
    }

    public static Map<String, Object> convertJsonToExcel(String filePath) {
        Map<String, Object> excelData = new LinkedHashMap<>();

        try {
            String jsonString = readFileAsString(filePath);
            JSONObject jsonObject = new JSONObject(jsonString);

            // Extract header row from JSON object keys
            List<String> headerRow = new ArrayList<>(jsonObject.keySet());

            // Create row data
            Map<String, Object> rowData = createRowData(jsonObject, headerRow);

            excelData.put("Sheet1", rowData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelData;
    }

    public static Map<String, Object> createRowData(JSONObject jsonObject, List<String> headerRow) {
        Map<String, Object> rowData = new LinkedHashMap<>();

        for (String key : headerRow) {
            Object value = jsonObject.opt(key);
            rowData.put(key, value);
        }

        return rowData;
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
            } else if (value instanceof Date) {
                cell.setCellValue((Date) value);
            }
        }

        // Write workbook to file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileAsString(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
        }
        return contentBuilder.toString();
    }
}
