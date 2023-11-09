package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.HomePage;

import java.util.ArrayList;
import java.util.List;

public class DashboardPageTests extends BaseTest{
    private DashboardPage dashboardPage;
    private HomePage homePage;
    private List<String> widgetTitlesList = new ArrayList<>();

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        dashboardPage = new DashboardPage(driver, wait, faker);
        homePage = new HomePage(driver, wait, faker);
        widgetTitlesList.add(0, "Time at Work");
        widgetTitlesList.add(1, "My Actions");
        widgetTitlesList.add(2, "Quick Launch");
        widgetTitlesList.add(3, "Buzz Latest Posts");
        widgetTitlesList.add(4, "Employees on Leave Today");
        widgetTitlesList.add(5, "Employee Distribution by Sub Unit");
        widgetTitlesList.add(6, "Employee Distribution by Location");
    }

    @BeforeMethod
    public void beforeMethod(){
        super.beforeMethod();
        loginPage.login(loginPage.getValidUsername(), loginPage.getValidPassword());
        homePage.goToThePage("Dashboard");
    }

    //checking if all the widgets are displayed on dashboard page
    @Test
    public void areDashboardWidgetsDisplayed(){
        dashboardPage.areDashboardElementsDisplayed(widgetTitlesList);
    }
}
