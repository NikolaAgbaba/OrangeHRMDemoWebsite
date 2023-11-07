package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PIMPage extends BasePage {

    public PIMPage(WebDriver driver, WebDriverWait wait, Faker faker) {
        super(driver, wait, faker);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")
    private WebElement employeeNameInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input")
    private WebElement employeeIdInputField;

    @FindBy(className = "oxd-button-icon")
    private WebElement addEmployeeButton;

    @FindBy(className = "oxd-table-card")
    private List<WebElement> employeesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[3]")
    private List<WebElement> employeeFirstNamesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[4]")
    private List<WebElement> employeeLastNamesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[2]")
    private List<WebElement> employeeIdsList;

    @FindBy(className = "bi-trash")
    private WebElement deleteButton;

    @FindBy(className = "orangehrm-left-space")
    private WebElement searchButton;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input")
    private WebElement idInputField;

    @FindBy(className = "orangehrm-left-space")
    private WebElement saveButton;

    @FindBy(className = "oxd-switch-input")
    private WebElement createLoginDetailsToggleButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")
    private WebElement savedMessage;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/a")
    private WebElement employeeListNavButton;

    @FindBy(className = "oxd-button--label-danger")
    private WebElement deleteConfirmationButton;

    //getter for employees list
    public List<WebElement> getEmployeesList() {
        return employeesList;
    }

    //method for returning the list of all employees
    public List<String> employeesFullNamesList() {
        List<String> employeeNames = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[3]")));
        for (int i = 0; i < employeeFirstNamesList.size(); i++) {
            employeeNames.add(employeeFirstNamesList.get(i).getText() + " " + employeeLastNamesList.get(i).getText());
        }
        return employeeNames;
    }

    //method for searching the employee by employee name and checking if that employee is present on the list
    public boolean searchRandomEmployeeByName() {
        String employeeName = "";
        boolean isEmployeePresent = false;
        int desiredEmployee = (int)(Math.random() * (employeeFirstNamesList.size() - 1) + 1);
        for (int i = 0; i < employeesFullNamesList().size(); i++){
            employeeName = employeesFullNamesList().get(desiredEmployee);
            break;
        }
        employeeNameInputField.sendKeys(employeeName, Keys.ARROW_DOWN, Keys.ENTER);
//        employeeNameInputField.sendKeys(Keys.ARROW_DOWN);
//        employeeNameInputField.sendKeys(Keys.ENTER);
        searchButton.click();
        for (String st: employeesFullNamesList()){
            if (st.contains(employeeName)){
                isEmployeePresent = true;
            }
        }
        return isEmployeePresent;
    }

    //method for searching the employee by employee id and checking if that employee is present on the list
    public boolean searchRandomEmployeeById() {
        String employeeId = "";
        boolean isEmployeePresent = false;
        int desiredEmployee = (int)(Math.random() * (employeeIdsList.size() - 1) + 1);
        for (int i = 0; i < employeeIdsList.size(); i++){
            employeeId = employeeIdsList.get(desiredEmployee).getText();
            break;
        }
        employeeIdInputField.sendKeys(employeeId);
        searchButton.click();
        for (WebElement el: employeeIdsList){
            if (el.getText().equals(employeeId)){
                isEmployeePresent = true;
            }
        }
        return isEmployeePresent;
    }

    //method for adding the new employee
    public void addEmployee(String firstName, String lastName, String id){
        addEmployeeButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-card-container")));
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        idInputField.clear();
        idInputField.sendKeys(id);
        saveButton.click();
    }

    //method for adding the new employee and creating the login credentials for that employee
    public void addEmployeeAndCreateLoginCredentials(String firstName, String lastName, String id, String username, String password, String confirmPassword) {
        addEmployeeButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-card-container")));
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        idInputField.clear();
        idInputField.sendKeys(id);
        createLoginDetailsToggleButton.click();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        saveButton.click();
    }

    //method for getting the message for created or deleted employee
    public String getMessageText(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")));
        return savedMessage.getText();
    }

    //method for searching the specific employee
    public boolean searchSpecificEmployeeByName(String employeeName){
        boolean isEmployeePresent = false;
        employeeListNavButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
        employeeNameInputField.sendKeys(employeeName, Keys.ARROW_DOWN, Keys.ENTER);
//        employeeNameInputField.sendKeys(Keys.ARROW_DOWN);
//        employeeNameInputField.sendKeys(Keys.ENTER);
        searchButton.click();
        for (String st: employeesFullNamesList()){
            if (st.contains(employeeName)){
                isEmployeePresent = true;
            }
        }
        return isEmployeePresent;
    }

    //method for deleting the employee
    public void deleteTheEmployee(){
        searchRandomEmployeeById();
        deleteButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-button--label-danger")));
        deleteConfirmationButton.click();
    }
}
