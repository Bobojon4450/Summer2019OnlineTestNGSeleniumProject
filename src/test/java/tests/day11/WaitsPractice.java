package tests.day11;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WaitsPractice {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearUp(){
        driver.quit();
    }

    @Test(description = "testing ImplicitWait feature")
    public void test1(){
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.partialLinkText("Example 2")).click();
        driver.findElement(By.tagName("button")).click();
        WebElement finishElement = driver.findElement(By.id("finish"));
        System.out.println(finishElement.getText());
    }

    @Test
    public void test2(){
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.partialLinkText("Example 1")).click();
        driver.findElement(By.tagName("button")).click();
        WebElement userNameInputBox = driver.findElement(By.id("username"));
        WebDriverWait wait = new WebDriverWait(driver,35);
        wait.until(ExpectedConditions.visibilityOf(userNameInputBox));
        userNameInputBox.sendKeys("tomsmith");
        WebElement passwordInputBox = driver.findElement(By.id("pwd"));
        wait.until(ExpectedConditions.visibilityOf(passwordInputBox));
        passwordInputBox.sendKeys("SuperSecretPassword");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']")));
        driver.findElement(By.cssSelector("[type='submit']")).click();
        WebElement successMsg = driver.findElement(By.cssSelector(".subheader"));
        String message = "Welcome to the Secure Area. When you are done click logout below.";
        Assert.assertEquals(message, successMsg.getText());
        Assert.assertTrue(successMsg.isDisplayed());
        driver.findElement(By.cssSelector(".button")).click();
        WebElement logInMsg = driver.findElement(By.xpath("//h2[text()='Login Page']"));
        Assert.assertEquals( "Login Page",logInMsg.getText());
        Assert.assertTrue(logInMsg.isDisplayed());
    }

    @Test
    public void test3() {
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.partialLinkText("Example 5")).click();
        WebElement overlayScreen = driver.findElement(By.cssSelector("[class='fa fa-cog fa-spin']"));
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(ExpectedConditions.invisibilityOf(overlayScreen));
        WebElement userNameInputBox = driver.findElement(By.id("username"));
        wait.until(ExpectedConditions.visibilityOf(userNameInputBox));
        userNameInputBox.sendKeys("tomsmith");
        WebElement passwordInputBox = driver.findElement(By.id("pwd"));
        wait.until(ExpectedConditions.visibilityOf(passwordInputBox));
        passwordInputBox.sendKeys("SuperSecretPassword");
        //this is a webelement that represents submit button
        WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));
        //wait, within 10 seconds, until that button is available for click
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        submit.click();
        WebElement message = driver.findElement(By.tagName("h4"));
        wait.until(ExpectedConditions.visibilityOf(message));
        String expectedMessage = "Welcome to the Secure Area. When you are done click logout below.";
        String actualMessage = message.getText();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test(description = "Fluent wait")
    public void test4(){
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.partialLinkText("Example 2")).click();
        driver.findElement(By.tagName("button")).click();
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(ElementNotVisibleException.class);
        WebElement message = (WebElement) wait.until(new Function <WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("finish"));
            }
        });
    }


}
