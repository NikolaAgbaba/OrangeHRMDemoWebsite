package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;

public class AdminPageTests extends BaseTest {
    private AdminPage adminPage;
    private HomePage homePage;
    private String jobTitle;
    private String editedJob;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        adminPage = new AdminPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
        jobTitle = faker.job().title();
        editedJob = jobTitle + " " + faker.job().title();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Admin");
    }

    //testing if the jobs container is displayed
    @Test(priority = 1)
    public void areJobTitlesDisplayed() {
        adminPage.jobDropdownNavigation("Job", "Job Titles");
        Assert.assertTrue(adminPage.getJobsContainer().isDisplayed());
    }

    //testing if the newly added job is present in the jobs list
    @Test(priority = 2)
    public void addANewJobTitle() {
        adminPage.jobDropdownNavigation("Job", "Job Titles");
        adminPage.addAJobTitle(jobTitle);
        softAssert.assertEquals(adminPage.getMessageText(), "Success");
        softAssert.assertTrue(adminPage.isJobPresent(jobTitle));
        softAssert.assertAll();
    }

    //testing if the job can be deleted
    @Test(priority = 4)
    public void deleteTheJob() {
        adminPage.jobDropdownNavigation("Job", "Job Titles");
        adminPage.deleteTheJob(editedJob);
        softAssert.assertEquals(adminPage.getMessageText(), "Success");
        softAssert.assertTrue(!adminPage.isJobPresent(editedJob));
        softAssert.assertAll();
    }

    //testing if the job can be edited
    @Test(priority = 3)
    public void editTheJob() {
        adminPage.jobDropdownNavigation("Job", "Job Titles");
        adminPage.editTheJob(jobTitle);
        softAssert.assertEquals(adminPage.getMessageText(), "Success");
        softAssert.assertTrue(adminPage.isJobPresent(editedJob));
    }
}
