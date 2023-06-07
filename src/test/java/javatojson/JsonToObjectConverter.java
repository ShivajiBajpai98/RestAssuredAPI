package javatojson;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonToObjectConverter {
    public static void main(String[] args) {
        String jsonFilePath = "src/test/java/javatojson/payload.json";

        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and convert to Java object (Map)
            Map<String, Object> jsonData = objectMapper.readValue(new File(jsonFilePath), Map.class);

            // Print all values
            printAllValues(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printAllValues(Map<String, Object> jsonData) {
        for (Map.Entry<String, Object> entry : jsonData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + ": " + value);
        }
    }
}
