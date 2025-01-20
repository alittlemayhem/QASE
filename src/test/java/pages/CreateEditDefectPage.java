package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.PickList;

@Log4j2
public class CreateEditDefectPage extends BasePage{

    private final String CREATE_DEFECT_BUTTON = "//button[text()='Create defect']";
    private final String DEFECT_TITLE = "//input[@name='title']";
    private final String ACTUAL_RESULT = "//*[text() = 'Actual result']//following::input[@value]";
    private final String UPDATE_DEFECT = "//button[text()='Update defect']";

    public CreateEditDefectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open page to create or edit defect in project with code - {code}.")
    public CreateEditDefectPage open(String code) {
        log.info("Create defect page opening.");
        driver.get("https://app.qase.io/defect/"+code+"/create");
        return this;
    }

    public CreateEditDefectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CREATE_DEFECT_BUTTON)));
        return this;
    }

    @Step("Create defect with required fields: title - {defTitle}, actual result - {result}, severity - {severity}")
    public CreateEditDefectPage fillRequiredDefectFields(String defTitle, String result, String severity) {
        log.info("Fill in required field for defect.");
        driver.findElement(By.xpath(DEFECT_TITLE)).sendKeys(defTitle);
        driver.findElement(By.xpath(ACTUAL_RESULT)).sendKeys(result);
        new PickList(driver).selectDefectOptions("Severity", severity);
        return this;
    }

    @Step("Create new defect.")
    public DefectsPage createDefect() {
        log.info("Click create defect.");
        driver.findElement(By.xpath(CREATE_DEFECT_BUTTON)).click();
        return new DefectsPage(driver);
    }

    @Step("Update selected defect.")
    public DefectsPage updateDefect() {
        log.info("Update defect.");
        driver.findElement(By.xpath(UPDATE_DEFECT)).click();
        return new DefectsPage(driver);
    }
}
