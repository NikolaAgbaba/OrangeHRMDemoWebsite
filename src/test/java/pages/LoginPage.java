package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver, WebDriverWait wait, Faker faker){
        super(driver, wait, faker);
    }

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(className = "orangehrm-login-button")
    private WebElement loginButton;

    @FindBy(className = "oxd-alert--error")
    private WebElement invalidCredentialsMessage;

    @FindBy(className = "oxd-input-field-error-message")
    private WebElement emptyFieldErrorMessage;

    //method for login
    public void login(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }


    //method for checking if the message for invalid credentials is valid
    public boolean isInvalidCredentialsMessageValid(String invalidCredentialsMessage){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-alert--error")));
        return this.invalidCredentialsMessage.getText().equals(invalidCredentialsMessage);
    }

    //method for checking if the message for empty fields is valid
    public boolean isEmptyFieldErrorMessageValid(String requiredFieldMessage){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-input-field-error-message")));
        return emptyFieldErrorMessage.getText().equals(requiredFieldMessage);
    }
}
