package tests.day3;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.BrowserFactory;

public class BrowserFactoryTest {

        @Test
        public void test(){
            WebDriver driver = BrowserFactory.getDriver("chrome");
            driver.get("http://vytrack.com");
            driver.manage().window().maximize();
            System.out.println(driver.getPageSource());
            driver.quit();
        }


}
