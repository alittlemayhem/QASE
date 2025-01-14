package pages;

import dto.TestСaseBasic;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wrappers.Checkbox;
import wrappers.PickList;

public class CreateTestCasePage extends BasePage{

    private final String TITLE = "title",
            SAVE_BUTTON = "save-case",
            ADD_STEP = "//span[text()=' Add step']//parent::button",
            STEP_ACTION = "//*[text() = '%s']//following::input[@value][1]",
            DATA = "//*[text() = '%s']//following::input[@value][2]",
            EXPECTED_RESULT = "//*[text() = '%s']//following::input[@value][3]";

    public CreateTestCasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill 'Basic' section for test case page.")
    public CreateTestCasePage fillBasicSection(TestСaseBasic basicInfo) {
        driver.findElement(By.id(TITLE)).sendKeys(basicInfo.getTitle());
        new PickList(driver).selectPicklistItem("Status", basicInfo.getStatus());
        new PickList(driver).selectPicklistItem("Severity", basicInfo.getSeverity());
        new PickList(driver).selectPicklistItem("Priority", basicInfo.getPriority());
        new PickList(driver).selectPicklistItem("Type", basicInfo.getType());
        new PickList(driver).selectPicklistItem("Layer", basicInfo.getLayer());
        new PickList(driver).selectPicklistItem("Is flaky", basicInfo.getIsFlaky());
        new PickList(driver).selectPicklistItem("Behavior", basicInfo.getBehavior());
        new PickList(driver).selectPicklistItem("Automation status", basicInfo.getAutoStatus());
        new Checkbox(driver).selectCheckbox("To be automated", basicInfo.getToBeAuto());
        new Checkbox(driver).selectCheckbox("Muted case", basicInfo.getMuted());
        return this;
    }

    @Step("Create specified amount of test steps - {numberOfSteps} - filled with some data.")
    public CreateTestCasePage fillTestCaseSteps(int numberOfSteps) {
        for (int i = 1; i <= numberOfSteps; i++) {
            driver.findElement(By.xpath(ADD_STEP)).click();

            String step = String.valueOf(i);

            driver.findElement(By.xpath(String.format(STEP_ACTION, step))).sendKeys(String.format("Step Action %s", step));
            driver.findElement(By.xpath(String.format(DATA, step))).sendKeys(String.format("Data %s", step));
            driver.findElement(By.xpath(String.format(EXPECTED_RESULT, step))).sendKeys(String.format("Expected result %s", step));
        }
        return this;
    }

    @Step("Save created test case.")
    public CreatedProjectPage saveTestCase() {
        driver.findElement(By.id(SAVE_BUTTON)).click();
        return new CreatedProjectPage(driver);
    }
}
