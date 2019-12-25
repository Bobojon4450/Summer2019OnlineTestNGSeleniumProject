package tests.day12;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;
import java.util.concurrent.TimeUnit;

public class ActionsClassHoverPractice {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");BrowserUtils.maximaze(driver);
        driver.get("https://practice-cybertekschool.herokuapp.com/hovers");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearUp(){
//        driver.quit();
    }

    @Test(description = "Verify first image")
    public void test1(){
        Actions action = new Actions(driver);
        WebElement image1 = driver.findElement(By.cssSelector(".figure:nth-of-type(1)"));
        action.moveToElement(image1).perform();
        BrowserUtils.wait(3);
        // h5 is a grand child of .figure:nth-of-type(1) this element that contains image
        WebElement textOfImageElement = driver.findElement(By.cssSelector(".figure:nth-of-type(1) h5"));
        String expectedText = "name: user1";
        String actualText = textOfImageElement.getText();
        Assert.assertEquals(actualText, expectedText);
    }

    @Test(description = "Verify first image")
    public void test2(){
        Actions action = new Actions(driver);
        for (int i = 1; i <= 3; i++) {
            action.moveToElement(driver.findElement(By.cssSelector(".figure:nth-of-type(" + i + ")"))).perform();
            BrowserUtils.wait(3);
            String name = driver.findElement(By.cssSelector(".figure:nth-of-type(" + i + ") h5")).getText();
            Assert.assertEquals(name, "name: user" +i);
        }
    }



}

