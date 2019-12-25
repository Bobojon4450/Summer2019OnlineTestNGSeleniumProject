package tests.day13;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebTablesPractice {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        BrowserUtils.maximaze(driver);
        driver.get("https://practice-cybertekschool.herokuapp.com/tables");
        wait = new WebDriverWait(driver,35);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("table1")));
    }

    @AfterMethod
    public void tearUp(){
//        driver.quit();
    }

    //table
    //thead - table header (columns names)
    //tbody - table body (content)
    //tr - table row
    //td - table data
    //th - table header data

    @Test(description = "Print table 1 data")
    public void test1(){
        WebElement table = driver.findElement(By.id("table1"));
        System.out.println(table.getText());
    }

    @Test(description = "Verify that number of columns is six")
    public void test2(){
        int actualColumnCount = driver.findElements(By.xpath("//table[@id='table1']//th")).size();
        int expectedColumnCount = 6;
        Assert.assertTrue(actualColumnCount == expectedColumnCount);
        Assert.assertEquals(actualColumnCount,expectedColumnCount);
    }

    @Test(description = "Verify that number of rows is five")
    public void test3(){
        int expectedRowCount = 5;
        int actualRowCount = driver.findElements(By.xpath("//table[@id='table1']//tr")).size();
        Assert.assertEquals(actualRowCount, expectedRowCount);
    }

    @Test(description = "Print all values from the second row (excluding table header)")
    public void test4(){
        List<WebElement> list = driver.findElements(By.xpath("//table[@id='table1']//tbody/tr[2]/td"));
        for(WebElement each : list){
            System.out.println(each);
        }
    }

    @DataProvider(name = "testData")
    public static Object[][] testData() {
        return new Object[][]{{1}, {2}, {3}, {4}};
    }

    @Test(dataProvider = "testData",description = "Print all values from the second row (excluding table header)")
    public void test5(int x){
        //if index = 1, then it's a first row
        //if index = 2, then it's a second row
        //if we don't specify td index, it will take all td elements
        //in css we use space " ", in xpath // to get to any child
        //or in css we use ">", in xpath /, to get to direct child
        //css selector alternative: table[id='table1'] tbody tr:nth-of-type(2) td
        //if index will exceed table size, you will not get any errors, list will be just empty
        //findElements() doesn't give NoSuchElementException, in any case.
//        int index = 1;
        List<WebElement> list = driver.findElements(By.xpath("//table[@id='table1']//tbody/tr["+x+"]/td"));
        for(WebElement each : list){
            System.out.println(each.getText());
        }
    }

    @Test(description = "Verify that email in the third row is equal to jdoe@hotmail.com")
    public void test6(){
        int row = 3;
        int column = 3;
        WebElement cell = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr["+row+"]/td["+column+"]"));
        String expectedEmail = "jdoe@hotmail.com";
        String actualEmail = cell.getText();
        Assert.assertEquals(actualEmail, expectedEmail);
    }

        //[id='table1'] tbody tr td:nth-child(3)
       //table[id='table1'] tbody tr td:nth-child(3)        Both work perfectly.
    @Test(description = "Verify that emails from all columns has a '@' sing.")
    public void test7() {
        final int column = 3;
        for (int row = 1; row <= 4; row++) {
            WebElement cell = driver.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + row + "]/td[" + column + "]"));
            System.out.println(cell.getText());
            Assert.assertTrue(cell.getText().contains("@"));
        }
    }

    @Test(description = "Verify that emails from all columns has a '@' sing.")
    public void Vasyl_test7() {
        List<WebElement> cell = driver.findElements(By.xpath("//table[@id='table1']//tbody/tr/td[3]"));
        for (WebElement each : cell ) {
            System.out.println(each.getText());
            Assert.assertTrue(each.getText().contains("@"));
        }
    }

/*----------------------------------------------------------------------------------------*/

    /**
     * Step 1. Click on "Last Name" column name
     * Step 2. Get all values from "Last Name" column
     * Step 3. Verify that column is sorted in alphabetic order
     */
    @Test
    public void test8(){       // use compareTo()
        WebElement lastNameButton = driver.findElement(By.xpath("//table[@id='table1']//th[1]"));//table[@id='table1']//*[text()='Last Name']
        lastNameButton.click();
        List<WebElement> lastNames = driver.findElements(By.xpath("//table[@id='table1']//tbody//td[1]"));
        for (int index = 0; index < lastNames.size() - 1; index++) {
            String lastName = lastNames.get(index).getText();
            String followingLastName = lastNames.get(index + 1).getText();
            Assert.assertTrue(lastName.compareTo(followingLastName) < 0);
        }

    }

//----------------------------------------------------------//
    @DataProvider(name = "Content")
    public Object[][] Contend(){
        Object[][] cell = new Object[4][1];
        for (int row = 1; row < 5; row++) {
            cell[row - 1][0] = driver.findElement(By.cssSelector("table[id='table1'] tbody tr:nth-of-type(" + row + ") td:nth-of-type(3)")).getText();
            System.out.println(cell[row - 1][0]);
        }
        return cell;
    }

    @Test(description = "chech if emails contains '@' ", dataProvider = "Content")
    public void test6(String cell){
        Assert.assertTrue(cell.contains("@"));
    }


}

//------------------------------------------------------------------------------------------------//
/*

Examples of good xpath:
cssLocator -> table[id='table1'] tbody tr:nth-of-type(1) td:nth-of-type(3)
OR
xpath      -> //table[@id='table1']//tbody//tr[1]//td[3]

*/