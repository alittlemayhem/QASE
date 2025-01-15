package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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
        driver.get("https://app.qase.io/plan/" + code);
        return this;
    }

    @Step("Switch to 'Create plan' page.")
    public CreateTestPlanPage createTestPlan() {
        driver.findElement(By.xpath(CREATE_PLAN_BUTTON)).click();
        return new CreateTestPlanPage(driver);
    }

    @Step("Edit test plan by changing name.")
    public CreateTestPlanPage editTestPlan(String planName) {
        driver.findElement(By.xpath(String.format(ACTION_MENU_PATTERN, planName))).click();
        driver.findElement(By.xpath(EDIT_BUTTON)).click();
        return new CreateTestPlanPage(driver);
    }

    @Step("Delete test plan using action menu for specific plan - {planName}")
    public TestPlanPage deleteTestPlan(String planName) {
        driver.findElement(By.xpath(String.format(ACTION_MENU_PATTERN, planName))).click();
        driver.findElement(By.xpath(DELETE_BUTTON)).click();
        return this;
    }

    @Step("Accept message to confirm selected action - {action}")
    public TestPlanPage acceptActionMessage(String action) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ACTION_MESSAGE, action)))).click();
        return this;
    }

    @Step("Get all plan names as list.")
    public List<WebElement> getPlansOnPage() {
        return driver.findElements(By.xpath(PLANS_PATTERN));
    }

    @Step("Check if test plan is actually present on page.")
    public boolean isPlanOnPage(List<WebElement> plans, String planName) {
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
        driver.findElement(By.xpath(String.format(CHECKBOX_PATTERN, planName))).click();
        return this;
    }

    @Step("Remove test plans that were with selected checkboxes.")
    public TestPlanPage deleteSelectedPlans() {
        driver.findElement(By.xpath(UPDATE_SELECTED_DROPBOX)).click();
        driver.findElement(By.xpath(DELETE_BUTTON)).click();
        return this;
    }
}
