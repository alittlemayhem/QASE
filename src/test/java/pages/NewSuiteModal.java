package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewSuiteModal extends BasePage{

    private final By TITLE = By.id("title");
    private final By DESCRIPTION = By.id("description");
    private final By PRECONDITIONS = By.id("preconditions");
    private final String CREATE_SAVE_PATTERN = "//span[text()='%s']";

    public NewSuiteModal(WebDriver driver) {
        super(driver);
    }

    @Step("Fill in data for new test suite: title - {title}, description - {description}, preconditions - {preconditions}.")
    public NewSuiteModal fillSuitData(String title, String description, String preconditions) {
        driver.findElement(TITLE).sendKeys(title);
        driver.findElement(DESCRIPTION).sendKeys(description);
        driver.findElement(PRECONDITIONS).sendKeys(preconditions);
        return this;
    }

    @Step("Create new or save edited test suite based on selected action  - {action}.")
    public CreatedProjectPage createOrSaveSuite(String action) {
        driver.findElement(By.xpath(String.format(CREATE_SAVE_PATTERN, action))).click();
        return new CreatedProjectPage(driver);
    }
}
