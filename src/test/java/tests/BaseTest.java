package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.LoginPage;

import java.time.Duration;
import java.util.List;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Faker faker;
    protected LoginPage loginPage;
    protected SoftAssert softAssert;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        faker = new Faker();
        loginPage = new LoginPage(driver, wait, faker);
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.navigate().to(loginPage.getUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterMethod
    public void afterMethod(){
        List<WebElement> logoutNavigation = driver.findElements(By.className("oxd-topbar-header"));
        if (!logoutNavigation.isEmpty()){
            WebElement nameDropdown = driver.findElement(By.className("oxd-userdropdown-name"));
            nameDropdown.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/web/index.php/auth/logout']")));
            WebElement logoutButton = driver.findElement(By.xpath("//a[@href='/web/index.php/auth/logout']"));
            logoutButton.click();
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
