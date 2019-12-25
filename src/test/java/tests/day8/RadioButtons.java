package tests.day8;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;

public class RadioButtons {
   private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        BrowserUtils.implicitWait(10,driver);
        driver.findElement(By.linkText("Radio Buttons")).click();
    }

    @AfterMethod
    public void tearDown(){
//        driver.quit();
    }

    @Test(description = "Verify that blue button is selected")
    public void test1(){
        WebElement blueButtons = driver.findElement(By.id("blue"));
        Assert.assertTrue(blueButtons.isSelected());
    }

    @Test(description = "Verify that red button is selected")
    public void test2(){
        WebElement redButton = driver.findElement(By.id("red"));
        Assert.assertFalse(redButton.isSelected());
    }

    @Test(description = "Verify that green button is not clickable")
    public void test3(){
        WebElement greenButton = driver.findElement(By.id("green"));
        /* Assert.assertTrue(!greenButton.isEnabled()); works perfect but we should avoid using it is assertions */
        Assert.assertFalse(greenButton.isEnabled());
    }

    @Test(description = "Click on all radio buttons")
    public void test4(){
        List<WebElement> list = driver.findElements(By.cssSelector("input[type='radio']"));
        for(WebElement button : list){
            if(button.isEnabled() && !button.isSelected()){
                button.click();
                System.out.println("Button clicked "+button.getAttribute("id"));
            }else {
                System.out.println("Button was not clicked "+button.getAttribute("id"));
            }
        }
    }
}
