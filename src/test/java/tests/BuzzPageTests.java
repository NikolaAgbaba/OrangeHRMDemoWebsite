package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BuzzPage;
import pages.HomePage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BuzzPageTests extends BaseTest{
    private BuzzPage buzzPage;
    private HomePage homePage;
    private String inputData;
    private SimpleDateFormat dateFormat;

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        buzzPage = new BuzzPage(driver, wait, faker, dateFormat);
        homePage = new HomePage(driver, wait, faker);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Buzz");
    }

    //checking if the user can publish a post
    @Test (priority = 1)
    public void publishAPost(){
        inputData = faker.lordOfTheRings().character();
        buzzPage.publishAPost(inputData);
        softAssert.assertEquals(buzzPage.getMessageText(), "Success");
        softAssert.assertEquals(buzzPage.getNewestPostText(), inputData);
        softAssert.assertAll();
    }

    //checking if the user can delete a post
    @Test (priority = 3)
    public void deleteAPost(){
        String firstPost = buzzPage.getNewestPostText();
        inputData = faker.lordOfTheRings().character();
        buzzPage.deleteOrEditAPost("delete", inputData);
        softAssert.assertEquals(buzzPage.getMessageText(), "Success");
        softAssert.assertFalse(buzzPage.isPostPresent(firstPost));
        softAssert.assertAll();
    }

    //checking if the user can edit a post
    @Test (priority = 2)
    public void editAPost(){
        String firstPost = buzzPage.getNewestPostText();
        System.out.println(firstPost);
        String editingPart = "edited";
        buzzPage.deleteOrEditAPost("edit", editingPart);
        softAssert.assertEquals(buzzPage.getMessageText(), "Success");
        System.out.println(buzzPage.getNewestPostText());
        softAssert.assertEquals(firstPost + editingPart, buzzPage.getNewestPostText());
        softAssert.assertAll();
    }

    //checking if the user can sort posts by the number of likes
    @Test
    public void sortPostsByNumberOfLikes(){
        buzzPage.sortPosts("Most Liked Posts");
        Assert.assertTrue(buzzPage.arePostsSortedByNumberOfLikes());
    }

    //checking if the user can sort posts by the number of comments
    @Test
    public void sortPostsByNumberOfComments(){
        driver.navigate().refresh();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buzzPage.sortPosts("Most Commented Posts");
        Assert.assertTrue(buzzPage.arePostsSortedByNumberOfComments());
    }

    //checking if the user can sort posts by the date
    @Test
    public void sortPostsByDate(){
        buzzPage.sortPosts("Most Recent Posts");
        try {
            Assert.assertTrue(buzzPage.arePostsSortedByTheDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
