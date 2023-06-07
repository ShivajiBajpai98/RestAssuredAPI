package javatojson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ExcelToRestAssured {
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

            // Create a JSON array to hold the rows
            JSONArray jsonArray = new JSONArray();

            // Get the first row (header row) from the sheet
            Row headerRow = sheet.getRow(0);

            // Iterate over each row starting from the second row
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                // Create a JSON object to hold the cell values for each row
                JSONObject jsonObject = new JSONObject();

                // Iterate over each cell in the row
                for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    Cell headerCell = headerRow.getCell(cellIndex);

                    String key = headerCell.getStringCellValue();
                    String value;

                    // Retrieve the cell value based on its type
                    switch (cell.getCellType()) {
                        case STRING:
                            value = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            value = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            value = String.valueOf(cell.getBooleanCellValue());
                            break;
                        default:
                            value = "Unknown cell type";
                            break;
                    }

                    // Add the key-value pair to the JSON object
                    if (key != null && value != null) {
                        jsonObject.put(key, value);
                    }
                }

                // Add the JSON object to the array
                jsonArray.put(jsonObject);
            }

            // Print the JSON array
            System.out.println(jsonArray.toString(4));

            // Pass the data to the REST Assured API and get the response
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(jsonArray.toString())
                    .post("https://reqres.in/api/endpoint");

            // Print the response status code
            int statusCode = response.getStatusCode();
            System.out.println("Response Status Code: " + statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
