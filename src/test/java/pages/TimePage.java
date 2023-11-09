package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TimePage extends BasePage{

    public TimePage(WebDriver driver, WebDriverWait wait, Faker faker){
        super(driver, wait, faker);
    }

    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topNavButtonsList;

    @FindBy(className = "oxd-dropdown-menu")
    private WebElement dropdownMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[1]/a")
    private WebElement myTimesheetsDropdownButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[2]/a")
    private WebElement employeeTimesheetsDropdownButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[4]/ul/li[1]/a")
    private WebElement customersDropdownButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[4]/ul/li[2]/a")
    private WebElement projectsDropdownButon;

    @FindBy(className = "--prev")
    private WebElement leftNavigationButton;

    @FindBy(className = "--next")
    private WebElement rightNavigationButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/button")
    private WebElement everyButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/button[1]")
    private WebElement editCreatedButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/button[2]")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/button[3]")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[2]")
    private List<WebElement> projectNamesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[1]/div/div[2]/div/div/input")
    private WebElement projectNameInputField;

    @FindBy(className = "oxd-select-text-input")
    private WebElement activityDropdownMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td/div/div[2]/input")
    private List<WebElement> datesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[1]/span")
    private WebElement savedProjectValue;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[2]/span")
    private WebElement savedActivityValue;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[3]/span")
    private WebElement savedMondayValue;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[4]/span")
    private WebElement savedTuesdayValue;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[5]/span")
    private WebElement savedWednesdayValue;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[6]/span")
    private WebElement savedThursdayValue;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[7]/span")
    private WebElement savedFridayValue;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[8]/span")
    private WebElement savedSaturdayValue;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[9]/span")
    private WebElement savedSundayValue;

    @FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")
    private WebElement savedMessage;

    @FindBy(className = "orangehrm-timesheet-table-body-row")
    private WebElement tableRow;


    //method for navigating through the timesheets dropdown menu
    public void timesheetsDropdownNavigation(String navigationButtonName, String dropdownElementName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-topbar-body-nav")));
        for (WebElement el: topNavButtonsList){
            if (el.getText().equals(navigationButtonName)){
                el.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-dropdown-menu")));
                switch (dropdownElementName){
                    case "My Timesheets":
                        myTimesheetsDropdownButton.click();
                        break;
                    case "Employee Timesheets":
                        employeeTimesheetsDropdownButton.click();
                        break;
                }
                break;
            }
        }
    }

    //method for navigating through the projects dropdown menu
    public void projectsDropdownNavigation(String navigationButtonName, String dropdownElementName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-topbar-body-nav")));
        for (WebElement el: topNavButtonsList){
            if (el.getText().equals(navigationButtonName)){
                el.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-dropdown-menu")));
                switch (dropdownElementName){
                    case "Customers":
                        customersDropdownButton.click();
                        break;
                    case "Projects":
                        projectsDropdownButon.click();
                        break;
                }
                break;
            }
        }
    }

    //method for getting a random project name
    public String getARandomProjectName(){
        String projectName = "";
        int desiredProject = (int)(Math.random() * (projectNamesList.size() - 2) + 1);
        for (int i = 0; i < projectNamesList.size(); i++){
            projectName = projectNamesList.get(desiredProject).getText();
        }
        return projectName;
    }

    //method for creating a timesheet without saving it (in order to be able to get the input values)
    public void createATimeSheetWithoutSaving(){
        projectsDropdownNavigation("Project Info", "Projects");
        String projectName = getARandomProjectName();
        timesheetsDropdownNavigation("Timesheets", "My Timesheets");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(!everyButton.getText().contains("Create Timesheet")) {
            leftNavigationButton.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        everyButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/button[1]")));
        editCreatedButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[1]/div/div[2]/div/div/input")));
        fillTheTimeSheet(projectName);
    }

    //method for editing a timesheet without saving it (in order to be able to get the input values)
    public void editATimeSheetWithoutSaving(){
        projectsDropdownNavigation("Project Info", "Projects");
        String projectName = getARandomProjectName();
        timesheetsDropdownNavigation("Timesheets", "My Timesheets");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(!tableRow.isDisplayed()) {
            leftNavigationButton.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        editCreatedButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[2]/table/tbody/tr[1]/td[1]/div/div[2]/div/div/input")));
        fillTheTimeSheet(projectName);
    }

    //method for filling the timesheet
    public void fillTheTimeSheet(String projectName){
        projectNameInputField.sendKeys(Keys.CONTROL + "a");
        projectNameInputField.sendKeys(projectName);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        projectNameInputField.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        activityDropdownMenu.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        activityDropdownMenu.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        for (WebElement el: datesList){
            int hours = (int)(Math.random() * 24);
            el.click();
            el.sendKeys(Keys.CONTROL + "a");
            if (hours >= 10) {
                el.sendKeys(hours + ":00");
            }else{
                el.sendKeys("0" +hours + ":00");
            }
        }
    }

    //method for saving a timesheet
    public void saveACreatedTimeSheet(){
        saveButton.click();
    }

    //method for getting the entered project value
    public String getProjectEnteredValue(){
        return projectNameInputField.getAttribute("value");
    }

    //method for getting the entered activity value
    public String getActivityEnteredValue(){
        return activityDropdownMenu.getText();
    }

    //method for getting the entered monday value
    public String getMondayEnteredValue(){
        return datesList.get(0).getAttribute("value");
    }

    //method for getting the entered tuesday value
    public String getTuesdayEnteredValue(){
        return datesList.get(1).getAttribute("value");
    }

    //method for getting the entered wednesday value
    public String getWednesdayEnteredValue(){
        return datesList.get(2).getAttribute("value");
    }

    //method for getting the entered thursday value
    public String getThursdayEnteredValue(){
        return datesList.get(3).getAttribute("value");
    }

    //method for getting the entered friday value
    public String getFridayEnteredValue(){
        return datesList.get(4).getAttribute("value");
    }

    //method for getting the entered saturday value
    public String getSaturdayEnteredValue(){
        return datesList.get(5).getAttribute("value");
    }

    //method for getting the entered sunday value
    public String getSundayEnteredValue(){
        return datesList.get(6).getAttribute("value");
    }

    //method for getting the saved project value
    public String getSavedProjectValue(){
        return savedProjectValue.getText();
    }

    //method for getting the saved activity value
    public String getSavedActivityValue(){
        return savedActivityValue.getText();
    }

    //method for getting the saved monday value
    public String getSavedMondayValue(){
        return savedMondayValue.getText();
    }

    //method for getting the saved tuesday value
    public String getSavedTuesdayValue(){
        return savedTuesdayValue.getText();
    }

    //method for getting the saved wednesday value
    public String getSavedWednesdayValue(){
        return savedWednesdayValue.getText();
    }

    //method for getting the saved thursday value
    public String getSavedThursdayValue(){
        return savedThursdayValue.getText();
    }

    //method for getting the saved friday value
    public String getSavedFridayValue(){
        return savedFridayValue.getText();
    }

    //method for getting the saved saturday value
    public String getSavedSaturdayValue(){
        return savedSaturdayValue.getText();
    }

    //method for getting the saved sunday value
    public String getSavedSundayValue(){
        return savedSundayValue.getText();
    }

    //method comparing the entered data and data in the saved timesheet
    public boolean verifyTimesheetValues(String createOrEditTheTimesheet){
        boolean timesheetValues = true;
        if (createOrEditTheTimesheet.equals("Create a timesheet")) {
            createATimeSheetWithoutSaving();
        }else if(createOrEditTheTimesheet.equals("Edit a timesheet")){
            editATimeSheetWithoutSaving();
        }
        String enteredProject = getProjectEnteredValue();
        String enteredActivity = getActivityEnteredValue();
        String enteredMondayValue = getMondayEnteredValue();
        String enteredTuesdayValue = getTuesdayEnteredValue();
        String enteredWednesdayValue = getWednesdayEnteredValue();
        String enteredThursdayValue = getThursdayEnteredValue();
        String enteredFridayValue = getFridayEnteredValue();
        String enteredSaturdayValue = getSaturdayEnteredValue();
        String enteredSundayValue = getSundayEnteredValue();
        saveACreatedTimeSheet();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String savedProject = getSavedProjectValue();
        String savedActivity = getSavedActivityValue();
        String savedMondayValue = getSavedMondayValue();
        String savedTuesdayValue = getSavedTuesdayValue();
        String savedWednesdayValue = getSavedWednesdayValue();
        String savedThursdayValue = getSavedThursdayValue();
        String savedFridayValue = getSavedFridayValue();
        String savedSaturdayValue = getSavedSaturdayValue();
        String savedSundayValue = getSavedSundayValue();
        if (!enteredProject.equals(savedProject) || !enteredActivity.equals(savedActivity) || !enteredMondayValue.equals(savedMondayValue) ||
        !enteredTuesdayValue.equals(savedTuesdayValue) || !enteredWednesdayValue.equals(savedWednesdayValue) || !enteredThursdayValue.equals(savedThursdayValue) ||
        !enteredFridayValue.equals(savedFridayValue) || !enteredSaturdayValue.equals(savedSaturdayValue) || !enteredSundayValue.equals(savedSundayValue)){
            timesheetValues = false;
        }
        return timesheetValues;
    }

}
