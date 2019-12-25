package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;
import java.util.concurrent.TimeUnit;

public class JSExecutorPractice {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        BrowserUtils.maximaze(driver);
//        driver.get("https://practice-cybertekschool.herokuapp.com/infinite_scroll");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearUp(){
        driver.quit();
    }

    @Test(description = "Scrolling with JavaScriptExecutor")
    public void test1(){
        driver.get("https://practice-cybertekschool.herokuapp.com/infinite_scroll");
        JavascriptExecutor js = (JavascriptExecutor) driver;// we could do -> new ChromeDriver()
        for(int i = 1; i <= 10; i++){
            js.executeScript("window.scrollBy(0,500);"); /* x axis = 0, y axis = 500  */
            BrowserUtils.wait(2);
        }
    }

    @Test(description = "Scrolling with JSexecutor to specific element")
    public void test2(){
        String URL = "http://practice.cybertekschool.com/large";
        driver.get(URL);
        WebElement link = driver.findElement(By.linkText("Cybertek School"));
        BrowserUtils.wait(2);//for demo
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //this script must scroll, until link element is visible
        //once link element will be visible, it will stop scrolling
        //arguments[0] = means first webelement after comma (link)
        //argument it is an array of webelements after comma
        //arguments[0] = link webelement, it can be any element
        js.executeScript("arguments[0].scrollIntoView(true)", link);
        BrowserUtils.wait(2);
    }


    @Test(description = "Click with JS executor")
    public void test3() {
        String URL = "http://practice.cybertekschool.com/dynamic_loading";
        driver.get(URL);
        WebElement link1 = driver.findElement(By.partialLinkText("Example 1"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //arguments[0] = link1 web element
        //whenever regular selenium methods are not working, I use js executor
        //or for scrolling
        //arguments[0].click() is an alternative for link1.click()
        BrowserUtils.wait(1);
        js.executeScript("arguments[0].click()",link1);
        BrowserUtils.wait(1);
    }

    @Test(description = "Enter text with JS executor")
    public void test4() {
        String URL = "http://practice.cybertekschool.com/sign_up";
        driver.get(URL);
        WebElement name = driver.findElement(By.name("full_name"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement button = driver.findElement(By.name("wooden_spoon"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', 'Adamsho')", name);
        BrowserUtils.wait(1);
        js.executeScript("arguments[0].setAttribute('value', 'adam@gmail.com')", email);
        BrowserUtils.wait(1);
        js.executeScript("arguments[0].click()", button);
        BrowserUtils.wait(1);
    }


//document.getElementsByName('full_name')[0].setAttribute('value','My name')


}
