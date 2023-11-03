package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class RedirectionTests extends BaseTest{
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        homePage = new HomePage(driver, wait, faker);
        loginPage = new LoginPage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage.enterLoginCredentials("Admin", "admin123");
        loginPage.login();
    }

    //going to the Admin page
    @Test
    public void goToAdminPage(){
        homePage.goToThePage("Admin");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/admin/viewSystemUsers"));
    }

    //going to the PIM page
    @Test
    public void goToPIMPage(){
        homePage.goToThePage("PIM");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/pim/viewEmployeeList"));
    }

    //going to the Leave page
    @Test
    public void goToLeavePage(){
        homePage.goToThePage("Leave");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/leave/viewLeaveList"));
    }

    //going to the Time page
    @Test
    public void goToTimePage(){
        homePage.goToThePage("Time");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/time/viewEmployeeTimesheet"));
    }

    //going to the Recruitment page
    @Test
    public void goToRecruitmentPage(){
        homePage.goToThePage("Recruitment");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/recruitment/viewCandidates"));
    }

    //going to the My Info page
    @Test
    public void goToMyInfoPage(){
        homePage.goToThePage("My Info");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/pim/viewPersonalDetails/empNumber/7"));
    }

    //going to the Performance page
    @Test
    public void goToPerformancePage(){
        homePage.goToThePage("Performance");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/performance/searchEvaluatePerformanceReview"));
    }

    //going to the Dashboard page
    @Test
    public void goToDashboardPage(){
        homePage.goToThePage("Dashboard");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/dashboard/index"));
    }

    //going to the Directory page
    @Test
    public void goToDirectoryPage(){
        homePage.goToThePage("Directory");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/directory/viewDirectory"));
    }

    //going to the Maintenance page
    @Test
    public void goToMaintenancePage(){
        homePage.goToThePage("Maintenance");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/maintenance/purgeEmployee"));
        driver.navigate().back();
    }

    //going to the Admin page
    @Test
    public void goToClaimPage(){
        homePage.goToThePage("Claim");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/claim/viewAssignClaim"));
    }

    //going to the Admin page
    @Test
    public void goToBuzzPage(){
        homePage.goToThePage("Buzz");
        Assert.assertTrue(homePage.isUrlValid("/web/index.php/buzz/viewBuzz"));
    }
}
