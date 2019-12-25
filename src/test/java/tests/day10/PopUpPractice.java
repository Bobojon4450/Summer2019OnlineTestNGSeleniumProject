package tests.day10;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class PopUpPractice {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(description = "Click on ok in pop-up message")
    public void test1(){
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        BrowserUtils.wait(2);
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());//prints the message of the pop-up
        alert.accept();//click ok.
        BrowserUtils.wait(2);
        System.out.println(driver.findElement(By.cssSelector("#result")).getText());
    }

    @Test(description = "Click on cancel in pop-up message")
    public void test2(){
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.xpath("//button[2]")).click();
        BrowserUtils.wait(2);
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());//prints the message of the pop-up
        alert.dismiss();//click cancel.
        BrowserUtils.wait(2);
        System.out.println(driver.findElement(By.cssSelector("#result")).getText());
    }

    @Test(description = "Click on button 3, enter some text and then click Ok")
    public void test3(){
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        BrowserUtils.wait(2);
        driver.switchTo().alert().sendKeys("\"Hello everyone!\"");
        driver.switchTo().alert().accept();
        BrowserUtils.wait(2);
        System.out.println(driver.findElement(By.cssSelector("#result")).getText());
    }
}
