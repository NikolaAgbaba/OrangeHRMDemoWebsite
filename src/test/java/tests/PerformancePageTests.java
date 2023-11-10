package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PerformancePage;

public class PerformancePageTests extends BaseTest{
    private PerformancePage performancePage;
    private HomePage homePage;
    private String logName;
    private String logComment;

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        performancePage = new PerformancePage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Performance");
        logName = faker.name().title();
        logComment = faker.lordOfTheRings().character();
    }

    //testing if the user can hide the performance tracker filters
    @Test
    public void hideFiltersTest(){
        performancePage.hideFilters();
        Assert.assertFalse(performancePage.getForm().isDisplayed());
    }

    //testing if the user can see the performance tracker log for the employee
    @Test
    public void viewEmployeePerformance(){
        performancePage.viewRandomEmployeePerformance();
        Assert.assertTrue(performancePage.isPerformanceTrackerPageDisplayed());
    }

    //testing if the user can add the log
    @Test
    public void addingTheLog(){
        performancePage.addPerformanceLog(logName, logComment);
        softAssert.assertEquals(performancePage.getMessageText(), "Success");
        softAssert.assertTrue(performancePage.isLogPresent(logName));
        softAssert.assertAll();
    }
}
