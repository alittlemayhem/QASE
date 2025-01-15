package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateTestPlanPage extends BasePage {

    private final String TITLE = "//input[@name='title']";
    private final String ADD_TESTCASES = "//button[text()=' Add cases']";
    private final String CREATE_SAVE_BUTTON = "//button[text()='%s']";

    public CreateTestPlanPage(WebDriver driver) {
        super(driver);
    }

    @Step("Create test plan with specified title: {title}.")
    public CreateTestPlanPage fillTitle(String title) {
        driver.findElement(By.xpath(TITLE)).sendKeys(title);
        return this;
    }

    @Step("Add test cases to new plan.")
    public SelectTestCasesModal addTestCases() {
        driver.findElement(By.xpath(ADD_TESTCASES)).click();
        return new SelectTestCasesModal(driver);
    }

    @Step("Create new plan or save edited based on passed action - {action}")
    public TestPlanPage createOrSavePlan(String action) {
        driver.findElement(By.xpath(String.format(CREATE_SAVE_BUTTON, action))).click();
        return new TestPlanPage(driver);
    }
}
