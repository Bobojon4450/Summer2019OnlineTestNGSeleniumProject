package tests.vytrack;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CalendarEventsPage;
import pages.CreateCalendarEventPage;
import pages.LoginPage;
import tests.TestBase;
import utils.ConfigurationReader;

public class CreateCalendarEventTests extends TestBase {

    @Test(description = "Verify owners name is equals to 'Stephan Haley' (it works on qa1 'storemenager85')")
    public void test1(){
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        CreateCalendarEventPage createCalendarEventPage = new CreateCalendarEventPage();

        /*  login as Stephan Haley (storemanager85)     */
        loginPage.login(ConfigurationReader.getProperty("user_name"), ConfigurationReader.getProperty("password"));

        /*  Go to calendar events page    */
        loginPage.navigateTo(ConfigurationReader.getProperty("moduleName"), ConfigurationReader.getProperty("subModuleName"));

        /*  Click on create calendar event button   */
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        calendarEventsPage.clickToCreateCalendarEvent();

        calendarEventsPage.waitUntilLoaderMaskDisappear();
        String expectedOwner = "Stephan Haley";
        String actualOwner = createCalendarEventPage.owner.getText().trim();

        Assert.assertEquals(actualOwner, expectedOwner);
    }

}
