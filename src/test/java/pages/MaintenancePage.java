package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MaintenancePage extends BasePage {

    public MaintenancePage(WebDriver driver, WebDriverWait wait, Faker faker) {
        super(driver, wait, faker);
    }

    @FindBy(className = "orangehrm-admin-access-container")
    private WebElement adminAccessContainer;

    @FindBy(name = "password")
    private WebElement passwordInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/form/div[4]/button[2]")
    private WebElement confirmButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]")
    private WebElement note;

    //method for checking if the admin access form is displayed
    public boolean isAdminAccessFormIsDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-admin-access-container")));
        return adminAccessContainer.isDisplayed();
    }

    //method for completing the additional authentication
    public void completeAuthenticationStep(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-admin-access-container")));
        passwordInputField.sendKeys(password);
        confirmButton.click();
    }

    //method for checking if the note is displayed on the maintenance page
    public boolean isTheNoteDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]")));
        return note.isDisplayed();
    }
}
