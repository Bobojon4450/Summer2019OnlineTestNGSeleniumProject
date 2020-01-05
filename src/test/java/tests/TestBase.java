package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.BrowserUtils;
import utils.ConfigurationReader;
import utils.Driver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/* This class will be a test foundation for all test classes. We'll put here only @BeforeMethod and @AfterMethod */
/* In this way @BeforeMethod and  @AfterMethod methods will be the same */
/* Every test class will extend TestBase class */

public abstract class TestBase {
    /* ExtentReports itself does not build any reports, but allows reporters to access information, which in
     * turn build the said reports. An example of building an HTML report and adding information to ExtentX:
     * ExtentHtmlReporter html = new ExtentHtmlReporter("Extent.html");
     * ExtentXReporter extentx = new ExtentXReporter("localhost");  */
    protected static ExtentReports extentReports;
    /* The ExtentHtmlReporter creates a rich standalone HTML file. It allows several
     * configuration options via the config() method.  */
    protected static ExtentHtmlReporter extentHtmlReporter;
    /* Defines a test. You can add logs, snapshots, assign author and categories to a test and its children. */
    protected static ExtentTest extentTest;


    /* To create report for the class of tests. Class can have many tests. */

    /* <parameter name="test" value="smoke_test_qa1"></parameter> */
    @BeforeTest
    @Parameters({"test", "env_url"}) /* parameter come from testng.xml */
    /* @Optional means, it can be passed from xml or anywhere */
    public void beforeTest(@Optional String param, @Optional String env_url) {
        String reportName = "-test-report";
        if (param != null) {
            reportName = "-" + param;
        }
        /* Here we could add Date and Time after slash before report */
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");//-yyyy-MM-dd HH:mm
        String date = df.format(new Date());
        String filePath = System.getProperty("user.dir") + "/test-output/" + date + reportName + ".html";
        extentReports = new ExtentReports();
        extentHtmlReporter = new ExtentHtmlReporter(filePath);
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setReportName("VYtrack Test Results");
        /* System Information */
        String env = ConfigurationReader.getProperty("url");
        if (env_url != null) {
            env = env_url;
        }
        extentReports.setSystemInfo("Environment", env);/* Sets the environment on the report */
        extentReports.setSystemInfo("Browser", ConfigurationReader.getProperty("browser"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
    }

    @AfterTest
    public void afterTest() {
        /* Writes test information from the started reporters to their output view */
        extentReports.flush();
    }


    @BeforeMethod /* Runs before every test in the class */
    @Parameters("env_url")
    public void setUp(@Optional String env_url) {
        String URL = ConfigurationReader.getProperty("url");
        BrowserUtils.maximaze(Driver.getDriver());
        if (env_url != null) {
            URL = env_url;
        }
        Driver.getDriver().get(URL);
    }

    /* ITestResult class describes the result of a test. */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getName());
            extentTest.fail(result.getThrowable());
            try {
                /* BrowserUtils.getScreenshot(result.getName()) -> takes screenshot and returns location of that screenshot */
                /* This method throws IOException (which is checked exception), any checked exception must be handled */
                extentTest.addScreenCaptureFromPath(BrowserUtils.getScreenshot(result.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test case was skipped : " + result.getName());
        }
        Driver.close();
    }
}




/*  ImplicitWait() and ExplicitWait can trigger issues if both are used in the same test. Use one of them only.   */