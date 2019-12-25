package tests.vytrack;

import com.google.gson.internal.$Gson$Preconditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CreateCarPage;
import pages.LoginPage;
import pages.VehiclesPage;
import tests.TestBase;
import utils.ConfigurationReader;

public class CreateCarTest extends TestBase {

    @Test(dataProvider = "Create Car", description = "Create some random car")
    public void test1(String licensePlates, String carType, String fuelType){
        extentTest = extentReports.createTest("Create a new car");
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();
        CreateCarPage createCarPage = new CreateCarPage();
        loginPage.login(ConfigurationReader.getProperty("user_name"), ConfigurationReader.getProperty("password"));
        loginPage.navigateTo("Fleet", "Vehicles");
        loginPage.waitUntilLoaderMaskDisappear();
        vehiclesPage.clickToCreateCar();
        loginPage.waitUntilLoaderMaskDisappear();
        createCarPage.licensePlateElement.sendKeys(licensePlates);
        createCarPage.selectTags(carType);
        createCarPage.selectFuelType(fuelType);
        createCarPage.saveAndCloseButtonElement.click();

        extentTest.pass("New car was created");
    }

    @DataProvider(name = "Create Car")
    public Object[][] createCar() {
        return new Object[][]{
                {"VPN 44-20", "Sedan", "Gasoline"},
                {"JVM 12-36", "Convertible", "Hybrid"},
                {"SASSI CAR", "Compact", "Electric"},
                {"RTD 11-11", "Senior", "Diesel"}
        };
    }
}
