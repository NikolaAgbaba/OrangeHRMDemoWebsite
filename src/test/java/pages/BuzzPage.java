package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class BuzzPage extends BasePage {
    private SimpleDateFormat dateFormat;

    public BuzzPage(WebDriver driver, WebDriverWait wait, Faker faker, SimpleDateFormat dateFormat) {
        super(driver, wait, faker);
        this.dateFormat = dateFormat;
    }

    @FindBy(className = "oxd-buzz-post-input")
    private WebElement postInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement postButton;

    @FindBy(className = "bi-three-dots")
    private List<WebElement> threeDotsButtons;

    @FindBy(className = "oxd-dropdown-menu")
    private WebElement dropdownMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[3]/div[1]/div/div[1]/div/div[2]/li/ul/li[1]")
    private WebElement deleteAPostButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[3]/div[1]/div/div[1]/div/div[2]/li/ul/li[2]")
    private WebElement editAPostButton;

    @FindBy(className = "orangehrm-post-filters-button")
    private List<WebElement> sortButtonsList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[3]/div/div")
    private List<WebElement> postsList;

    @FindBy(className = "orangehrm-buzz-post-body-text")
    private List<WebElement> postsTextList;

    @FindBy(className = "orangehrm-buzz-post-footer")
    private List<WebElement> likesSharesAndCommentsList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[3]/div[1]/div/div[3]/div[2]")
    private WebElement like;

    @FindBy(className = "orangehrm-buzz-post-time")
    private List<WebElement> datesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[3]/div[1]/div/div[2]/div/p[1]")
    private WebElement firstPostText;

    @FindBy(className = "orangehrm-buzz-post-header-config-item")
    private List<WebElement> commandsForPost;

    @FindBy(className = "oxd-button--label-danger")
    private WebElement deleteConfirmationButton;

    @FindBy(xpath = "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")
    private WebElement successMessage;

    @FindBy(xpath = "//*[@style='height: 29px; overflow-y: hidden;']")
    private WebElement editPostInputField;

    @FindBy(className = "oxd-button--main")
    private List<WebElement> postButtons;


    //method for publishing a post
    public void publishAPost(String inputData) {
        postInputField.sendKeys(inputData);
        postButton.click();
    }

    //method for getting a text
    public String getMessageText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]")));
        return successMessage.getText();
    }

    //method for getting a text from the newest post
    public String getNewestPostText() {
        String postText = "";
        for (int i = 0; i < postsTextList.size(); i++) {
            postText = postsTextList.get(0).getText();
        }
        return postText;
    }

    //method for deleting a first post
    public void deleteOrEditAPost(String command, String postText) {
        for (int i = 0; i < threeDotsButtons.size(); i++) {
            threeDotsButtons.get(0).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-dropdown-menu")));
        if (command.equals("delete")) {
            commandsForPost.get(0).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-button--label-danger")));
            deleteConfirmationButton.click();
        } else if (command.equals("edit")) {
            commandsForPost.get(1).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-dialog-modal")));
            editPostInputField.sendKeys(postText);
            postButtons.get(1).click();
        }
    }

    //method for checking if the first post is present
    public boolean isPostPresent(String text) {
        boolean isPostPresent = false;
        for (int i = 0; i < postsTextList.size(); i++) {
            if (postsTextList.get(i).getText().equals(text)) {
                isPostPresent = true;
                break;
            }
        }
        return isPostPresent;
    }

    //method for sorting a posts by desired criteria
    public void sortPosts(String sortingMethod) {
        for (int i = 0; i < sortButtonsList.size(); i++) {
            if (sortingMethod.equals("Most Recent Posts")) {
                sortButtonsList.get(0).click();
            } else if (sortingMethod.equals("Most Liked Posts")) {
                sortButtonsList.get(1).click();
            } else if (sortingMethod.equals("Most Commented Posts")) {
                sortButtonsList.get(2).click();
            }
        }
    }

    //method for checking if posts are sorted by number of likes
    public boolean arePostsSortedByNumberOfLikes() {
        boolean postsAreSorted = true;
        List<String> likesList = new ArrayList<>();
        List<Integer> likesListNumber = new ArrayList<>();
        for (WebElement el : likesSharesAndCommentsList) {
            likesList.add(el.getText().substring(0, 1));
        }
        for (String el : likesList) {
            int intValue = Integer.parseInt(el);
            likesListNumber.add(intValue);
        }
        if (likesListNumber.size() > 1) {
//        System.out.println("Likes list: " + likesListNumber);
            for (int i = likesListNumber.size() - 1; i > 0; i--) {
                if (likesListNumber.get(i) > likesListNumber.get(i - 1)) {
                    postsAreSorted = false;
                    break;
                }
            }
        }
        return postsAreSorted;
    }

    //method for getting the list with number of likes
    public List<Integer> getTheNumberOfLikes() {
        List<String> likesList = new ArrayList<>();
        List<Integer> likesListNumber = new ArrayList<>();
        for (WebElement el : likesSharesAndCommentsList) {
            likesList.add(el.getText().substring(0, 1));
        }
        for (String el : likesList) {
            int intValue = Integer.parseInt(el);
            likesListNumber.add(intValue);
        }
        return likesListNumber;
    }

    //method for checking if posts are sorted by number of comments
    public boolean arePostsSortedByNumberOfComments() {
        boolean postsAreSorted = true;
        List<String> commentsList = new ArrayList<>();
        List<Integer> commentsListNumber = new ArrayList<>();
//        System.out.println("Number of likes: " + getTheNumberOfLikes());
        for (int i = 0; i < getTheNumberOfLikes().size(); i++) {
            if (getTheNumberOfLikes().get(i) == 1) {
                commentsList.add(likesSharesAndCommentsList.get(i).getText().substring(7, 8));
            } else {
                commentsList.add(likesSharesAndCommentsList.get(i).getText().substring(8, 9));
            }
        }
//        System.out.println("Comments list" + commentsList);
        for (String el : commentsList) {
            int intValue = Integer.parseInt(el);
            commentsListNumber.add(intValue);
        }
//        System.out.println("Comments list but numbers: " + commentsListNumber);
        if (commentsListNumber.size() > 1) {
            for (int i = commentsListNumber.size() - 1; i > 0; i--) {
                if (commentsListNumber.get(i) > commentsListNumber.get(i - 1)) {
                    postsAreSorted = false;
                    break;
                }
            }
        }
        return postsAreSorted;
    }

    //method for checking if posts are sorted by the date
    public boolean arePostsSortedByTheDate() throws ParseException {
        boolean isBeginningDateValid = true;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<LocalDate> postDates = new ArrayList<>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (WebElement el : datesList) {
            String date = el.getText().substring(0, 10);
            LocalDate postDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyy-MM-dd"));
            postDates.add(postDate);
        }
        System.out.println(postDates);
        if (!datesList.isEmpty()) {
            for (int i = postDates.size() - 1; i > 0; i--) {
                if (postDates.get(i).isAfter(postDates.get(i - 1))) {
                    isBeginningDateValid = false;
                    break;
                }
            }
        }
        return isBeginningDateValid;
    }
}

