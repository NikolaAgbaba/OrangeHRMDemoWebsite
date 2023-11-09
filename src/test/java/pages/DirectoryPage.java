package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DirectoryPage extends BasePage{
    public DirectoryPage(WebDriver driver, WebDriverWait wait, Faker faker){
        super(driver, wait, faker);
    }

    @FindBy(className = "orangehrm-profile-picture-img")
    private List<WebElement> profilesList;

    @FindBy(className = "orangehrm-corporate-directory-sidebar")
    private WebElement expandedEmployeeCard;

    //method for clicking on a random employee
    public void clickingOnARandomEmployee(){
        int employeeNumber = (int)(Math.random() * profilesList.size());
        for (int i = 0 ; i<profilesList.size(); i++){
            profilesList.get(employeeNumber).click();
            break;
        }
    }

    //method for checking if the expanded profile info is displayed
    public boolean isProfileDisplayed(){
        clickingOnARandomEmployee();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-corporate-directory-sidebar")));
        return expandedEmployeeCard.isDisplayed();
    }
}
