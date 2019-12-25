package tests.vytrack;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.concurrent.TimeUnit;

public class CalendarEventsTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        wait = new WebDriverWait(driver,35);
         /* waits for the entire dom's elements are loaded within given time-frame,
           continues if elements are loaded quicker then given time    */
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        BrowserUtils.maximaze(driver);driver.get("https://qa1.vytrack.com/");
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);
        //String xpath = "//a[@class='unclickable' and @href = '#' ]/span[@class='title title-level-1' and contains(text(),'Activities')]";
        //WebElement activitiesElement = driver.findElement(By.xpath(xpath));

        WebElement spinner = null;
        if(driver.findElements(By.cssSelector("div[class='loader-mask shown']")).size() > 0){
            spinner = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(spinner));
        }

        BrowserUtils.wait(2);
        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        activitiesElement.click();

        WebElement calendarEventsElement = driver.findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calendarEventsElement));
        wait.until(ExpectedConditions.elementToBeClickable(calendarEventsElement));
        calendarEventsElement.click();

        wait.until(ExpectedConditions.invisibilityOf(spinner));
    }

    @AfterMethod
    public void tearUp(){
        driver.quit();
    }

    @Test(description = "Verify page subtitle")
    public void test1(){
        String expectedSubtitle = "All Calendar Events";
        String atualSubtitle = driver.findElement(By.className("oro-subtitle")).getText();
        Assert.assertEquals(atualSubtitle,expectedSubtitle, "Subtitle is wrong");
    }

    @Test(description = "Verify that 'Create Calendar' button is displayed")
    public void test2(){
        Assert.assertTrue(driver.findElement(By.cssSelector(".btn.main-group")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("[title='Create Calendar event']")).isDisplayed());
    }

}
