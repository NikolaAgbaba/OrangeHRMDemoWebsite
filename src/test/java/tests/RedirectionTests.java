package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

public class RedirectionTests extends BaseTest {
    private HomePage homePage;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
    }

    @Test
    public void navigateToAdminPage() {
        homePage.goToThePage("Admin");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }

    @Test
    public void navigateToPIMPage() {
        homePage.goToThePage("PIM");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
    }

    @Test
    public void navigateToLeavePage() {
        homePage.goToThePage("Leave");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList");
    }

    @Test
    public void navigateToTimePage() {
        homePage.goToThePage("Time");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/time/viewEmployeeTimesheet");
    }

    @Test
    public void navigateToRecruitmentPage() {
        homePage.goToThePage("Recruitment");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates");
    }

    @Test
    public void navigateToMyInfoPage() {
        homePage.goToThePage("My Info");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7");
    }

    @Test
    public void navigateToPerformancePage() {
        homePage.goToThePage("Performance");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/performance/searchEvaluatePerformanceReview");
    }

    @Test
    public void navigateToDashboardPage() {
        homePage.goToThePage("Dashboard");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @Test
    public void navigateToDirectoryPage() {
        homePage.goToThePage("Directory");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory");
    }

    @Test
    public void navigateToMaintenancePage() {
        homePage.goToThePage("Maintenance");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/maintenance/purgeEmployee");
        driver.navigate().back();
    }

    @Test
    public void navigateToClaimPage() {
        homePage.goToThePage("Claim");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewAssignClaim");
    }

    @Test
    public void navigateToBuzzPage() {
        homePage.goToThePage("Buzz");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz");
    }
}
