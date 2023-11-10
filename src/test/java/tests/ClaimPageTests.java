package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ClaimPage;
import pages.HomePage;

public class ClaimPageTests extends BaseTest {
    private ClaimPage claimPage;
    private HomePage homePage;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        claimPage = new ClaimPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Claim");
    }

    //testing if the user can delete multiple expense types
    @Test
    public void deletingMultipleExpenseTypes() {
        claimPage.configurationDropdownNavigation("Configuration", "Expense Types");
        Assert.assertFalse(claimPage.areExpenseTypesDeleted((int) (Math.random() * 3) + 1));
    }
}
