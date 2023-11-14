package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver, WebDriverWait wait, Faker faker) {
        super(driver, wait, faker);
    }

    @FindBy(className = "oxd-sheet--rounded")
    private List<WebElement> dashboardWidgets;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/p")
    private WebElement timeAtWorkHeader;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div/div[1]/div/p")
    private WebElement myActionsHeader;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[1]/div/p")
    private WebElement quickLaunchHeader;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[4]/div/div[1]/div/p")
    private WebElement buzzLatestPostsHeader;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[5]/div/div[1]/div/p")
    private WebElement employeesOnLeaveHeader;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[6]/div/div[1]/div/p")
    private WebElement employeeDistributionSubUnitHeader;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[7]/div/div[1]/div/p")
    private WebElement employeeDistributionLocationHeader;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div/p")
    private List<WebElement> headersList;


    //method for checking if the dashboard widgets are displayed
    public boolean areDashboardElementsDisplayed(List<String> widgetsTitlesList) {
        boolean areDisplayed = true;
        for (int i = 0; i < dashboardWidgets.size(); i++) {
            if (!dashboardWidgets.get(i).getText().contains(widgetsTitlesList.get(i))) {
                areDisplayed = false;
                break;
            }
        }
        return areDisplayed;
    }
}
