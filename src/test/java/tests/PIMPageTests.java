package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.PIMPage;

public class PIMPageTests extends BaseTest{
    private PIMPage pimPage;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        pimPage = new PIMPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
        loginPage = new LoginPage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage.login(loginPage.readUsername("credentials.txt"), loginPage.readPassword("credentials.txt"));
        homePage.goToThePage("PIM");
    }

    //checking if the employee searched by name is present in the filtered list
    @Test
    public void searchEmployeeByName(){
        Assert.assertTrue(pimPage.searchRandomEmployeeByName());
    }

    //checking if the employee searched by id is present in the filtered list
    @Test
    public void searchEmployeeById(){
        Assert.assertTrue(pimPage.searchRandomEmployeeById());
    }

    //checking if the new employee can be added
    @Test
    public void addTheEmployee(){
        SoftAssert softAssert = new SoftAssert();
        String expectedMessage = "Success";
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String fullName = firstName + " " + lastName;
        String id = faker.number().digits(5);
        pimPage.addEmployee(firstName, lastName, id);
        softAssert.assertEquals(pimPage.getMessageText(), expectedMessage);
        softAssert.assertTrue(pimPage.searchSpecificEmployeeByName(fullName));
        softAssert.assertAll();
    }
}
