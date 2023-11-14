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
    private int expenseTypeNumber;

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

    @Test(priority = 2)
    public void deletingMultipleExpenseTypes() {
        claimPage.configurationDropdownNavigation("Configuration", "Expense Types");
        Assert.assertFalse(claimPage.areExpenseTypesDeleted((int) (Math.random() * 3) + 1));
    }

    @Test(priority = 1)
    public void changeTheActivityStatus() {
        claimPage.configurationDropdownNavigation("Configuration", "Expense Types");
        expenseTypeNumber = (int) (Math.random() * (claimPage.getExpenseTypesList().size()));
        Assert.assertTrue(claimPage.hasStatusChanged(expenseTypeNumber));
    }
}
