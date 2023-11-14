package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PerformancePage extends BasePage {

    public PerformancePage(WebDriver driver, WebDriverWait wait, Faker faker) {
        super(driver, wait, faker);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/span")
    private WebElement configureNavigationDropdownButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/span")
    private WebElement manageReviewsNavigationDropdownButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]/a")
    private WebElement myTrackersNavigationButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[4]/a")
    private WebElement employeeTrackersNavigationButton;

    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    private List<WebElement> navigationButtonsList;

    @FindBy(className = "bi-caret-up-fill")
    private WebElement collapseButton;

    @FindBy(className = "bi-caret-down-fill")
    private WebElement expandButton;

    @FindBy(className = "oxd-form-row")
    private WebElement form;

    @FindBy(className = "oxd-table-card")
    private List<WebElement> employeeTrackersList;

    @FindBy(name = "view")
    private WebElement viewButton;

    @FindBy(className = "orangehrm-container")
    private WebElement trackerLogContainer;

    @FindBy(className = "oxd-button--secondary")
    private WebElement addLogButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[1]/div/div[2]/input")
    private WebElement logNameInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[2]/div/button[1]")
    private WebElement positiveLogButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[2]/div/button[2]")
    private WebElement negativeLogButton;

    @FindBy(className = "oxd-textarea")
    private WebElement commentInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[4]/button[2]")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")
    private WebElement savedMessage;

    @FindBy(className = "orangehrm-employee-tracker-log-title-text")
    private List<WebElement> trackerLogsHeadings;

    //method for navigating through the performance page
    public void navigateThroughThePerformancePage(String desiredPage) {
        for (WebElement el : navigationButtonsList) {
            if (el.getText().equals(desiredPage)) {
                el.click();
                break;
            }
        }
    }

    //method for hiding the employee tracker filters
    public void hideFilters() {
        navigateThroughThePerformancePage("Employee Trackers");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bi-caret-up-fill")));
        collapseButton.click();
    }

    //method for showing the employee tracker filters
    public void showFilters() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bi-caret-down-fill")));
        expandButton.click();
    }

    //getting the form
    public WebElement getForm() {
        return form;
    }

    //method for viewing the employees performance
    public void viewRandomEmployeePerformance() {
        navigateThroughThePerformancePage("Employee Trackers");
        int employeeNumber = (int) (Math.random() * (employeeTrackersList.size() - 2) + 1);
        for (int i = 0; i < employeeTrackersList.size(); i++) {
            employeeTrackersList.get(employeeNumber).findElement(By.name("view")).click();
            break;
        }
    }

    //method for checking if the tracker log page is displayed
    public boolean isPerformanceTrackerPageDisplayed() {
        navigateThroughThePerformancePage("Employee Trackers");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
        return trackerLogContainer.isDisplayed();
    }

    //method for adding the performance log
    public void addPerformanceLog(String logName, String comment) {
        int randomNumber = (int) (Math.random() * 2) + 1;
        navigateThroughThePerformancePage("Employee Trackers");
        viewRandomEmployeePerformance();
        addLogButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-sheet")));
        logNameInputField.sendKeys(logName);
        if (randomNumber == 1) {
            positiveLogButton.click();
        } else if (randomNumber == 2) {
            negativeLogButton.click();
        }
        commentInputField.sendKeys(comment);
        saveButton.click();
    }

    //method for getting the message text
    public String getMessageText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")));
        return savedMessage.getText();
    }

    //method for checking if the tracker log is present in the tracker logs list
    public boolean isLogPresent(String logName) {
        boolean logIsPresent = false;
        for (WebElement el : trackerLogsHeadings) {
            if (el.getText().equals(logName)) {
                logIsPresent = true;
                break;
            }
        }
        return logIsPresent;
    }

}



