package tests.day7;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;

public class CssSelectorPractice {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void test(){
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        List<WebElement> buttons = driver.findElements(By.cssSelector(".btn.btn-primary"));
        for (WebElement element: buttons) {
            element.click();
            BrowserUtils.wait(1);
        }
        // find element with a tag name h3, that has a parent element, with class name container
        WebElement header = driver.findElement(By.cssSelector(".container > h3"));
        System.out.println(header.getText());
        WebElement p = driver.findElement(By.cssSelector("[class='container'] > p"));
        System.out.println(p.getText());
        driver.quit();
    }
}
/* [id^='button_']*/