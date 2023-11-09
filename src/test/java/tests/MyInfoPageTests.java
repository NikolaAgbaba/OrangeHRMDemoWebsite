package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.MyInfoPage;

import java.util.Set;

public class MyInfoPageTests extends BaseTest {
    private MyInfoPage myInfoPage;
    private HomePage homePage;
    private String city;
    private String mobile;
    private String workEmail;
    private String name;
    private String relationship;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        myInfoPage = new MyInfoPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("My Info");
        city = faker.address().cityName();
        mobile = faker.number().digits(9);
        workEmail = faker.internet().emailAddress();
        name = faker.name().fullName();
        relationship = faker.relationships().any();
    }

    //checking if the user can change the contact details
    @Test
    public void contactDetailsChanging() {
        SoftAssert softAssert = new SoftAssert();
        myInfoPage.navigateThroughMyInfoPages("Contact Details");
        myInfoPage.changeContactInfo(city, mobile, workEmail);
        softAssert.assertEquals(myInfoPage.getMessageText(), "Success");
        softAssert.assertEquals(myInfoPage.getCity(), city);
        softAssert.assertEquals(myInfoPage.getMobile(), mobile);
        softAssert.assertEquals(myInfoPage.getWorkEmail(), workEmail);
        softAssert.assertAll();
    }

    //checking if the user can go to the help page for creating an emergency contact
    @Test
    public void goingToTheHelpPage(){
        myInfoPage.navigateThroughMyInfoPages("Emergency Contacts");
        myInfoPage.goToTheHelpPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://starterhelp.orangehrm.com/hc/en-us/articles/360018590940-How-to-Add-Emergency-Contacts");
        myInfoPage.exitFromTheHelpPage();
    }

    //checking if the emergency contact creation can be cancelled
    @Test
    public void test(){
        myInfoPage.navigateThroughMyInfoPages("Emergency Contacts");
        myInfoPage.fillEmergencyContactInfo(name, relationship, mobile);
        myInfoPage.cancelEmergencyContactCreation();
        Assert.assertTrue(!myInfoPage.getTableValues().contains(name) && !myInfoPage.getTableValues().contains(relationship) && !myInfoPage.getTableValues().contains(mobile));
    }
}

