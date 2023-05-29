package proj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // File path for the configuration properties file
    private static final String filePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "configproj.properties";

    private Properties properties;

    public ConfigReader() {
        // Load the properties from the file
        properties = loadProperties(filePath);
    }

    /**
     * Retrieves the value of a property based on the provided key.
     *
     * @param key the key of the property
     * @return the value of the property
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Loads the properties from the specified file path.
     *
     * @param filePath the file path of the properties file
     * @return the loaded properties
     */
    private Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
