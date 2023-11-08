package pages;

import com.github.javafaker.Faker;
import framework.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    //setting a valid email
    public String getValidUsername(){
        try {
            Configuration.init();
            return Configuration.validUsername;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //getting a valid password
    public String getValidPassword(){
        try {
            Configuration.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Configuration.validPassword;
    }

    //getting the url
    public String getUrl(){
        try {
            Configuration.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Configuration.url;
    }

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

    //method for reading the credentials from a .txt file
    public String readUsername(String path){
        String username = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("username: ")) {
                    username = line.replace("username: ", "");
                    break; // Exit the loop once the password is found
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // Handle any closing errors
            }
        }
        return username;
    }


    public String readPassword(String path) {
        String password = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("password: ")) {
                    password = line.replace("password: ", "");
                    break; // Exit the loop once the password is found
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // Handle any closing errors
            }
        }
        return password;
    }
}
