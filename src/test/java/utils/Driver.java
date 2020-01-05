package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    public static WebDriver driver;

    /*  Making sure that object of Driver won't be created    */
    private Driver() {
    }

    public static WebDriver getDriver() {
        /*  if webDriver object was not created yet */
        if (driver == null) {
            String browser = ConfigurationReader.getProperty("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    /*Sets browser to headless browser. Super fast*/
                    /*ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(true);//false makes it run in regular mode, kicking the browser.
                    driver = new ChromeDriver(chromeOptions);*/
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser type");
            }
        }
        return driver;
    }

    public static void close() {
        if (driver != null) {
            /*  close all browsers */
            driver.quit();
            /* Destroy driver object, ready for gc()  */
            driver = null;
        }
    }
}
