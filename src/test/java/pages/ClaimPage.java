package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ClaimPage extends BasePage{
    public ClaimPage(WebDriver driver, WebDriverWait wait, Faker faker){
        super(driver, wait, faker);
    }

    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    private List<WebElement> topNavButtonsList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[1]")
    private WebElement eventsButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[2]")
    private WebElement expenseTypesButton;

    @FindBy(className = "oxd-table-card")
    private List<WebElement> expenseTypesList;

    @FindBy(className = "oxd-button--label-danger")
    private WebElement deleteSelectedButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[2]/div[1]/button")
    private WebElement addButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[1]/div/div[2]/input")
    private WebElement expenseTypeNameInputField;

    @FindBy(className = "oxd-switch-input")
    private WebElement switchActiveStatusToggleButton;

    @FindBy(className = "orangehrm-left-space")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")
    private WebElement deleteConfirmationButton;

    @FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")
    private WebElement message;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[2]/div[3]/div/div[2]/div/div/div[2]/div")
    private List<WebElement> expenseTypesNames;


    //method for navigation through the configuration dropdown
    public void configurationDropdownNavigation(String navigationButtonName, String dropdownElementName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-topbar-body-nav")));
        for (WebElement el: topNavButtonsList){
            if (el.getText().equals(navigationButtonName)){
                el.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-dropdown-menu")));
                switch (dropdownElementName){
                    case "Events":
                        eventsButton.click();
                        break;
                    case "Expense Types":
                        expenseTypesButton.click();
                        break;
                }
            }
            break;
        }
    }

    //method for adding expense type
    public void addExpenseType(String expenseTypeName){
        int randomNumber = (int) (Math.random() * 2) + 1;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
        addButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-left-space")));
        expenseTypeNameInputField.sendKeys(expenseTypeName);
        if (randomNumber == 2){
            switchActiveStatusToggleButton.click();
        }
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
    }

    //method for deleting multiple expense types
    public void deleteMultipleExpenseTypes(int numberOfExpenseTypes){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
//        if (numberOfExpenseTypes > expenseTypesList.size()){
//            for (int i = 0; i < numberOfExpenseTypes; i++){
//                addExpenseType("Random expense type " + i);
//            }
//        }
        for (int i = 0; i < numberOfExpenseTypes; i++){
            expenseTypesList.get(i).findElement(By.className("bi-check")).click();
        }
        deleteSelectedButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")));
        deleteConfirmationButton.click();
    }

    //method for getting the success message
    public String getMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")));
        return message.getText();
    }

    //method for checking if deleted expense types present in the expense types list
    public boolean areExpenseTypesDeleted(int numberOfExpenseTypes){
        boolean expenseTypesArePresent = false;
        List<String> expenseTypesNames = new ArrayList<>();
        List<String> expenseTypesNamesAfterDeleting = new ArrayList<>();
        List<String> deletedExpenseTypes = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
//        for (int i = 0; i < this.expenseTypesNames.size(); i++){
//            expenseTypesNames.add(this.expenseTypesNames.get(i).getText());
//        }
//        System.out.println("Expense types names: " + expenseTypesNames);
        if (numberOfExpenseTypes > expenseTypesList.size()){
            for (int i = 0; i < numberOfExpenseTypes; i++){
                addExpenseType("Random expense type " + faker.name().fullName());
            }
        }
        for (int i = 0; i < this.expenseTypesNames.size(); i++){
            expenseTypesNames.add(this.expenseTypesNames.get(i).getText());
        }
//        System.out.println("Expense types names: " + expenseTypesNames);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < numberOfExpenseTypes; i++){
//            System.out.println("Expenses to delete " + numberOfExpenseTypes);
//            System.out.println("Size " + expenseTypesNames.size());
            deletedExpenseTypes.add(expenseTypesNames.get(i));
        }
//        System.out.println("Deleted expense types: " + deletedExpenseTypes);
        deleteMultipleExpenseTypes(numberOfExpenseTypes);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-container")));
        for (int i = 0; i < this.expenseTypesNames.size(); i++){
            expenseTypesNamesAfterDeleting.add(this.expenseTypesNames.get(i).getText());
        }
//        System.out.println("List after deleting: " + expenseTypesNamesAfterDeleting);
        for (int i = 0; i < expenseTypesNamesAfterDeleting.size(); i++){
            for (int j = 0; j < deletedExpenseTypes.size(); j++){
                if (expenseTypesNamesAfterDeleting.get(i).equals(deletedExpenseTypes.get(j))){
                    expenseTypesArePresent = true;
                    break;
                }
            }
        }
        return expenseTypesArePresent;
    }

}
