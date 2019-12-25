package tests.day8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;

public class CheckBoxes {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        BrowserUtils.implicitWait(10,driver);
        driver.findElement(By.linkText("Checkboxes")).click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void test1(){
        List<WebElement> list = driver.findElements(By.cssSelector("[type='checkbox']"));
        for(WebElement checkBox : list){
            if(checkBox.isEnabled() && !checkBox.isSelected()){
                checkBox.click();
                System.out.println("Clicked the checkbox: "+(list.indexOf(checkBox)+1));
            }else
                System.out.println("Checkbox wasn't clicked:  "+(list.indexOf(checkBox)+1));
        }
    }



}
