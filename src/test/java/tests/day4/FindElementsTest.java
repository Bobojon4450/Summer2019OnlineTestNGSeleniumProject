package tests.day4;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class FindElementsTest {
    WebDriver driver  = BrowserFactory.getDriver("chrome");

    @Test
    public void test(){
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/forgot_password");
        WebElement inputBox = driver.findElement(By.xpath("//*[@name='email']"));
        WebElement passwButton = driver.findElement(By.id("form_submit"));
        passwButton.click();
        String expected = "Practice";
        String actual= driver.getTitle();
        Assert.assertEquals(actual,expected);
        BrowserUtils.wait(2);
        driver.quit();
    }

}
