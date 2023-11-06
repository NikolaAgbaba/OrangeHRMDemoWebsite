package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Faker faker;

    public BasePage(){

    }

    public BasePage(WebDriver driver, WebDriverWait wait, Faker faker){
        this.driver = driver;
        this.wait = wait;
        this.faker = faker;
        PageFactory.initElements(this.driver, this);
    }
}
