package tests.day12;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.concurrent.TimeUnit;

public class FramesPractice {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");BrowserUtils.maximaze(driver);
        driver.get("https://practice-cybertekschool.herokuapp.com/frames");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearUp(){
//        driver.quit();
    }

    @Test(description = "iFrame")
    public void test1(){
        String expectedText = "Your content goes here.";
        driver.findElement(By.linkText("iFrame")).click();
        driver.switchTo().frame("mce_0_ifr");
        WebElement inputBox = driver.findElement(By.id("tinymce"));
        String actualText = inputBox.getText();
        Assert.assertEquals(expectedText,actualText);
        BrowserUtils.wait(1);
        inputBox.clear();
        BrowserUtils.wait(4);
        inputBox.sendKeys("Java is fun!");
        //to exit from the frame
        driver.switchTo().defaultContent();
    }

    @Test(description = "Nested Frames example")
    public void test2(){
        //it's not switch to frame
        //it's a navigation action
        driver.findElement(By.linkText("Nested Frames")).click();
        //we switch to frame based on webelement
        driver.switchTo().frame(driver.findElement(By.cssSelector("[name='frame-bottom']")));
        //the reason why we are switching here
        //is because content that is inside a frame is not visible for selenium
        //it's like when you are on the first floor
        //trying to find what is on the second floor
        WebElement content = driver.findElement(By.tagName("body"));
        System.out.println(content.getText());
        driver.switchTo().defaultContent();//to exit from all frames, got to first floor
        driver.switchTo().frame("frame-top"); // second floor
        driver.switchTo().frame("frame-left"); // third floor
        System.out.println(driver.findElement(By.tagName("body")).getText());//print text of body
    }
}
