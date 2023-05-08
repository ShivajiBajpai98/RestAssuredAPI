package testng.paralleltesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ParallelTest1
{
    WebDriver driver;
    @Test
    void logoTest() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver(options);
        driver.get("https://www.atmecs.com/");


        WebElement logo= driver.findElement(By.xpath("//img[@class='attachment-full size-full wp-image-12084 has-transparency lazyloaded']"));
        Assert.assertTrue(logo.isDisplayed(),"logo is not on the page");





    }

    @Test
    void homePageTitle()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver(options);
        driver.get("https://www.atmecs.com/");
        String title= driver.getTitle();
       // System.out.println(title);
        Assert.assertEquals(title,"ATMECS - :: A True R&D Services Company","title is not matched");
    }
    @AfterMethod
    void tearDown()
    {

        driver.quit();
    }
}
