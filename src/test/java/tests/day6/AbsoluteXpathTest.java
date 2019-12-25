package tests.day6;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class AbsoluteXpathTest {


    WebDriver driver = BrowserFactory.getDriver("chrome");


//    WebElement incorrectMessage = driver.findElement(By.cssSelector(".errortext"));


    @Test
    public void absoluteXpathTest() {
        BrowserUtils.maximaze(driver);
        driver.get("https://login1.nextbasecrm.com/");
        driver.findElement(By.cssSelector(".login-btn")).click();
        BrowserUtils.wait(1);
        WebElement incorrectMessage = driver.findElement(By.cssSelector(".errortext"));
        Assert.assertTrue(incorrectMessage.isDisplayed());
    }

    @Test
    public void absoluteXpathTest_Login() {
        BrowserUtils.maximaze(driver);
        driver.get("https://login1.nextbasecrm.com/");
        WebElement loginTextBox = driver.findElement(By.cssSelector("input.login-inp[type='text']"));
        WebElement passwordTextBox = driver.findElement(By.cssSelector("input.login-inp[type='password']"));
        WebElement loginButton = driver.findElement(By.cssSelector(".login-btn"));
        loginTextBox.sendKeys("hr25@cybertekschool.com");
        passwordTextBox.sendKeys("UserUser");
        loginButton.click();
        String actualText = driver.findElement(By.cssSelector("#user-name")).getText();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleIs(driver.getTitle()));
        Assert.assertEquals(actualText, "hr25@cybertekschool.com");
        Assert.assertTrue(driver.findElement(By.cssSelector("#user-name")).isDisplayed());
        driver.quit();
    }


}
