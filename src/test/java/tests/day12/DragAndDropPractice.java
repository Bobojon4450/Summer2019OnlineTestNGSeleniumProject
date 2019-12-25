package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.concurrent.TimeUnit;

public class DragAndDropPractice {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");BrowserUtils.maximaze(driver);
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearUp(){
//        driver.quit();
    }

    @Test(description = "drag and drop example")
    public void test1(){
        WebElement cookies = driver.findElement(By.cssSelector(".optanon-allow-all.accept-cookies-button"));
        cookies.click();
        Actions actions = new Actions(driver);
        WebElement moon = driver.findElement(By.id("draggable"));
        WebElement earth = driver.findElement(By.id("droptarget"));
        BrowserUtils.wait(2);
        /* actions.dragAndDrop(moon, earth).pause(200).build().perform(); */
        actions.dragAndDrop(moon, earth).perform();
        BrowserUtils.wait(2);
    }
}
