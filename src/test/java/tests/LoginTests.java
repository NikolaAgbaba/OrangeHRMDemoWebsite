package tests;

import framework.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

import java.io.IOException;

public class LoginTests extends BaseTest{
    private String validAdminUsername;
    private String validAdminPassword;
    private final String credentialsErrorMessage = "Invalid credentials";
    private final String requiredErrorMessage = "Required";
    private String path = "credentials.properties";


    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        validAdminUsername = loginPage.getValidUsername();
        validAdminPassword = loginPage.getValidPassword();
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
        String expectedUrl = loginPage.getUrl();
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
        String expectedUrl = loginPage.getUrl();
        loginPage.login(validAdminUsername, invalidPassword);
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertTrue(loginPage.isInvalidCredentialsMessageValid(credentialsErrorMessage), "Invalid credentials message isn't valid");
        softAssert.assertAll();
    }

    //login while leaving the username field empty
    @Test
    public void emptyUsernameFieldLogin(){
        SoftAssert softAssert = new SoftAssert();
        String expectedUrl = loginPage.getUrl();
        loginPage.login("", validAdminPassword);
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertTrue(loginPage.isEmptyFieldErrorMessageValid(requiredErrorMessage), "Required");
        softAssert.assertAll();
    }

    @Test
    public void emptyPasswordFieldLogin(){
        SoftAssert softAssert = new SoftAssert();
        String expectedUrl = loginPage.getUrl();
        loginPage.login(validAdminUsername, "");
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertTrue(loginPage.isEmptyFieldErrorMessageValid(requiredErrorMessage), "Required");
        softAssert.assertAll();
    }

//    @Test
//    public void readUsername(){
//        loginPage.readUsername(path);
//    }
//
//    @Test
//    public void readPassword(){
//        loginPage.readPassword(path);
//    }
//
}
