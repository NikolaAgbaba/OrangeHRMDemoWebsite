package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MaintenancePage;

public class MaintenancePageTests extends BaseTest {
    private MaintenancePage maintenancePage;
    private HomePage homePage;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        maintenancePage = new MaintenancePage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Maintenance");
    }

    @Test
    public void isAdditionalAuthenticationRequired() {
        Assert.assertTrue(maintenancePage.isAdminAccessFormIsDisplayed());
        driver.navigate().back();
    }

    @Test
    public void isNoteDisplayed() {
        maintenancePage.completeAuthenticationStep(loginPage.getValidPassword());
        Assert.assertTrue(maintenancePage.isTheNoteDisplayed());
    }
}
