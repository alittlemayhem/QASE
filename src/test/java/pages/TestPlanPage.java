package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class TestPlanPage extends BasePage {

    private final String CREATE_PLAN_BUTTON = "//span[text()='Create plan']";
    private final String ACTION_MENU_PATTERN = "//a[text()='%s']//following::button";
    private final String EDIT_BUTTON = "//a[text()='Edit']";
    private final String DELETE_BUTTON = "//button[text()='Delete']";
    private final String PLANS_PATTERN = "//a[contains(text(), 'Plan')]";
    private final String CHECKBOX_PATTERN = "//*[text()='%s']//ancestor::tr//input";
    private final String UPDATE_SELECTED_DROPBOX = "//span[text()='Update selected ']";
    private final String ACTION_MESSAGE = "//span[text()='%s']";

    public TestPlanPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open test plans page for project with code - {code}.")
    public TestPlanPage open(String code) {
        log.info("Test plan page opening.");
        driver.get("https://app.qase.io/plan/" + code);
        return this;
    }

    @Step("Switch to 'Create plan' page.")
    public CreateTestPlanPage createTestPlan() {
        log.info("Creating test plan.");
        driver.findElement(By.xpath(CREATE_PLAN_BUTTON)).click();
        return new CreateTestPlanPage(driver);
    }

    @Step("Edit test plan by changing name.")
    public CreateTestPlanPage editTestPlan(String planName) {
        log.info("Editing test plan.");
        driver.findElement(By.xpath(String.format(ACTION_MENU_PATTERN, planName))).click();
        driver.findElement(By.xpath(EDIT_BUTTON)).click();
        return new CreateTestPlanPage(driver);
    }

    @Step("Delete test plan using action menu for specific plan - {planName}")
    public TestPlanPage deleteTestPlan(String planName) {
        log.info("Deleting test plan..");
        driver.findElement(By.xpath(String.format(ACTION_MENU_PATTERN, planName))).click();
        driver.findElement(By.xpath(DELETE_BUTTON)).click();
        return this;
    }

    @Step("Accept message to confirm selected action - {action}")
    public TestPlanPage acceptActionMessage(String action) {
        log.info("Confirming action.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ACTION_MESSAGE, action)))).click();
        return this;
    }

    @Step("Get all plan names as list.")
    public List<WebElement> getPlansOnPage() {
        log.info("Getting all plans on page.");
        return driver.findElements(By.xpath(PLANS_PATTERN));
    }

    @Step("Check if test plan is actually present on page.")
    public boolean isPlanOnPage(List<WebElement> plans, String planName) {
        log.info("Confirming that plan is present on page.");
        boolean isDisplayed = false;
        for (int i = 0; i < plans.size(); i++) {
            if (plans.get(i).getText().equals(planName)) {
                isDisplayed = true;
                break;
            }
        }
        return isDisplayed;
    }

    @Step("Select checkbox for specific plan - {planName")
    public TestPlanPage selectSpecificPlan(String planName) {
        log.info("Select checkbox for specific plan.");
        driver.findElement(By.xpath(String.format(CHECKBOX_PATTERN, planName))).click();
        return this;
    }

    @Step("Remove test plans that were with selected checkboxes.")
    public TestPlanPage deleteSelectedPlans() {
        log.info("Remove test plans that were with selected checkboxes.");
        driver.findElement(By.xpath(UPDATE_SELECTED_DROPBOX)).click();
        driver.findElement(By.xpath(DELETE_BUTTON)).click();
        return this;
    }
}
