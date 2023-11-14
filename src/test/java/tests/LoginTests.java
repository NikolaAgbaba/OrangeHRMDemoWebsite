package tests;

import framework.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;

import java.io.IOException;

public class LoginTests extends BaseTest {
    private String validAdminUsername;
    private String validAdminPassword;
    private final String credentialsErrorMessage = "Invalid credentials";
    private final String requiredErrorMessage = "Required";

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        validAdminUsername = loginPage.getValidUsername();
        validAdminPassword = loginPage.getValidPassword();
    }

    @Test
    public void loginWithValidCredentials() {
        loginPage.login(validAdminUsername, validAdminPassword);
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @Test
    public void invalidUsernameAndValidPasswordLogin() {
        loginPage.login(faker.name().username(), validAdminPassword);
        softAssert.assertEquals(driver.getCurrentUrl(), loginPage.getUrl());
        softAssert.assertTrue(loginPage.isInvalidCredentialsMessageValid(credentialsErrorMessage), "Invalid credentials message isn't valid");
        softAssert.assertAll();
    }

    @Test
    public void validUsernameAndInvalidPasswordLogin() {
        loginPage.login(validAdminUsername, faker.internet().password());
        softAssert.assertEquals(driver.getCurrentUrl(), loginPage.getUrl());
        softAssert.assertTrue(loginPage.isInvalidCredentialsMessageValid(credentialsErrorMessage), "Invalid credentials message isn't valid");
        softAssert.assertAll();
    }

    @Test
    public void emptyUsernameFieldLogin() {
        loginPage.login("", validAdminPassword);
        softAssert.assertEquals(driver.getCurrentUrl(), loginPage.getUrl());
        softAssert.assertTrue(loginPage.isEmptyFieldErrorMessageValid(requiredErrorMessage), "Required");
        softAssert.assertAll();
    }

    @Test
    public void emptyPasswordFieldLogin() {
        loginPage.login(validAdminUsername, "");
        softAssert.assertEquals(driver.getCurrentUrl(), loginPage.getUrl());
        softAssert.assertTrue(loginPage.isEmptyFieldErrorMessageValid(requiredErrorMessage), "Required");
        softAssert.assertAll();
    }
}
