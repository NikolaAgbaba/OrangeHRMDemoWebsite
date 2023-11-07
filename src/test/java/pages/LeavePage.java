package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LeavePage extends BasePage {
    private SimpleDateFormat dateFormat;

    public LeavePage(SimpleDateFormat dateFormat, WebDriver driver, WebDriverWait wait, Faker faker) {
        super(driver, wait, faker);
        this.dateFormat = dateFormat;
    }

    @FindBy(className = "oxd-table-card")
    private List<WebElement> leaveReqestsList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")
    private WebElement fromDateInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input")
    private WebElement toDateInputField;

    @FindBy(className = "orangehrm-left-space")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div/div/div[2]")
    private List<WebElement> datesList;

    @FindBy(xpath = "//*[@class='oxd-multiselect-chips-area']//*[text()='Pending Approval ']")
    private WebElement pendingApprovalContainer;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div[2]/span[1]/i")
    private WebElement xButtonForPendingApproval;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div[1]/div[1]")
    private WebElement leaveStatusDropdown;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div[2]/span")
    private WebElement selectedStatusFilter;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div/div/div[7]")
    private List<WebElement> leaveStatusList;

    @FindBy(className = "oxd-table-loader")
    private WebElement tableLoader;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div[2]/div/div/div[3]")
    List<WebElement> employeeNamesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[1]/div/div[2]/div/div/input")
    private WebElement employeeNameInputField;

    //method for searching by the date
    public void searchByDate(String fromDateValue, String toDateValue) {
        fromDateInputField.click();
        fromDateInputField.sendKeys(Keys.CONTROL + "a");
        fromDateInputField.sendKeys(fromDateValue);
        toDateInputField.click();
        toDateInputField.sendKeys(Keys.CONTROL + "a");
        toDateInputField.sendKeys(toDateValue);
        searchButton.click();
    }

    //method for printing dates
    public void printDates() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
        for (WebElement el : datesList) {
            System.out.println(el.getText());
        }
    }

    //method for checking if the leaves are being filtered by date
    public boolean filterLeavesByDate(String fromDateValue, String toDateValue) throws ParseException {
        boolean isBeginningDateValid = true;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = dateFormat.parse(fromDateValue);
        Date toDate = dateFormat.parse(toDateValue);
        searchByDate(fromDateValue, toDateValue);
        for (WebElement el : datesList) {
            String fromDateElement = el.getText().substring(0, 10);
            Date oneFromDate = dateFormat.parse(fromDateElement);
            if (oneFromDate.before(fromDate)) {
                isBeginningDateValid = false;
            }
        }
        return isBeginningDateValid;
    }

    //method for searching by the leave status
    public void selectLeaveStatus(String leaveStatus){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-form")));
        xButtonForPendingApproval.click();
        leaveStatusDropdown.click();
        switch (leaveStatus){
            case "Rejected":
                leaveStatusDropdown.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
                break;
            case "Cancelled":
                leaveStatusDropdown.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
                break;
            case "Pending Approval":
                leaveStatusDropdown.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
                break;
            case "Scheduled":
                leaveStatusDropdown.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
                break;
            case "Taken":
                leaveStatusDropdown.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
                break;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        searchButton.click();
    }

    //method for getting the selected filtered status
    public String getSelectedStatus(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div[2]/span")));
        return selectedStatusFilter.getText();
    }

    //method for checking if all the leaves have selected leave status
    public boolean checkLeavesBySelectedStatus(String selectStatus){
        boolean status = true;
        selectLeaveStatus(selectStatus);
        String selectedStatus = getSelectedStatus();
        for (WebElement el: leaveStatusList){
            if (!el.getText().contains(selectedStatus)){
                status = false;
            }
        }
        return status;
    }

    //method for searching by the employee name and checking if only leaves for that employee are present in the list
    public boolean searchByEmployeeName(){
        boolean isEmployeeValid = true;
        String employeeName = "";
        int desiredEmployee = (int)(Math.random() * (employeeNamesList.size() - 2) + 1);
        System.out.println(desiredEmployee);
        for (int i = 0; i < employeeNamesList.size(); i++){
            employeeName = employeeNamesList.get(desiredEmployee).getText();
            break;
        }
        employeeNameInputField.sendKeys(employeeName);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        employeeNameInputField.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-header")));
        for (WebElement el: employeeNamesList){
            if (!el.getText().contains(employeeName)){
                isEmployeeValid = false;
            }
        }
        return isEmployeeValid;
    }
}
