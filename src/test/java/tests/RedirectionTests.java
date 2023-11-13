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

    //navigating to the Admin page
    @Test
    public void navigateToAdminPage() {
        homePage.goToThePage("Admin");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }

    //navigating to the PIM page
    @Test
    public void navigateToPIMPage() {
        homePage.goToThePage("PIM");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
    }

    //navigating to the Leave page
    @Test
    public void navigateToLeavePage() {
        homePage.goToThePage("Leave");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList");
    }

    //navigating to the Time page
    @Test
    public void navigateToTimePage() {
        homePage.goToThePage("Time");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/time/viewEmployeeTimesheet");
    }

    //navigating to the Recruitment page
    @Test
    public void navigateToRecruitmentPage() {
        homePage.goToThePage("Recruitment");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates");
    }

    //navigating to the My Info page
    @Test
    public void navigateToMyInfoPage() {
        homePage.goToThePage("My Info");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7");
    }

    //navigating to the Performance page
    @Test
    public void navigateToPerformancePage() {
        homePage.goToThePage("Performance");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/performance/searchEvaluatePerformanceReview");
    }

    //navigating to the Dashboard page
    @Test
    public void navigateToDashboardPage() {
        homePage.goToThePage("Dashboard");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    //navigating to the Directory page
    @Test
    public void navigateToDirectoryPage() {
        homePage.goToThePage("Directory");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory");
    }

    //navigating to the Maintenance page
    @Test
    public void navigateToMaintenancePage() {
        homePage.goToThePage("Maintenance");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/maintenance/purgeEmployee");
        driver.navigate().back();
    }

    //navigating to the Admin page
    @Test
    public void navigateToClaimPage() {
        homePage.goToThePage("Claim");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewAssignClaim");
    }

    //navigating to the Admin page
    @Test
    public void navigateToBuzzPage() {
        homePage.goToThePage("Buzz");
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz");
    }
}
