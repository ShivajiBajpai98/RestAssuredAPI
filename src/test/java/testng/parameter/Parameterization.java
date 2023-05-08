package testng.parameter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Parameterization
{
    WebDriver driver;
    @BeforeClass
    @Parameters({"browser"})
    void setUP(String browser)
    {
        if (browser.equalsIgnoreCase("chrome"))
        {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver(options);
            driver.get("https://www.atmecs.com/");


          /*  WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();*/
        }
        else if(browser.equalsIgnoreCase("firefox"))
        {

            FirefoxOptions options = new FirefoxOptions();

            options.addArguments("--remote-allow-origins=*");
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver(options);
            driver.get("https://www.atmecs.com/");



        }


    }

    @Test
    void logoTest()
    {

        WebElement logo= driver.findElement(By.xpath("//img[@class='attachment-full size-full wp-image-12084 has-transparency lazyloaded']"));
        Assert.assertTrue(logo.isDisplayed(),"logo is not on the page");


    }

    @Test
    void homePageTitle()
    {  String title= driver.getTitle();
        // System.out.println(title);
        Assert.assertEquals(title,"ATMECS - :: A True R&D Services Company","title is not matched");
    }

    @AfterClass
    void tearDown()
    {
        driver.quit();
    }
}
