package restassuredwithproperties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Properties;

public class Propertie
{
    Properties properties;
    @BeforeClass
    void property() {
        try {
             properties = new Properties();
            FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(inputStream);

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}