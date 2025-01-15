package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectsPage extends BasePage {

    private static final String CREATE_PROJECT_BUTTON = "//span[text()='Create new project']";

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public ProjectsPage open() {
        driver.get("https://app.qase.io/projects");
        return this;
    }

    public ProjectsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CREATE_PROJECT_BUTTON)));
        return this;
    }

    @Step("Open 'Create project' modal window.")
    public NewProjectModal createProject() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CREATE_PROJECT_BUTTON))).click();
        return new NewProjectModal(driver);
    }
}
