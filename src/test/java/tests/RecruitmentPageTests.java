package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.RecruitmentPage;

public class RecruitmentPageTests extends BaseTest {
    private RecruitmentPage recruitmentPage;
    private HomePage homePage;
    private String expectedMessage = "Success";

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        recruitmentPage = new RecruitmentPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Recruitment");
    }

    @Test
    public void deletingTheVacancy() {
        String vacancyName = recruitmentPage.getRandomVacancyName();
        recruitmentPage.deleteTheVacancy(vacancyName);
        softAssert.assertEquals(recruitmentPage.getTheMessage(), expectedMessage);
        softAssert.assertTrue(!recruitmentPage.isVacancyPresent(vacancyName));
        softAssert.assertAll();
    }

    @Test
    public void filterTheVacancyByStatus() {
        String vacancyStatus = recruitmentPage.getRandomVacancyStatus();
        Assert.assertTrue(recruitmentPage.areVacanciesFilteredByStatus(vacancyStatus));
    }

    @Test
    public void resetVacanciesFilter() {
        String vacancyStatus = recruitmentPage.getRandomVacancyStatus();
        recruitmentPage.filterVacanciesByStatus(vacancyStatus);
        softAssert.assertEquals(recruitmentPage.getVacanciesStatusFilter(), recruitmentPage.getRandomVacancyStatus());
        recruitmentPage.resetFilters();
        softAssert.assertEquals(recruitmentPage.getVacanciesStatusFilter(), "-- Select --");
        softAssert.assertAll();
    }
}
