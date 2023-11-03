import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Helper {
    private WebDriver driver;
    private WebDriverWait wait;

    public Helper() {

    }

    public Helper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    //method for login
    public void login(String username, String password) {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-login-button")));
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.className("orangehrm-login-button"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    //method for checking if the url ends with a valid path
    public boolean isUrlValid(String urlEnd) {
        return driver.getCurrentUrl().endsWith(urlEnd);
    }

    //method for navigation through the pages
    public void goToThePage(String elementName) {
        login("Admin", "admin123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-main-menu")));
        List<WebElement> navigationElements = driver.findElements(By.className("oxd-main-menu-item"));
        for (WebElement el : navigationElements) {
            if (el.getText().equals(elementName)) {
                el.click();
                break;
            }
        }
    }

    //getting the error message (invalid credentials)
    public String getLoginErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-alert--error")));
        return driver.findElement(By.className("oxd-alert--error")).getText();
    }

    //getting the empty field error message
    public String getEmptyFieldErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-input-field-error-message")));
        return driver.findElement(By.className("oxd-input-field-error-message")).getText();
    }
}
