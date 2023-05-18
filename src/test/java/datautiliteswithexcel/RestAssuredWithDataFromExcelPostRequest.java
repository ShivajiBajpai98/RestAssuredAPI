package datautilites;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class RestAssuredWithDataFromExcelPostRequest {

    @DataProvider(name = "testData")
    public Object[][] testDataFromExcel() {
        String filePath = "src/test/resources/data.xlsx";
        String sheetName = "Sheet1";

        Object[][] data = null;
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();

            data = new Object[rowCount][columnCount];
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = getCellValueAsString(cell);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String getCellValueAsString(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            CellType cellType = cell.getCellType();
            if (cellType == CellType.STRING) {
                cellValue = cell.getStringCellValue();
            } else if (cellType == CellType.NUMERIC) {
                cellValue = String.valueOf(cell.getNumericCellValue());
            } else if (cellType == CellType.BOOLEAN) {
                cellValue = String.valueOf(cell.getBooleanCellValue());
            }
        }
        return cellValue;
    }

    @Test(dataProvider = "testData")
    public void testRestAssured(String name, String job) {
        RestAssured.baseURI = "https://reqres.in/";
        String requestBody = String.format("{\"name\":\"%s\",\"job\":\"%s\"}", name, job);
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/api/users");

        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
    }
}
