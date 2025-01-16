package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class NewProjectModal extends BasePage{

    private static final String HEADER = "//h3";
    private static final By PROJECT_NAME = By.id("project-name");
    private static final By PROJECT_CODE = By.id("project-code");
    private static final String RADIOBUTTON_PATTERN = "//input[@value='%s']";
    private static final String DROPDOWN = "//div[@role='combobox']";
    private static final String OWNER_GROUP = "//div[text()='Owner group']";
    private static final String CREATE_PROJECT_BUTTON = "//span[text()='Create project']";

    public NewProjectModal(WebDriver driver) {
        super(driver);
    }

    public NewProjectModal isModalOpened() {
        log.info("Confirming that new project modal is opened.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HEADER)));
        return this;
    }

    @Step("Specify project name - {project}.")
    public NewProjectModal setProjectName(String project) {
        log.info("Entering project name.");
        driver.findElement(PROJECT_NAME).sendKeys(project);
        return this;
    }

    @Step("Specify user-defined value of project code - {projectCode}.")
    public NewProjectModal setProjectCode(String projectCode) {
        log.info("Specifying user defined value of project code.");
        driver.findElement(PROJECT_CODE).clear();
        driver.findElement(PROJECT_CODE).sendKeys(projectCode);
        return this;
    }

    @Step("Select radiobutton for mode access.")
    public NewProjectModal setRadioButtonValue(String buttonValue) {
        log.info("Selecting radiobutton for mode access.");
        driver.findElement(By.xpath(String.format(RADIOBUTTON_PATTERN, buttonValue))).click();
        return this;
    }

    @Step("Select group owner for private access mode.")
    public NewProjectModal selectGroupOwner() {
        log.info("Select group owner for private access mode.");
        driver.findElement(By.xpath(DROPDOWN)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OWNER_GROUP))).click();
        return this;
    }

    @Step("Create project.")
    public CreatedProjectPage clickCreateProject() {
        log.info("Project creation.");
        driver.findElement(By.xpath(CREATE_PROJECT_BUTTON)).click();
        return new CreatedProjectPage(driver);
    }
}
