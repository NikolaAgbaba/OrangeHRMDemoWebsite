package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BuzzPage;
import pages.HomePage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BuzzPageTests extends BaseTest {
    private BuzzPage buzzPage;
    private HomePage homePage;
    private String inputData;
    private SimpleDateFormat dateFormat;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        buzzPage = new BuzzPage(driver, wait, faker, dateFormat);
        homePage = new HomePage(driver, wait, faker);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Buzz");
    }

    @Test(priority = 1)
    public void publishAPost() {
        inputData = faker.lordOfTheRings().character();
        buzzPage.publishAPost(inputData);
        softAssert.assertEquals(buzzPage.getMessageText(), "Success");
        softAssert.assertEquals(buzzPage.getNewestPostText(), inputData);
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void deleteAPost() {
        String firstPost = buzzPage.getNewestPostText();
        inputData = faker.lordOfTheRings().character();
        buzzPage.deleteOrEditAPost("delete", inputData);
        softAssert.assertEquals(buzzPage.getMessageText(), "Success");
        softAssert.assertFalse(buzzPage.isPostPresent(firstPost));
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void editAPost() {
        String firstPost = buzzPage.getNewestPostText();
        String editingPart = "edited";
        buzzPage.deleteOrEditAPost("edit", editingPart);
        softAssert.assertEquals(buzzPage.getMessageText(), "Success");
        softAssert.assertEquals(firstPost + editingPart, buzzPage.getNewestPostText());
        softAssert.assertAll();
    }

    @Test
    public void sortPostsByNumberOfLikes() {
        buzzPage.sortPosts("Most Liked Posts");
        Assert.assertTrue(buzzPage.arePostsSortedByNumberOfLikes());
    }

    @Test
    public void sortPostsByNumberOfComments() {
        driver.navigate().refresh();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buzzPage.sortPosts("Most Commented Posts");
        Assert.assertTrue(buzzPage.arePostsSortedByNumberOfComments());
    }

    @Test
    public void sortPostsByDate() {
        buzzPage.sortPosts("Most Recent Posts");
        try {
            Assert.assertTrue(buzzPage.arePostsSortedByTheDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
