package tests.day14;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utils.ConfigurationReader;

public class ConfigReaderTest {
   
    @Test
    public void test(){
        String expectedBrowser = "chrome";
        String actualBrowser = ConfigurationReader.getProperty("browser");
        Assert.assertEquals(expectedBrowser,actualBrowser);
        System.out.println(ConfigurationReader.getProperty("url"));
    }

}
