package testng.assertions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assertion {
    WebDriver driver;

    @BeforeClass
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.get("https://www.atmecs.com/");
    }

    @Test
    void logoTest() {
        WebElement logo = driver.findElement(By.xpath("//img[@class='attachment-full size-full wp-image-12084 has-transparency lazyloaded']"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed on the page");
    }

    @Test
    void homePageTitle() {
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "ATMECS - :: A True R&D Services Company", "Title is not matched");
    }

    @AfterClass
    void tearDown() {
        driver.quit();
    }
}
