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
import java.util.Set;

public class MyInfoPage extends BasePage {

    public MyInfoPage(WebDriver driver, WebDriverWait wait, Faker faker) {
        super(driver, wait, faker);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div/a")
    private List<WebElement> myInfoNavigationButtons;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div[3]/div/div[2]/input")
    private WebElement cityInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div/div[2]/div/div[2]/input")
    private WebElement mobileInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div/div[1]/div/div[2]/input")
    private WebElement workEmailInputField;

    @FindBy(className = "orangehrm-left-space")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")
    private WebElement savedMessage;

    @FindBy(xpath = "//button[@title='Help']")
    private WebElement helpButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/button")
    private WebElement addTheEmergencyContactButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div")
    private WebElement emergencyContactsTable;

    @FindBy(className = "oxd-button--ghost")
    private WebElement cancelButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div[1]/div/div[2]/input")
    private WebElement contactNameInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div[2]/div/div[2]/input")
    private WebElement contactRelationshipInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div/div[1]/div/div[2]/input")
    private WebElement homeTelephoneInputField;


    //method for navigating through the my info pages
    public void navigateThroughMyInfoPages(String page) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-tabs")));
        for (WebElement el : myInfoNavigationButtons) {
            if (el.getText().equals(page)) {
                el.click();
                break;
            }
        }
    }

    //method for changing the contact info for city, mobile and work email
    public void changeContactInfo(String city, String mobile, String workEmail) {
        if (!cityInputField.getAttribute("value").equals("")) {
            cityInputField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        cityInputField.sendKeys(city);
        if (!mobileInputField.getAttribute("value").equals("")) {
            mobileInputField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        mobileInputField.sendKeys(mobile);
        if (!workEmailInputField.getAttribute("value").equals("")) {
            workEmailInputField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        workEmailInputField.sendKeys(workEmail);
        saveButton.click();
    }

    //getting the message
    public String getMessageText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")));
        return savedMessage.getText();
    }

    //getting the city field value
    public String getCity() {
        return cityInputField.getAttribute("value");
    }

    //getting the mobile field value
    public String getMobile() {
        return mobileInputField.getAttribute("value");
    }

    //getting the work email field value
    public String getWorkEmail() {
        return workEmailInputField.getAttribute("value");
    }

    //method for going on the help page
    public void goToTheHelpPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Help']")));
        helpButton.click();
        Set<String> windowHandles = driver.getWindowHandles();
        String originalHandle = driver.getWindowHandle();
        for (String handle : windowHandles) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        wait.until(ExpectedConditions.urlToBe("https://starterhelp.orangehrm.com/hc/en-us/articles/360018590940-How-to-Add-Emergency-Contacts"));
    }

    //method for exiting the help page
    public void exitFromTheHelpPage() {
        String originalHandle = driver.getWindowHandle();
        driver.switchTo().window(originalHandle);
    }

    //method for filling the emergency contact info
    public void fillEmergencyContactInfo(String name, String relationship, String homeTelephone) {
        addTheEmergencyContactButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-button--ghost")));
        contactNameInputField.sendKeys(name);
        contactRelationshipInputField.sendKeys(relationship);
        homeTelephoneInputField.sendKeys(homeTelephone);
    }

    public String getContactNameValue() {
        return contactNameInputField.getAttribute("value");
    }

    public String getContactRelationshipInputField() {
        return contactRelationshipInputField.getAttribute("value");
    }

    public String getHomeTelephoneInputField() {
        return homeTelephoneInputField.getAttribute("value");
    }

    //method for canceling the emergency contact creation
    public void cancelEmergencyContactCreation() {
        cancelButton.click();
    }

    //method for getting the emergency contact table values
    public String getTableValues() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div")));
        return emergencyContactsTable.getText();
    }
}
