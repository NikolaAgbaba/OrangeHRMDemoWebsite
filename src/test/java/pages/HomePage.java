package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver, WebDriverWait wait, Faker faker){
        super(driver, wait, faker);
    }

    @FindBy(className = "oxd-main-menu-item")
    private List<WebElement> navigationItems;

    @FindBy(className = "oxd-userdropdown-name")
    private WebElement nameDropdown;

    @FindBy(xpath = "//a[@href='/web/index.php/auth/logout']")
    private WebElement logoutButton;

    //method for navigating through the pages
    public void goToThePage(String elementName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-main-menu")));
        for (WebElement el: navigationItems) {
            if (el.getText().equals(elementName)) {
                el.click();
                break;
            }
        }
    }

    //method for logging out
    public void logout(){
        nameDropdown.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/web/index.php/auth/logout']")));
        logoutButton.click();
    }
}
