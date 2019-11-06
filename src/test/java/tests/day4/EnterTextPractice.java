package tests.day4;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class EnterTextPractice {

    WebDriver driver = BrowserFactory.getDriver("chrome");

    @Test
    public void verifyTitle() {
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/forgot_password");
        WebElement inputBox = driver.findElement(By.xpath("//*[@name='email']"));
        WebElement passwButton = driver.findElement(By.id("form_submit"));
        WebElement emailTextBox = driver.findElement(By.xpath("//*[@name='email']"));
        emailTextBox.sendKeys("adam@gmail.com" + Keys.ENTER);
        BrowserUtils.wait(3);
        String expected = "Practice";
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
        BrowserUtils.wait(2);
        driver.quit();
    }

    @Test
    public void verifyURL() {
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/forgot_password");
        WebElement inputBox = driver.findElement(By.xpath("//*[@name='email']"));
        WebElement passwButton = driver.findElement(By.id("form_submit"));
        WebElement emailTextBox = driver.findElement(By.xpath("//*[@name='email']"));
        emailTextBox.sendKeys("adam@gmail.com" + Keys.ENTER);
        BrowserUtils.wait(3);
        String expectedURL = "http://practice.cybertekschool.com/email_sent";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expectedURL);
        BrowserUtils.wait(2);
        driver.quit();
    }

    @Test
    public void verifyConfMessage() {
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/forgot_password");
        WebElement inputBox = driver.findElement(By.xpath("//*[@name='email']"));
        WebElement passwButton = driver.findElement(By.id("form_submit"));
        WebElement emailTextBox = driver.findElement(By.xpath("//*[@name='email']"));
        emailTextBox.sendKeys("adam@gmail.com" + Keys.ENTER);
        BrowserUtils.wait(3);
        String expectedURL = "http://practice.cybertekschool.com/email_sent";
        WebElement confirmationMessage = driver.findElement(By.name("confirmation_message"));
        Assert.assertTrue(confirmationMessage.isDisplayed());
        BrowserUtils.wait(2);
        driver.quit();
    }

    @Test
    public void verifyAttribute() {
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/forgot_password");
        WebElement inputBox = driver.findElement(By.xpath("//*[@name='email']"));
        WebElement passwButton = driver.findElement(By.id("form_submit"));
        String input = driver.findElement(By.xpath("//*[@name='email']")).getAttribute("name");
        Assert.assertEquals(input, "email");
        System.out.println(input);
        BrowserUtils.wait(3);
        driver.quit();
    }

}
