package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DirectoryPage;
import pages.HomePage;

public class DirectoryPageTests extends BaseTest {
    private DirectoryPage directoryPage;
    private HomePage homePage;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        directoryPage = new DirectoryPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Directory");
    }

    @Test
    public void isExpandedCardDisplayed() {
        Assert.assertTrue(directoryPage.isProfileDisplayed());
    }
}
