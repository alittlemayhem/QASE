package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProjectsPage extends BasePage {

    private static final String CREATE_PROJECT_BUTTON = "//span[text()='Create new project']";

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public ProjectsPage open() {
        log.info("Opening projects page.");
        driver.get("https://app.qase.io/projects");
        return this;
    }

    public ProjectsPage isPageOpened() {
        log.info("Confirming that page is opened.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CREATE_PROJECT_BUTTON)));
        return this;
    }

    @Step("Open 'Create project' modal window.")
    public NewProjectModal createProject() {
        log.info("Create project.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CREATE_PROJECT_BUTTON))).click();
        return new NewProjectModal(driver);
    }

    public boolean checkIfProjectExists() {
        boolean projectExists;
        try {
            projectExists = driver.findElement(By.xpath("//a[text()='QASE']")).isDisplayed();
        } catch (NoSuchElementException e) {
            projectExists = false;
        }
        return projectExists;
    }
}
