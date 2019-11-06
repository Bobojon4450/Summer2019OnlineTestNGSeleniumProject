package tests.day5;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestForID_Locator {

    /*  This web driver is an Interface */
    WebDriver driver = BrowserFactory.getDriver("chrome");

    @Test
    public void test_ID(){
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        driver.findElement(By.cssSelector("#disappearing_button")).click();
        BrowserUtils.wait(2);
        WebElement result = driver.findElement(By.cssSelector("#result"));
        System.out.println(result.getText());
        Assert.assertTrue(result.isDisplayed());
        BrowserUtils.wait(2);
        driver.quit();
    }

    @Test
    public void test_Name() {/*Here we locating an elements by using By.name locator    */
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/sign_up");
        driver.findElement(By.name("full_name")).sendKeys("Test User");
        BrowserUtils.wait(2);
        driver.findElement(By.name("email")).sendKeys("adam@gmail.com");
        BrowserUtils.wait(2);
        driver.findElement(By.name("wooden_spoon")).click();
        BrowserUtils.wait(3);
        WebElement result = driver.findElement(By.name("signup_message"));
        System.out.println(result.getText());
        Assert.assertTrue(result.isDisplayed());
        BrowserUtils.wait(3);
        driver.findElement(By.cssSelector("#wooden_spoon")).click();
        WebElement homePage = driver.findElement(By.cssSelector(".h1y"));
        System.out.println(homePage.getText());
        Assert.assertTrue(homePage.isDisplayed());
        BrowserUtils.wait(3);
        driver.quit();
    }

    @Test
    public void test_TagName() {
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com//sign_up");
        driver.findElement(By.name("full_name")).sendKeys("Test User");
        BrowserUtils.wait(2);
        driver.findElement(By.name("email")).sendKeys("adam@gmail.com");
        BrowserUtils.wait(2);
        driver.findElement(By.name("wooden_spoon")).click();
        BrowserUtils.wait(3);
        WebElement result = driver.findElement(By.name("signup_message"));
        System.out.println(result.getText());
        Assert.assertTrue(result.isDisplayed());
        BrowserUtils.wait(2);
        WebElement homePage = driver.findElement(By.tagName("h3"));
        System.out.println(homePage.getText());
        BrowserUtils.wait(3);
        driver.quit();
    }

    @Test
    public void test_linkText() {
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/");
        driver.findElement(By.linkText("Autocomplete")).click();
        BrowserUtils.wait(2);
        driver.quit();
    }

}
