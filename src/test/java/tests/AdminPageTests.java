package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;

public class AdminPageTests extends BaseTest{
    private AdminPage adminPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        adminPage = new AdminPage(driver, wait, faker);
        loginPage = new LoginPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage.enterLoginCredentials("Admin", "admin123");
        loginPage.login();
        homePage.goToThePage("Admin");
    }

    @Test
    public void goToTheJobTitles(){
        adminPage.goToTheJobTitles("Job", "Job Titles");
    }
}
