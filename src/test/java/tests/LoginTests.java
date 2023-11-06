package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

public class LoginTests extends BaseTest{
    private LoginPage loginPage;
    private final String validAdminUsername = "Admin";
    private final String validAdminPassword = "admin123";
    private final String credentialsErrorMessage = "Invalid credentials";
    private final String requiredErrorMessage = "Required";

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        loginPage = new LoginPage(driver, wait, faker);
    }

    //login with valid credentials
    @Test
    public void loginWithValidCredentials(){
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        loginPage.login(validAdminUsername, validAdminPassword);
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

    //login with invalid username and a valid password
    @Test
    public void invalidUsernameAndValidPasswordLogin(){
        SoftAssert softAssert = new SoftAssert();
        String invalidUsername = faker.name().username();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        loginPage.login(invalidUsername, validAdminPassword);
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertTrue(loginPage.isInvalidCredentialsMessageValid(credentialsErrorMessage), "Invalid credentials message isn't valid");
        softAssert.assertAll();
    }

    //login with a valid username and invalid password
    @Test
    public void validUsernameAndInvalidPasswordLogin(){
        SoftAssert softAssert = new SoftAssert();
        String invalidPassword = faker.internet().password();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        loginPage.login(validAdminUsername, invalidPassword);
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertTrue(loginPage.isInvalidCredentialsMessageValid(credentialsErrorMessage), "Invalid credentials message isn't valid");
        softAssert.assertAll();
    }

    //login while leaving the username field empty
    @Test
    public void emptyUsernameFieldLogin(){
        SoftAssert softAssert = new SoftAssert();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        loginPage.login("", validAdminPassword);
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertTrue(loginPage.isEmptyFieldErrorMessageValid(requiredErrorMessage), "Required");
        softAssert.assertAll();
    }

    @Test
    public void emptyPasswordFieldLogin(){
        SoftAssert softAssert = new SoftAssert();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        loginPage.login(validAdminUsername, "");
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertTrue(loginPage.isEmptyFieldErrorMessageValid(requiredErrorMessage), "Required");
        softAssert.assertAll();
    }
}
