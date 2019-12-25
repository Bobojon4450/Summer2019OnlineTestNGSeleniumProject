package tests.day10;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class FileUploading {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/");
    }

    @AfterMethod
    public void tearDown() {
//        driver.quit();
    }

    @Test(description = "Verify that file was uploaded")
    public void test1(){
        driver.findElement(By.linkText("File Upload")).click();
        driver.findElement(By.id("file-upload")).sendKeys("C:\\Users\\Vaha\\Desktop\\classNotes.txt");
        BrowserUtils.wait(1);
        driver.findElement(By.id("file-submit")).click();
        BrowserUtils.wait(4);
        String expectedFileName = "classNotes.txt";
        String actuallFileName = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(actuallFileName,expectedFileName);
        BrowserUtils.wait(1);
        WebElement message = driver.findElement(By.cssSelector("#uploaded-files"));
        Assert.assertTrue(message.isDisplayed());
    }

}


