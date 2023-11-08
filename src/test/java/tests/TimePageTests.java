package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.TimePage;

public class TimePageTests extends BaseTest {
    private TimePage timePage;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        timePage = new TimePage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
        loginPage = new LoginPage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Time");
    }

    //checking if the timesheet is added and the values in the timesheet are the same as in the input fields
    @Test
    public void addTheTimesheet() {
        Assert.assertTrue(timePage.verifyTimesheetValues("Create a timesheet"));
    }

    //checking if the timesheet is edited and the values in the timesheet are the same as in the input fields
    @Test
    public void editTheTimesheet(){
        Assert.assertTrue(timePage.verifyTimesheetValues("Edit a timesheet"));
    }
}
