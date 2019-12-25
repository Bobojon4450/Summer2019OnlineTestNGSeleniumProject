package tests.day7;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.BrowserFactory;

public class TestNGPractice {

    @Test
    public void test(){
        Assert.assertEquals("apple", "apple", "Should be pass");
    }

    @Test(description = "Verifying title of the practice page")
    public void testNg(){
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        String expectedTitle = "Practice";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
        driver.quit();
    }


    @Test(description = "Verify the heading is displayed")
    public void verifyHeadingIsDisplayed(){
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        WebElement heading = driver.findElement(By.xpath("//span[text()='Test Automation Practice']"));
        Assert.assertTrue(heading.isDisplayed());
        driver.quit();
    }


}
