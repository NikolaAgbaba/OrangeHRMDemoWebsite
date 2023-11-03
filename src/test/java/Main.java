import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Helper helper = new Helper(driver, wait);

        //login with valid credentials
        helper.login("Admin", "admin123");
        Assert.isTrue(helper.isUrlValid("/web/index.php/dashboard/index"), "Url is not valid, the user isn't able to login");
        driver.quit();

        //login using invalid username and a valid password
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.login("Admi", "admin123");
        Assert.isTrue(helper.isUrlValid("/web/index.php/auth/login"), "Url is not valid, the user is able to login using invalid username");
        String expectedMessage = "Invalid credentials";
        Assert.isTrue(expectedMessage.equals(helper.getLoginErrorMessage()), "Unsuccessful login message isn't valid");
        driver.quit();

        //login using a valid username and invalid password
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.login("Admin", "admin12");
        Assert.isTrue(helper.isUrlValid("/web/index.php/auth/login"), "Url is not valid, the user is able to login using invalid password");
        Assert.isTrue(expectedMessage.equals(helper.getLoginErrorMessage()), "Unsuccessful login message isn't valid");
        driver.quit();

        //login using a valid username and leaving the password field empty
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.login("Admin", "");
        Assert.isTrue(helper.isUrlValid("/web/index.php/auth/login"), "Url is not valid, the user is able to login using invalid password");
        String expectedRequiredFieldMessage = "Required";
        Assert.isTrue(expectedRequiredFieldMessage.equals(helper.getEmptyFieldErrorMessage()), "Empty field message isn't displayed");
        driver.quit();


        //login using a valid password and leaving the username field empty
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.login("", "admin123");
        Assert.isTrue(helper.isUrlValid("/web/index.php/auth/login"), "Url is not valid, the user is able to login using invalid password");
        Assert.isTrue(expectedRequiredFieldMessage.equals(helper.getEmptyFieldErrorMessage()), "Empty field message isn't displayed");
        driver.quit();


        //going to the Admin page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Admin");
        Assert.isTrue(helper.isUrlValid("/web/index.php/admin/viewSystemUsers"), "The user is not on the valid page");
        driver.quit();


        //going to the PIM page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("PIM");
        Assert.isTrue(helper.isUrlValid("/web/index.php/pim/viewEmployeeList"), "The user is not on the valid page");
        driver.quit();

        //going to the Leave page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Leave");
        Assert.isTrue(helper.isUrlValid("/web/index.php/leave/viewLeaveList"), "The user is not on the valid page");
        driver.quit();

        //going to the Time page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Time");
        Assert.isTrue(helper.isUrlValid("/web/index.php/time/viewEmployeeTimesheet"), "The user is not on the valid page");
        driver.quit();

        //going to the Recruitment page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Recruitment");
        Assert.isTrue(helper.isUrlValid("/web/index.php/recruitment/viewCandidates"), "The user is not on the valid page");
        driver.quit();

        //going to the My Info page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("My Info");
        Assert.isTrue(helper.isUrlValid("/web/index.php/pim/viewPersonalDetails/empNumber/7"), "The user is not on the valid page");
        driver.quit();

        //going to the Performance page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Performance");
        Assert.isTrue(helper.isUrlValid("/web/index.php/performance/searchEvaluatePerformanceReview"), "The user is not on the valid page");
        driver.quit();

        //going to the Dashboard page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Dashboard");
        Assert.isTrue(helper.isUrlValid("/web/index.php/dashboard/index"), "The user is not on the valid page");
        driver.quit();

        //going to the Directory page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Directory");
        Assert.isTrue(helper.isUrlValid("/web/index.php/directory/viewDirectory"), "The user is not on the valid page");
        driver.quit();

        //going to the Maintenance page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Maintenance");
        Assert.isTrue(helper.isUrlValid("/web/index.php/maintenance/purgeEmployee"), "The user is not on the valid page");
        driver.quit();

        //going to the Claim page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Claim");
        Assert.isTrue(helper.isUrlValid("/web/index.php/claim/viewAssignClaim"), "The user is not on the valid page");
        driver.quit();

        //going to the Buzz page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper = new Helper(driver, wait);
        helper.goToThePage("Buzz");
        Assert.isTrue(helper.isUrlValid("/web/index.php/buzz/viewBuzz"), "The user is not on the valid page");
        driver.quit();


    }
}
