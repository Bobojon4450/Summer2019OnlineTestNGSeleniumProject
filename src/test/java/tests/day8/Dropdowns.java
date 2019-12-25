package tests.day8;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;
import java.util.List;

public class Dropdowns {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        BrowserUtils.maximaze(driver);
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        BrowserUtils.implicitWait(10,driver);
        driver.findElement(By.linkText("Dropdown")).click();
    }

    @AfterMethod
    public void tearDown(){
//        driver.quit();
    }

    @Test(description = "Select option two from the dropdown")
    public void test1(){
       WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Option 2");
        BrowserUtils.wait(2);
        //getFirstSelectedOption() = to get selected option. This is what was selected from dropdown.
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Option 2");
    }

    @Test(description = "Print list of the states")
    public void test2(){
        WebElement states = driver.findElement(By.cssSelector("#state"));
        Select select = new Select(states);
        List<WebElement>statesList = select.getOptions();
        for(WebElement option: statesList){
            System.out.println(option);
        }
    }

    @Test(description = "Select your state and verify that state is selected")
    public void test3(){
        WebElement state = driver.findElement(By.id("state"));
        Select select = new Select(state);
        select.selectByValue("VA");BrowserUtils.wait(1);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Virginia");
    }
}
