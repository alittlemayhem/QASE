package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class CreatedProjectPage extends BasePage {

    private final String PROJECT_TITLE = "//h1";
    private final String CASETITLE_PANE = "//h1//div";
    private final String CREATE_NEW_SUITE_BUTTON = "//span[text()='Create new suite']";
    private final String SUITE_BUTTON = "//span[text()='Suite']";
    private final String EDIT_SUITE_BUTTON = "//button[@aria-label='Edit suite']";
    private final String DELETE_SUITE = "//button[@aria-label='Delete suite']";
    private final String ACTION_MESSAGE_PATTERN = "//span[text()='%s']";
    private final String CREATE_NEW_CASE_BUTTON = "//span[text()='Create new case']";
    private final String CASE_BUTTON = "//span[text()='Case']";
    private final String CASE_TITLES = "//*[text()='%s']";
    private final String STEPS = "//h3[contains(text(), 'Steps')]";

    public CreatedProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open page of created project with code {code}")
    public CreatedProjectPage open(String code) {
        log.info("Created project page opening.");
        driver.get("https://app.qase.io/project/" + code);
        return this;
    }

    @Step("Check that created projects has correct title: {name} repository.")
    public String getProjectTitle() {
        log.info("Obtaining project title.");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PROJECT_TITLE))).getText();
    }

    @Step("Open modal window to create new test suite by clicking on 'Create new suite' button.")
    public NewSuiteModal openNewSuiteModalByMainButton() {
        log.info("Open modal to create new suite by clicking on 'Create new suite' button.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CREATE_NEW_SUITE_BUTTON))).click();
        return new NewSuiteModal(driver);
    }

    @Step("Open modal window to create new test suite by clicking on '+ Suite' button.")
    public NewSuiteModal openNewSuiteModalByPlusButton() {
        log.info("Open modal to create new suite by clicking on '+ Suite' button.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUITE_BUTTON))).click();
        return new NewSuiteModal(driver);
    }

    @Step("Open modal window to edit test suite.")
    public NewSuiteModal openEditSuiteModal() {
        log.info("Open modal window to edit test suite.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EDIT_SUITE_BUTTON))).click();
        return new NewSuiteModal(driver);
    }

    @Step("Open modal window to delete test suite.")
    public CreatedProjectPage deleteSuite() {
        log.info("Delete test suite.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DELETE_SUITE))).click();
        return this;
    }

    @Step("Accept message to confirm selected action - {action}")
    public CreatedProjectPage acceptActionMessage(String action) {
        log.info("Confirm action in action message.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ACTION_MESSAGE_PATTERN, action)))).click();
        return this;
    }

    @Step("Switch to new test case creation page by 'Create new case' button")
    public CreateTestCasePage switchToTestCaseByMainButton() {
        log.info("Switch to new test case creation page by 'Create new case' button");
        driver.findElement(By.xpath(CREATE_NEW_CASE_BUTTON)).click();
        return new CreateTestCasePage(driver);
    }

    @Step("Switch to new test case creation page by '+ Case' button")
    public CreateTestCasePage switchToTestCaseByPlusButton() {
        log.info("Switch to new test case creation page by '+ Case' button");
        driver.findElement(By.xpath(CASE_BUTTON)).click();
        return new CreateTestCasePage(driver);
    }

    @Step("Check that correct {title} is displayed on panel")
    public String getTestCaseTitle() {
        log.info("Obtaining test case title.");
        return driver.findElement(By.xpath(CASETITLE_PANE)).getText();
    }

    @Step("Check that correct {title} is displayed on panel and in table")
    public int numberOfCaseTitlesOnPage(String title) {
        log.info("Obtaining number of case titles on page after create.");
        List<WebElement> numberOfTitles = driver.findElements(By.xpath((String.format(CASE_TITLES, title))));
        return numberOfTitles.size();
    }

    @Step("Check that steps of test case were added")
    public boolean areStepsCreated() {
        log.info("Check if steps are displayed.");
        return driver.findElement(By.xpath(STEPS)).isDisplayed();
    }
}
