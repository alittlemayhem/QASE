package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CreateTestPlanPage extends BasePage {

    private final String TITLE = "//input[@name='title']";
    private final String ADD_TESTCASES = "//button[text()=' Add cases']";
    private final String CREATE_SAVE_BUTTON = "//button[text()='%s']";

    public CreateTestPlanPage(WebDriver driver) {
        super(driver);
    }

    @Step("Create test plan with specified title: {title}.")
    public CreateTestPlanPage fillTitle(String title) {
        log.info("Fill test plan title.");
        driver.findElement(By.xpath(TITLE)).sendKeys(title);
        return this;
    }

    @Step("Add test cases to new plan.")
    public SelectTestCasesModal addTestCases() {
        log.info("Add cases  to test plan.");
        driver.findElement(By.xpath(ADD_TESTCASES)).click();
        return new SelectTestCasesModal(driver);
    }

    @Step("Create new plan or save edited based on passed action - {action}")
    public TestPlanPage createOrSavePlan(String action) {
        log.info("Create or save plan.");
        driver.findElement(By.xpath(String.format(CREATE_SAVE_BUTTON, action))).click();
        return new TestPlanPage(driver);
    }
}
