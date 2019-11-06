package tests.day3;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utils.*;

public class NavigationPractice {
    WebDriver driver = BrowserFactory.getDriver("chrome");

    @Test
    public void test2() {
        driver.manage().window().maximize();
        driver.get("https://vytrack.com");
        BrowserUtils.wait(1);
        driver.quit();
    }
}
