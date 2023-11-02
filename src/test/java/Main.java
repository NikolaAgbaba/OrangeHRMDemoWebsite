import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Test1 test1 = new Test1(driver, wait);

        //login with valid credentials
        test1.login("Admin", "admin123");
        Assert.isTrue(test1.isUrlValid("/web/index.php/dashboard/index"), "Url is not valid, the user isn't able to login");
        driver.quit();

        //login using invalid username and a valid password
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.login("Admi", "admin123");
        Assert.isTrue(test1.isUrlValid("/web/index.php/auth/login"), "Url is not valid, the user is able to login using invalid username");
        String expectedMessage = "Invalid credentials";
        Assert.isTrue(expectedMessage.equals(test1.getLoginErrorMessage()), "Unsuccessful login message isn't valid");
        driver.quit();

        //login using a valid username and invalid password
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.login("Admin", "admin12");
        Assert.isTrue(test1.isUrlValid("/web/index.php/auth/login"), "Url is not valid, the user is able to login using invalid password");
        Assert.isTrue(expectedMessage.equals(test1.getLoginErrorMessage()), "Unsuccessful login message isn't valid");
        driver.quit();

        //login using a valid username and leaving the password field empty
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.login("Admin", "");
        Assert.isTrue(test1.isUrlValid("/web/index.php/auth/login"), "Url is not valid, the user is able to login using invalid password");
        String expectedRequiredFieldMessage = "Required";
        Assert.isTrue(expectedRequiredFieldMessage.equals(test1.getEmptyFieldErrorMessage()), "Empty field message isn't displayed");
        driver.quit();

        //login using a valid password and leaving the username field empty
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.login("", "admin123");
        Assert.isTrue(test1.isUrlValid("/web/index.php/auth/login"), "Url is not valid, the user is able to login using invalid password");
        Assert.isTrue(expectedRequiredFieldMessage.equals(test1.getEmptyFieldErrorMessage()), "Empty field message isn't displayed");
        driver.quit();

        //going to the Admin page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Admin");
        Assert.isTrue(test1.isUrlValid("/web/index.php/admin/viewSystemUsers"), "The user is not on the valid page");
        driver.quit();

        //going to the PIM page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("PIM");
        Assert.isTrue(test1.isUrlValid("/web/index.php/pim/viewEmployeeList"), "The user is not on the valid page");
        driver.quit();

        //going to the Leave page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Leave");
        Assert.isTrue(test1.isUrlValid("/web/index.php/leave/viewLeaveList"), "The user is not on the valid page");
        driver.quit();

        //going to the Time page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Time");
        Assert.isTrue(test1.isUrlValid("/web/index.php/time/viewEmployeeTimesheet"), "The user is not on the valid page");
        driver.quit();

        //going to the Recruitment page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Recruitment");
        Assert.isTrue(test1.isUrlValid("/web/index.php/recruitment/viewCandidates"), "The user is not on the valid page");
        driver.quit();

        //going to the My Info page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("My Info");
        Assert.isTrue(test1.isUrlValid("/web/index.php/pim/viewPersonalDetails/empNumber/7"), "The user is not on the valid page");
        driver.quit();

        //going to the Performance page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Performance");
        Assert.isTrue(test1.isUrlValid("/web/index.php/performance/searchEvaluatePerformanceReview"), "The user is not on the valid page");
        driver.quit();

        //going to the Dashboard page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Dashboard");
        Assert.isTrue(test1.isUrlValid("/web/index.php/dashboard/index"), "The user is not on the valid page");
        driver.quit();

        //going to the Directory page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Directory");
        Assert.isTrue(test1.isUrlValid("/web/index.php/directory/viewDirectory"), "The user is not on the valid page");
        driver.quit();

        //going to the Maintenance page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Maintenance");
        Assert.isTrue(test1.isUrlValid("/web/index.php/maintenance/purgeEmployee"), "The user is not on the valid page");
        driver.quit();

        //going to the Claim page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Claim");
        Assert.isTrue(test1.isUrlValid("/web/index.php/claim/viewAssignClaim"), "The user is not on the valid page");
        driver.quit();

        //going to the Buzz page
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        test1 = new Test1(driver, wait);
        test1.goToThePage("Buzz");
        Assert.isTrue(test1.isUrlValid("/web/index.php/buzz/viewBuzz"), "The user is not on the valid page");
        driver.quit();




        //valid login test
//        login("Admin", "admin123");
//        Assert.isTrue(isUrlValid("/web/index.php/dashboard/index"), "Url is not valid");
    }


//    public static void login(String username, String password){
//        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        driver.manage().window().maximize();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-login-button")));
//        WebElement usernameField = driver.findElement(By.name("username"));
//        WebElement passwordField = driver.findElement(By.name("password"));
//        WebElement loginButton = driver.findElement(By.className("orangehrm-login-button"));
//        usernameField.sendKeys(username);
//        passwordField.sendKeys(password);
//        loginButton.click();
//        driver.quit();
//    }

//    public static boolean isUrlValid(String urlEnd){
//        return getCurrentUrl().endsWith(urlEnd);
//    }
}
