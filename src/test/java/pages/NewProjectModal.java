package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewProjectModal extends BasePage{

    private static final String HEADER = "//h3";
    private static final By PROJECT_NAME = By.id("project-name");
    private static final By PROJECT_CODE = By.id("project-code");
    private static final String RADIOBUTTON_PATTERN = "//input[@value='%s']";
    private static final String DROPDOWN = "//input[@id='select-search-:rm:']";
    private static final String OWNER_GROUP = "//div[text()='Owner group']";
    private static final String CREATE_PROJECT_BUTTON = "//span[text()='Create project']";

    public NewProjectModal(WebDriver driver) {
        super(driver);
    }

    public NewProjectModal isModalOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HEADER)));
        return this;
    }

    @Step("Specify project name - {project}.")
    public NewProjectModal setProjectName(String project) {
        driver.findElement(PROJECT_NAME).sendKeys(project);
        return this;
    }

    @Step("Specify user-defined value of project code - {projectCode}.")
    public NewProjectModal setProjectCode(String projectCode) {
        driver.findElement(PROJECT_CODE).clear();
        driver.findElement(PROJECT_CODE).sendKeys(projectCode);
        return this;
    }

    @Step("Select radiobutton for mode access.")
    public NewProjectModal setRadioButtonValue(String buttonValue) {
        // ИЛИ с ожиданием
        // ИЛИ через JS click
        driver.findElement(By.xpath(String.format(RADIOBUTTON_PATTERN, buttonValue))).click();
        return this;
    }

    @Step("Select group owner for private access mode.")
    public NewProjectModal selectGroupOwner() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DROPDOWN))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OWNER_GROUP))).click();
        return this;
    }

    @Step("Create project.")
    public CreatedProjectPage clickCreateProject() {
        driver.findElement(By.xpath(CREATE_PROJECT_BUTTON)).click();
        return new CreatedProjectPage(driver);
    }
}
