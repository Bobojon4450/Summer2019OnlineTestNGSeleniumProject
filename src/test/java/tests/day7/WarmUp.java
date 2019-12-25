package tests.day7;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.ArrayList;
import java.util.List;

public class WarmUp {

    WebDriver driver = BrowserFactory.getDriver("chrome");

    @Test
    public void testLinks(){
        driver.get("https://cybertekschool.com");
        BrowserUtils.wait(1);
        List<WebElement> links = new ArrayList<WebElement>();
        links = driver.findElements(By.xpath("//a"));
        System.out.println(links.size());

        for(WebElement each : links){
            if (!each.getText().isEmpty()){
                String attr = each.getAttribute("href");
                String text = each.getText();
                System.out.println("Text: " + text + " Links: " + attr);
            }
        }
        driver.quit();
    }
}
