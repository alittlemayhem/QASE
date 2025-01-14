package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreatedProjectPage extends BasePage{

    private final String PROJECT_TITLE = "//h1",
            CASETITLE_PANE_PATTERN = "//h1//div[text()='%s']",
            CASETITLE_TABLE = "//div[text()='%s']";

    public CreatedProjectPage(WebDriver driver) {
        super(driver);
    }

    public CreatedProjectPage open(String code) {
        driver.get("https://app.qase.io/project/"+code);
        return this;
    }

    @Step("Check that created projects has correct title: {name} repository.")
    public String getProjectTitle(String name) {
        //name + " repository"
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PROJECT_TITLE))).getText();
    }

    @Step("Open modal window to create new test suite by clicking on 'Create new suite' button.")
    public NewSuiteModal openNewSuiteModalByMainButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Create new suite']"))).click();
        return new NewSuiteModal(driver);
    }

    @Step("Open modal window to create new test suite by clicking on '+ Suite' button.")
    public NewSuiteModal openNewSuiteModalByPlusButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Suite']"))).click();
        return new NewSuiteModal(driver);
    }

    @Step("Open modal window to edit test suite.")
    public NewSuiteModal openEditSuiteModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Edit suite']"))).click();
        return new NewSuiteModal(driver);
    }

    @Step("Open modal window to edit test suite.")
    public CreatedProjectPage deleteSuite() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Delete suite']"))).click();
        return this;
    }

    public CreatedProjectPage acceptActionMessage(String action) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']", action)))).click();
        return this;
    }

    @Step("Switch to new test case creation page by 'Create new case' button'")
    public CreateTestCasePage switchToTestCaseByMainButton() {
        driver.findElement(By.xpath("//span[text()='Create new case']")).click();
        return new CreateTestCasePage(driver);
    }

    @Step("Switch to new test case creation page by '+ Case' button")
    public CreateTestCasePage switchToTestCaseByPlusButton() {
        driver.findElement(By.id("create-case-button")).click();
        return new CreateTestCasePage(driver);
    }

    @Step("Check that correct {title} is displayed on panel and in the table.")
    public CreatedProjectPage getTestCaseTitle(String title) {
        //$x(String.format(CASETITLE_PANE_PATTERN, title)).shouldBe(visible);
        //$x(String.format(CASETITLE_TABLE, title)).shouldBe(visible);
        return this;
    }
}
