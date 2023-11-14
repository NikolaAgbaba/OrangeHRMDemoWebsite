package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.PIMPage;

public class PIMPageTests extends BaseTest {
    private PIMPage pimPage;
    private HomePage homePage;
    private String expectedMessage;
    private String firstName;
    private String lastName;
    private String fullName;
    private String id;
    private String username;
    private String password;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        pimPage = new PIMPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("PIM");
        expectedMessage = "Success";
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        fullName = firstName + " " + lastName;
        id = faker.number().digits(5);
        username = faker.name().username();
        password = faker.internet().password() + 1;
    }

    @Test
    public void searchEmployeeByName() {
        Assert.assertTrue(pimPage.searchRandomEmployeeByName());
    }

    @Test
    public void searchEmployeeById() {
        Assert.assertTrue(pimPage.searchRandomEmployeeById());
    }

    @Test
    public void addTheEmployee() {
        pimPage.addEmployee(firstName, lastName, id);
        softAssert.assertEquals(pimPage.getMessageText(), expectedMessage);
        softAssert.assertTrue(pimPage.searchSpecificEmployeeByName(fullName));
        softAssert.assertAll();
    }

    @Test
    public void addTheEmployeeAndCreateCredentials() {
        pimPage.addEmployeeAndCreateLoginCredentials(firstName, lastName, id, username, password, password);
        softAssert.assertEquals(pimPage.getMessageText(), expectedMessage);
        softAssert.assertTrue(pimPage.searchSpecificEmployeeByName(fullName));
        homePage.logout();
        loginPage.login(username, password);
        softAssert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        softAssert.assertAll();
    }

    @Test
    public void deleteTheEmployee() {
        pimPage.deleteTheEmployee();
        softAssert.assertEquals(pimPage.getMessageText(), "Success");
        softAssert.assertTrue(pimPage.getEmployeesList().isEmpty());
        softAssert.assertAll();
    }
}
