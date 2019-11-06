package tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

public class BasicNavigation {

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        String actual = driver.getTitle();
        String expected = "Google";
        Assert.assertEquals(actual,expected);
        System.out.println(driver.getTitle());
        driver.quit();
    }

    @Test
    public void test2() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.navigate().to("http://www.etsy.com");
        String actual = driver.getTitle();
        String expected = "Etsy - Shop for handmade, vintage, custom, and unique gifts for everyone";
        Assert.assertEquals(actual,expected);
        System.out.println(driver.getTitle());
        driver.quit();
    }


}
