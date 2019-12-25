package tests.day10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.Set;

public class WindowSwitching {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        BrowserUtils.maximaze(driver);
        driver.get("http://practice.cybertekschool.com/");
    }

    @Test(description = "Verify the title is still practice")
    public void test1() {
        driver.findElement(By.linkText("New tab")).click();
        BrowserUtils.wait(3);
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Practice", "Wrong title!");
    }

    /*  windowhandle is a unique id of an each tab    */
    @Test
    public void test2() {
        driver.findElement(By.linkText("New tab")).click();
        String oldWindow = driver.getWindowHandle();// == get tabId;
        BrowserUtils.wait(3);
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(oldWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
        System.out.println(driver.getTitle()); //Fresh tab
        Assert.assertEquals(driver.getTitle(), "Fresh tab");

        /*comeback to original page, based on page title, we can determine where to stop. */
        String pageTitle = "Practice";
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(pageTitle)){
                //stop jumping
                break;
            }
        }
        System.out.println(driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
