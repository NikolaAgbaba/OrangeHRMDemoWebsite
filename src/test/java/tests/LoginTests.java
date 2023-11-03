package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

public class LoginTests extends BaseTest{
    private LoginPage loginPage;
    private final String VALIDADMINUSERNAME = "Admin";
    private final String VALIDADMINPASSWORD = "admin123";
    private final String CREDENTIALSRRORMESSAGE = "Invalid credentials";
    private final String REQUIREDERRORMESSAGE = "Required";

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        loginPage = new LoginPage(driver, wait, faker);
    }

    //login with valid credentials
    @Test
    public void loginWithValidCredentials(){
        loginPage.enterLoginCredentials(VALIDADMINUSERNAME, VALIDADMINPASSWORD);
        loginPage.login();
        Assert.assertTrue(loginPage.isUrlValid("/web/index.php/dashboard/index"));
    }

    //login with invalid username and a valid password
    @Test
    public void invalidUsernameAndValidPasswordLogin(){
        SoftAssert softAssert = new SoftAssert();
        String invalidUsername = faker.name().username();
        loginPage.enterLoginCredentials(invalidUsername, VALIDADMINPASSWORD);
        loginPage.login();
        softAssert.assertTrue(loginPage.isUrlValid("/web/index.php/auth/login"), "URL is not valid");
        softAssert.assertTrue(loginPage.isInvalidCredentialsMessageValid(CREDENTIALSRRORMESSAGE), "Invalid credentials message isn't valid");
        softAssert.assertAll();
    }

    //login with a valid username and invalid password
    @Test
    public void validUsernameAndInvalidPasswordLogin(){
        SoftAssert softAssert = new SoftAssert();
        String invalidPassword = faker.internet().password();
        loginPage.enterLoginCredentials(VALIDADMINUSERNAME, invalidPassword);
        loginPage.login();
        softAssert.assertTrue(loginPage.isUrlValid("/web/index.php/auth/login"), "URL is not valid");
        softAssert.assertTrue(loginPage.isInvalidCredentialsMessageValid(CREDENTIALSRRORMESSAGE), "Invalid credentials message isn't valid");
        softAssert.assertAll();
    }

    //login while leaving the username field empty
    @Test
    public void emptyUsernameFieldLogin(){
        SoftAssert softAssert = new SoftAssert();
        loginPage.enterLoginCredentials("", VALIDADMINPASSWORD);
        loginPage.login();
        softAssert.assertTrue(loginPage.isUrlValid("/web/index.php/auth/login"), "URL is not valid");
        softAssert.assertTrue(loginPage.isEmptyFieldErrorMessageValid(REQUIREDERRORMESSAGE), "Required");
        softAssert.assertAll();
    }

    @Test
    public void emptyPasswordFieldLogin(){
        SoftAssert softAssert = new SoftAssert();
        loginPage.enterLoginCredentials(VALIDADMINUSERNAME, "");
        loginPage.login();
        softAssert.assertTrue(loginPage.isUrlValid("/web/index.php/auth/login"), "URL is not valid");
        softAssert.assertTrue(loginPage.isEmptyFieldErrorMessageValid(REQUIREDERRORMESSAGE), "Required");
        softAssert.assertAll();
    }
}
