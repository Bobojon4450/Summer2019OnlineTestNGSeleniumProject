package tests.SandBox;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.BrowserUtils;
import utils.Driver;

public class QuickTest {

    @Test
    public void test(){
        Driver.getDriver().get("http://34.220.250.213/");
        Driver.getDriver().findElement(By.linkText("Sign in")).click();
        Driver.getDriver().findElement(By.id("login")).sendKeys("eventscrmmanager64@info.com");
        Driver.getDriver().findElement(By.id("password")).sendKeys("eventscrmmanager", Keys.ENTER);
        BrowserUtils.wait(2);
        Driver.getDriver().findElement(By.linkText("CRM")).click();
        BrowserUtils.wait(2);
        WebElement filter = Driver.getDriver().findElement(By.className("selected"));
        Assert.assertEquals("selected",filter.getAttribute("class"));
        System.out.println(filter.getAttribute("class"));

    }

    @Test
    public void test2(){
        Driver.getDriver().get("http://34.220.250.213/");
        Driver.getDriver().findElement(By.linkText("Sign in")).click();
        Driver.getDriver().findElement(By.id("login")).sendKeys("eventscrmmanager64@info.com");
        Driver.getDriver().findElement(By.id("password")).sendKeys("eventscrmmanager", Keys.ENTER);
        BrowserUtils.wait(2);
        Driver.getDriver().findElement(By.linkText("Calendar")).click();
        BrowserUtils.wait(2);
        WebElement week = Driver.getDriver().findElement(By.cssSelector("button[class$='default active']"));
        Assert.assertTrue(week.getAttribute("class").endsWith("default active"));
    }


}
