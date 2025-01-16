package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class SelectTestCasesModal extends BasePage {

    private final String TITLE = "//div[@id='modals']//following::h2";
    private final String SELECT_ALL = "//label[text()='Select all']//child::div";
    private final String DONE_BUTTON = "//span[text()='Done']";

    public SelectTestCasesModal(WebDriver driver) {
        super(driver);
    }

    @Step("Mark 'Select All' checkbox to select all available test cases.")
    public SelectTestCasesModal selectAllTestCases() {
        log.info("Mark 'Select All' checkbox to select all available test cases.");
        driver.findElement(By.xpath(SELECT_ALL)).click();
        return this;
    }

    @Step("Finish test case selection.")
    public CreateTestPlanPage finishCaseSelection() {
        log.info("Finish test case selection.");
        driver.findElement(By.xpath(DONE_BUTTON)).click();
        return new CreateTestPlanPage(driver);
    }
}
