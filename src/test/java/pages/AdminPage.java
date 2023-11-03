package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdminPage extends BasePage{

    public AdminPage(WebDriver driver, WebDriverWait wait, Faker faker){
        super(driver, wait, faker);
    }

    @FindBy(className = "oxd-topbar-body-nav-tab-item")
    List<WebElement> topNavButtonsList;

    @FindBy(className = "oxd-dropdown-menu")
    private WebElement dropdownMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a")
    private WebElement jobTitlesButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[2]/a")
    private WebElement payGradesButton;


    //method for going to the job titles
    public void goToTheJobTitles(String navigationButtonName, String dropdownElementName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-topbar-body-nav")));
        for (WebElement el: topNavButtonsList){
            if (el.getText().equals(navigationButtonName)){
                el.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-dropdown-menu")));
                switch (dropdownElementName){
                    case "Job Titles":
                        jobTitlesButton.click();
                        break;
                    case "Pay Grades":
                        payGradesButton.click();
                }
                break;
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
