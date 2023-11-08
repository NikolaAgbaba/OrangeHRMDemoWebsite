package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

public class RedirectionTests extends BaseTest{
    private HomePage homePage;

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
    }

    //going to the Admin page
    @Test
    public void navigateToAdminPage(){
        homePage.goToThePage("Admin");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the PIM page
    @Test
    public void navigateToPIMPage(){
        homePage.goToThePage("PIM");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the Leave page
    @Test
    public void navigateToLeavePage(){
        homePage.goToThePage("Leave");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the Time page
    @Test
    public void navigateToTimePage(){
        homePage.goToThePage("Time");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/time/viewEmployeeTimesheet";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the Recruitment page
    @Test
    public void navigateToRecruitmentPage(){
        homePage.goToThePage("Recruitment");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the My Info page
    @Test
    public void navigateToMyInfoPage(){
        homePage.goToThePage("My Info");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the Performance page
    @Test
    public void navigateToPerformancePage(){
        homePage.goToThePage("Performance");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/performance/searchEvaluatePerformanceReview";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the Dashboard page
    @Test
    public void navigateToDashboardPage(){
        homePage.goToThePage("Dashboard");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the Directory page
    @Test
    public void navigateToDirectoryPage(){
        homePage.goToThePage("Directory");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the Maintenance page
    @Test
    public void navigateToMaintenancePage(){
        homePage.goToThePage("Maintenance");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/maintenance/purgeEmployee";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        driver.navigate().back();
    }

    //going to the Admin page
    @Test
    public void navigateToClaimPage(){
        homePage.goToThePage("Claim");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewAssignClaim";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    //going to the Admin page
    @Test
    public void navigateToBuzzPage(){
        homePage.goToThePage("Buzz");
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}
