package javatojson;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.*;

public class ExcelJsonConverter {
    public static void main(String[] args) {
        // Convert Excel to JSON
        JSONArray jsonArray = convertExcelToJson("src/test/resources/petswagger.xlsx");
        System.out.println("JSON data:");
        System.out.println(jsonArray.toString());

        // Write JSON to file
        writeJsonToFile(jsonArray, "output.json");
        System.out.println("JSON file generated successfully.");

        // Convert JSON to Excel
        List<Map<String, Object>> excelData = convertJsonToExcel("output2.json");
        System.out.println("Excel data:");
        printExcelData(excelData);

        // Generate Excel file
        createExcelFile(excelData, "src/test/resources/output.xlsx");
        System.out.println("Excel file generated successfully.");
    }

    public static JSONArray convertExcelToJson(String filePath) {
        JSONArray jsonArray = new JSONArray();

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                JSONObject jsonObject = convertRowToJson(row, headerRow);
                jsonArray.put(jsonObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static JSONObject convertRowToJson(Row row, Row headerRow) {
        Map<String, Object> rowData = new LinkedHashMap<>();

        for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
            Cell cell = row.getCell(cellIndex);
            Cell headerCell = headerRow.getCell(cellIndex);
            String key = headerCell.getStringCellValue().trim();
            Object value = getCellValue(cell);
            rowData.put(key, value);
        }

        return new JSONObject(rowData);
    }

    public static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }

    public static void writeJsonToFile(JSONArray jsonArray, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> convertJsonToExcel(String filePath) {
        List<Map<String, Object>> excelData = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filePath)) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(fileReader));

            if (jsonArray.length() > 0) {
                JSONObject firstObject = jsonArray.getJSONObject(0);

                // Extract header row from JSON object keys
                List<String> headerRow = new ArrayList<>(firstObject.keySet());

                // Convert JSON objects to excelData rows
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Map<String, Object> rowData = createRowData(jsonObject, headerRow);
                    excelData.add(rowData);
                }
            }
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

    public static void printExcelData(List<Map<String, Object>> excelData) {
        for (Map<String, Object> rowData : excelData) {
            for (Map.Entry<String, Object> entry : rowData.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.printf("%s: %s\t", key, value);
            }
            System.out.println();
        }
    }

    public static void createExcelFile(List<Map<String, Object>> excelData, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create header row
        Row headerRow = sheet.createRow(0);
        int cellIndex = 0;
        for (String key : excelData.get(0).keySet()) {
            Cell cell = headerRow.createCell(cellIndex++);
            cell.setCellValue(key);
        }

        // Create data rows
        int rowIndex = 1;
        for (Map<String, Object> rowData : excelData) {
            Row dataRow = sheet.createRow(rowIndex++);
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
        }

        // Write workbook to file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
