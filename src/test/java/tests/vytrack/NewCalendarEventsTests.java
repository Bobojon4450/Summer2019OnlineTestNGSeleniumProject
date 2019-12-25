package tests.vytrack;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import tests.TestBase;
import utils.BrowserUtils;
import utils.ConfigurationReader;

public class NewCalendarEventsTests extends TestBase {

    BasePage basePage = new BasePage();

    @Test(description = "Verify that page subtitle is equals to 'All Calendar Events'")
    public void test1() {
        extentTest = extentReports.createTest("Verify that page subtitle is equals to 'All Calendar Events'");

        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.getProperty("user_name"), ConfigurationReader.getProperty("password"));
        loginPage.navigateTo(ConfigurationReader.getProperty("moduleName"), ConfigurationReader.getProperty("subModuleName"));
        String expectedSubtitle = "All Calendar Events";
        String actualSubTitle = loginPage.getPageSubTitle();
        Assert.assertEquals(actualSubTitle, expectedSubtitle);
        basePage.logOut(); BrowserUtils.wait(2);

        extentTest.pass("Verified that page subtitle 'All Calendar Events' is displayed");
    }

    @Test(description = "Verify that user name is 'Stephan Haley' ")
    public void test2() {
        extentTest = extentReports.createTest("Verify that page subtitle is equals to 'All Calendar Events'");

        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.getProperty("user_name"), ConfigurationReader.getProperty("password"));
        loginPage.navigateTo(ConfigurationReader.getProperty("moduleName"), ConfigurationReader.getProperty("subModuleName"));
        String expectedSubtitle = "All Calendar Events";
        String actualSubTitle = loginPage.getPageSubTitle();
        Assert.assertEquals(actualSubTitle, expectedSubtitle);
       /* basePage.logOut(); BrowserUtils.wait(2);*/

        extentTest.pass("Verified that page subtitle 'All Calendar Events' is displayed");
    }



}
