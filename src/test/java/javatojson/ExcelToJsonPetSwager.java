package javatojson;

import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

class ExcelToJsonPetSwager {
    public static void main(String[] args) {
        convertExcelToJson();
    }

    public static void convertExcelToJson() {
        String filePath = "src/test/java/javatojson/payload.xlsx";
        String sheetName = "Sheet1";
        JSONArray jsonArray = new JSONArray();

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                JSONObject jsonObject = convertRowToJson(row, headerRow);
                jsonArray.put(jsonObject);
            }

            // Write JSON array to file
            try (FileWriter fileWriter = new FileWriter("output.json")) {
                fileWriter.write(jsonArray.toString(4));
            }

            System.out.println("JSON file generated successfully.");

            // Print JSON data to console
            System.out.println("JSON data:");
            System.out.println(jsonArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject convertRowToJson(Row row, Row headerRow) {
        Map<String, Object> rowData = new LinkedHashMap<>();

        for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
            Cell cell = row.getCell(cellIndex);
            Cell headerCell = headerRow.getCell(cellIndex);
            String key = headerCell.getStringCellValue().trim();
            Object value = getCellValue(cell);
            rowData.put(key, value);
        }

        return createJsonFromMap(rowData);
    }

    public static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

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

    public static JSONObject createJsonFromMap(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                JSONObject nestedObject = createJsonFromMap((Map<String, Object>) value);
                jsonObject.put(key, nestedObject);
            } else if (value instanceof JSONArray) {
                JSONArray nestedArray = (JSONArray) value;
                jsonObject.put(key, nestedArray);
            } else if (value instanceof String && key.equals("")) {
                // Skip empty keys
                continue;
            } else {
                jsonObject.put(key, value);
            }
        }

        return jsonObject;
    }
}
