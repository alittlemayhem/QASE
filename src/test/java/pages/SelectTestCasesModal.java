package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SelectTestCasesModal extends BasePage {

    private final String TITLE = "//div[@id='modals']//following::h2";
    private final String SELECT_ALL = "//label[text()='Select all']//child::div";
    private final String DONE_BUTTON = "//span[text()='Done']";

    public SelectTestCasesModal(WebDriver driver) {
        super(driver);
    }

    public SelectTestCasesModal isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE)));
        return this;
    }

    @Step("Mark 'Select All' checkbox to select all available test cases.")
    public SelectTestCasesModal selectAllTestCases() {
        driver.findElement(By.xpath(SELECT_ALL)).click();
        return this;
    }

    @Step("Finish test case selection.")
    public CreateTestPlanPage finishCaseSelection() {
        driver.findElement(By.xpath(DONE_BUTTON)).click();
        return new CreateTestPlanPage(driver);
    }
}
