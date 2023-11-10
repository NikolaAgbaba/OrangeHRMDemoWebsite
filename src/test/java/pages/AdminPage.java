package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdminPage extends BasePage{

    public AdminPage(WebDriver driver, WebDriverWait wait, Faker faker){
        super(driver, wait, faker);
    }

    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    private List<WebElement> topNavButtonsList;

    @FindBy(className = "oxd-dropdown-menu")
    private WebElement dropdownMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a")
    private WebElement jobTitlesButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[2]/a")
    private WebElement payGradesButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[3]/a")
    private WebElement employmentStatusButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[4]/a")
    private WebElement jobCategoriesButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[5]/a")
    private WebElement workShiftsButton;

    @FindBy(className = "orangehrm-container")
    private WebElement jobsContainer;

    @FindBy(className = "oxd-table-card")
    private List<WebElement> jobsList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div/div/div[2]")
    private List<WebElement> jobTitlesList;

    @FindBy(className = "oxd-button-icon")
    private WebElement addJobButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")
    private WebElement jobTitleInputField;

    @FindBy(className = "orangehrm-left-space")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")
    private WebElement savedMessage;

    @FindBy(className = "bi-trash")
    private WebElement deleteJobButton;

    @FindBy(className = "bi-pencil-fill")
    private WebElement editJobButton;

    @FindBy(className = "oxd-button--label-danger")
    private WebElement deleteConfirmationButton;

    //getter for job titles list
    public WebElement getJobsContainer() {
        return jobsContainer;
    }

    //method for navigating through the job dropdown menu
    public void jobDropdownNavigation(String navigationButtonName, String dropdownElementName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-topbar-body-nav")));
        for (WebElement el: topNavButtonsList){
            if (el.getText().equals(navigationButtonName)){
                el.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-dropdown-menu")));
                switch (dropdownElementName){
                    case "Job Titles":
                        jobTitlesButton.click();
                        break;
                    case "Pay Grades":
                        payGradesButton.click();
                        break;
                    case "Employment Status":
                        employmentStatusButton.click();
                        break;
                    case "Job Categories":
                        jobCategoriesButton.click();
                        break;
                    case "Work Shifts":
                        workShiftsButton.click();
                        break;
                }
                break;
            }
        }
    }

    //method for adding a job title
    public void addAJobTitle(String jobTitle){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-button-icon")));
        addJobButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")));
        jobTitleInputField.sendKeys(jobTitle);
        saveButton.click();
    }

//    public String getJobTitleInputFieldValue(){
//        return jobTitleInputField.getAttribute("value");
//    }

    //method for checking if the job is present in the jobs list
    public boolean isJobPresent(String jobTitle){
        boolean jobTitleIsPresent = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
        for (WebElement el: jobTitlesList){
            if (el.getText().equals(jobTitle)){
                jobTitleIsPresent = true;
                break;
            }
        }
        return jobTitleIsPresent;
    }

    //method for deleting the job
    public void deleteTheJob(String jobName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
        for (WebElement el: jobsList){
            if (el.getText().contains(jobName)){
                el.findElement(By.className("bi-trash")).click();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-button--label-danger")));
        deleteConfirmationButton.click();
    }

    //method for editing the job
    public void editTheJob(String jobName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
        for (WebElement el: jobsList){
            if (el.getText().contains(jobName)){
                el.findElement(By.className("bi-pencil-fill")).click();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")));
        jobTitleInputField.sendKeys(" - edited job");
        saveButton.click();
    }

    //getting the message text
    public String getMessageText(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")));
        return savedMessage.getText();
    }
}
