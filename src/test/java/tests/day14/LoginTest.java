package tests.day14;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import tests.TestBase;
import utils.ConfigurationReader;
import utils.Driver;

public class LoginTest extends TestBase {

    /* @Ignore */   /* ignores the @Test  */
    @Test
    public void test(){
        String URL = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(URL);
    }



}
