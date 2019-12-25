package tests.vytrack;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.TestBase;
import utils.ConfigurationReader;
import utils.Driver;

/*  We write extends TestBase to inherit @Before and @After methods
    this class will be dedicated to the tests related to Login page only  */
/*  We don't have to find elements here, we should find elements in the page classes only. */

public class LoginTests extends TestBase {

    LoginPage logPage = new LoginPage();

    @Test(description = "Verify that page title is a 'Dashboard'")
    public void test1(){
        extentTest = extentReports.createTest("Create a new car");

        logPage.login(ConfigurationReader.getProperty("user_name"), ConfigurationReader.getProperty("password"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        /* Waits until title is 'Dashboard' */
        wait.until(ExpectedConditions.titleIs("Dashboard"));
        Assert.assertEquals(Driver.getDriver().getTitle(),"Dashboard");

        extentTest.pass("New car was created");
    }





}
