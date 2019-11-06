package utils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TitleVerification {
    WebDriver driver = BrowserFactory.getDriver("firefox");

    @Test
    public void TitleVerification_1 () {
        BrowserUtils.maximaze(driver);
        List<String> urls = Arrays.asList("http://practice.cybertekschool.com/", "http://practice.cybertekschool.com/dropdown", "http://practice.cybertekschool.com/login");
        List<String> titles = new ArrayList<>();
        for (String URL : urls) {
            driver.get(URL);
            BrowserUtils.wait(5);
            titles.add(driver.getTitle());
            String url = driver.getCurrentUrl();
            Assert.assertTrue(url.startsWith("http://practice.cybertekschool.com"));
            BrowserUtils.wait(1);
        }
        System.out.println(titles);
        for (String title : titles) {
            Assert.assertEquals(title, titles.get(0));
        }
        driver.quit();
    }

    @Test
    public void TitleVerification_2 (){
        List<String> urls = Arrays.asList("https://www.wayfair.com/", "https://www.walmart.com", "https://www.westelm.com/");
        for (String URL : urls){
            BrowserUtils.maximaze(driver);
            driver.get(URL);
            String targetURL = URL.substring(URL.indexOf('.')+1, URL.lastIndexOf('.'));
            BrowserUtils.wait(5);
            String title = driver.getTitle().toLowerCase().replaceAll(" ","");
            BrowserUtils.wait(1);
            Assert.assertTrue(title.contains(targetURL));
            System.out.print(title +" <|> ");
            System.out.println(targetURL);
        }
        driver.quit();
    }


    @Test
    public void TitleVerification_3 () {
        List<String> urls = Arrays.asList("https://www.wayfair.com/", "https://www.walmart.com", "https://www.westelm.com/", " https://www.luluandgeorgia.com/");
        for (String URL : urls) {
            WebDriver localDriver = BrowserFactory.getDriver("chrome");
            localDriver.get(URL);
            BrowserUtils.maximaze(localDriver);
            BrowserUtils.wait(5);
            String targetURL = URL.substring(URL.indexOf('.') + 1, URL.lastIndexOf('.'));
            String title = localDriver.getTitle().toLowerCase().replaceAll(" ", "");
            BrowserUtils.wait(1);
            Assert.assertTrue(title.contains(targetURL));
            System.out.print(title + " <|> ");
            System.out.println(targetURL);
            localDriver.quit();
        }
        driver.quit();
    }

    @Test
    public void VYTrack_login_Verify (){
        String userName = "user191";
        String password = "UserUser123";
        String expectedUser = "Marco Cartwright";
        driver.get("http://qa2.vytrack.com/user/login");
        BrowserUtils.maximaze(driver);
        WebElement userNameInputBox = driver.findElement(By.cssSelector("#prependedInput"));
        WebElement passwordInputBox = driver.findElement(By.cssSelector("#prependedInput2"));
        userNameInputBox.sendKeys(userName);
        passwordInputBox.sendKeys(password + Keys.ENTER);//ul.nav.pull-right.user-menu > li#user-menu > a:first-child
        BrowserUtils.wait(2);
        WebElement userNameText = driver.findElement(By.cssSelector("ul.nav.pull-right.user-menu > li#user-menu > a:first-child"));
        System.out.println(userNameText.getText());
        BrowserUtils.wait(1);
        Assert.assertTrue(userNameText.isDisplayed());
        driver.quit();
    }

}

/*ul.nav.pull-right.user-menu > li#user-menu*/