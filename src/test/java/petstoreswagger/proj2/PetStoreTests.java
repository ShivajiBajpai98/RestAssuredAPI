package petstoreswagger.proj2;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class PetStoreTests {

    private final String baseUrl = "https://petstore.swagger.io/v2";
    private final String JSON_FILE_PATH = "src/test/resources/payload.json";

    @Test
    public void runPetStoreTests() {
        testAddPet();
        testUploadFile();
        testFindPetById();
        testFindPetsByStatus();
    }

    private void testAddPet() {
        String endpoint = "/pet";
        String requestBody = readPayloadFromFile(JSON_FILE_PATH);

        int statusCode = sendHttpRequest("POST", endpoint, requestBody);
        Assert.assertEquals(statusCode, 200, "The status code is not 200");
    }

    private void testUploadFile() {
        String uploadEndpoint = "/pet/{petId}/uploadImage";
        long petId = 123454321;
        String filePath = "src/test/resources/dog.jpg";

        String endpoint = uploadEndpoint.replace("{petId}", String.valueOf(petId));
        String requestBody = readFileContent(filePath);

        int statusCode = sendHttpRequestWithFileUpload(endpoint, filePath);
        Assert.assertEquals(statusCode, 200, "The status code is not 200");
    }

    private void testFindPetById() {
        long petId = 123454321;
        String endpoint = "/pet/{petId}".replace("{petId}", String.valueOf(petId));

        sendHttpRequest("GET", endpoint, null);
    }

    private void testFindPetsByStatus() {
        String status = "available";
        String endpoint = "/pet/findByStatus?status=" + status;

        sendHttpRequest("GET", endpoint, null);
    }

    private int sendHttpRequest(String method, String endpoint, String requestBody) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);

            if (requestBody != null) {
                connection.setRequestProperty("Content-Type", "application/json");
                try (OutputStream outputStream = connection.getOutputStream()) {
                    outputStream.write(requestBody.getBytes());
                }
            }

            int statusCode = connection.getResponseCode();
            System.out.println("Status Code: " + statusCode);

            // ...

            connection.disconnect();
            return statusCode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int sendHttpRequestWithFileUpload(String endpoint, String filePath) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String boundary = "*****";
            String lineEnd = "\r\n";
            String twoHyphens = "--";

            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + filePath + "\"" + lineEnd);
                outputStream.writeBytes("Content-Type: application/octet-stream" + lineEnd);
                outputStream.writeBytes(lineEnd);

                File file = new File(filePath);
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                fileInputStream.close();

                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                outputStream.flush();
            }

            int statusCode = connection.getResponseCode();
            System.out.println("Status Code: " + statusCode);

            // ...

            connection.disconnect();
            return statusCode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private String readFileContent(String filePath) {
        try {
            File file = new File(filePath);
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readPayloadFromFile(String filePath) {
        StringBuilder payload = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                payload.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payload.toString();
    }
}
