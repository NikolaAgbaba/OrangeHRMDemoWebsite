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

public class RecruitmentPage extends BasePage{

    public RecruitmentPage(WebDriver driver, WebDriverWait wait, Faker faker){
        super(driver, wait, faker);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]")
    private WebElement vacanciesNavigationButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]")
    private WebElement candidatesNavigationButton;

    @FindBy(className = "oxd-table-card")
    private List<WebElement> vacanciesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[2]/div")
    private List<WebElement> vacanciesNamesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[5]/div")
    private List<WebElement> vacanciesStatusList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]")
    private WebElement vacanciesStatusDropdown;

    @FindBy(className = "orangehrm-left-space")
    private WebElement searchButton;

    @FindBy(className = "oxd-button--ghost")
    private WebElement resetFilters;

    @FindBy(className = "oxd-button--label-danger")
    private WebElement deleteConfirmationButton;

    @FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")
    private WebElement message;

    //method for navigating through the recruitment pages
    public void navigateThroughTheRecruitmentPages(String desiredPage){
        if (desiredPage.equals("Candidates")){
            candidatesNavigationButton.click();
        }else if (desiredPage.equals("Vacancies")){
            vacanciesNavigationButton.click();
        }
    }

    //method for deleting the vacancy
    public void deleteTheVacancy(String vacancyName){
        navigateThroughTheRecruitmentPages("Vacancies");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-body")));
        for (WebElement el: vacanciesList){
            if (el.getText().contains(vacancyName)){
                el.findElement(By.className("bi-trash")).click();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-button--label-danger")));
        deleteConfirmationButton.click();
    }

    //method for checking if the vacancy name is present in the vacancies list
    public boolean isVacancyPresent(String vacancyName){
        boolean isVacancyPresent = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
        for (WebElement el: vacanciesNamesList){
            if (el.getText().equals(vacancyName)){
                isVacancyPresent = true;
                break;
            }
        }
        return isVacancyPresent;
    }

    //method for getting a random vacancy name
    public String getRandomVacancyName(){
        navigateThroughTheRecruitmentPages("Vacancies");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
        int randomVacancyNum = (int)(Math.random() * (vacanciesNamesList.size() - 2) + 1);
        String randomVacancy = "";
        for (int i = 0; i < vacanciesNamesList.size(); i++){
            randomVacancy = vacanciesNamesList.get(randomVacancyNum).getText();
            break;
        }
        return randomVacancy;
    }

    //method for getting a random vacancy status
    public String getRandomVacancyStatus(){
        navigateThroughTheRecruitmentPages("Vacancies");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
        int randomVacancyNum = (int)(Math.random() * (vacanciesStatusList.size() - 2) + 1);
        String randomStatus = "";
        for (int i = 0; i < vacanciesStatusList.size(); i++) {
            randomStatus = vacanciesStatusList.get(randomVacancyNum).getText();
            break;
        }
        return randomStatus;
    }

    //method for checking if all the vacancies have required status
    public void filterVacanciesByStatus(String vacancyStatus){
//        boolean isVacancyPresent = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-table-card")));
        switch (vacancyStatus){
            case "Active":
                vacanciesStatusDropdown.click();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                vacanciesStatusDropdown.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
                searchButton.click();
                break;
            case "Closed":
                vacanciesStatusDropdown.click();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                vacanciesStatusDropdown.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
                searchButton.click();
        }
//        for (WebElement el: vacanciesStatusList){
//            if (el.getText().equals(vacancyStatus)){
//                isVacancyPresent = true;
//                break;
//            }
//        }
//        return isVacancyPresent;
    }

    //checking the vacancies status filter
    public String getVacanciesStatusFilter(){
        return vacanciesStatusDropdown.getText();
    }

    //resetting the vacancies status filter
    public void resetFilters(){
        resetFilters.click();
    }

    //method for checking if all the vacancies have required status
    public boolean areVacanciesFilteredByStatus(String vacancyStatus){
        boolean isVacancyPresent = false;
        filterVacanciesByStatus(vacancyStatus);
        for (WebElement el: vacanciesStatusList){
            if (el.getText().equals(vacancyStatus)){
                isVacancyPresent = true;
                break;
            }
        }
        return isVacancyPresent;
    }

    //method for getting the message
    public String getTheMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")));
        return message.getText();
    }
}
