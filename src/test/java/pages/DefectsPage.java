package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DefectsPage extends BasePage {

    private final By CREATE_NEW_DEFECT = By.linkText("Create new defect");
    private final String ACTION_MENU = "//button[@aria-label='Open action menu']";
    private final String EDIT_DEFECT = "//div[text()='Edit']";
    private final String MODIFY_OR_DELETE_PATTERN = "//span[text()='%s']";
    private final String ACTION_MESSAGE = "//span[text()='%s']";
    private final String DEFECT_CODE = "//*[text()='%s']//ancestor::td//span[@class]";
    private final String DEFECTS_ON_PAGE = "//a[contains(text(), 'Defect')]";
    private final String DEFECT_CODES = "//span[contains(text(), 'D-')]";

    public DefectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open defects page of project with code - {code}")
    public DefectsPage open(String code) {
        driver.get("https://app.qase.io/defect/" + code);
        return this;
    }

    public DefectsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CREATE_NEW_DEFECT));
        return this;
    }

    @Step("Open 'Create defect' page.")
    public CreateEditDefectPage createNewDefect() {
        driver.findElement(CREATE_NEW_DEFECT).click();
        return new CreateEditDefectPage(driver);
    }

    @Step("Edit defect.")
    public CreateEditDefectPage editDefect() {
        driver.findElement(By.xpath(ACTION_MENU)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EDIT_DEFECT))).click();
        return new CreateEditDefectPage(driver);
    }

    @Step("Change status of defect or delete it based on action - {action}")
    public DefectsPage modifyOrDeleteDefect(String action) {
        driver.findElement(By.xpath(ACTION_MENU)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(MODIFY_OR_DELETE_PATTERN, action)))).click();
        return this;
    }

    @Step("Accept message to confirm selected action - {action}")
    public DefectsPage acceptActionMessage(String action) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ACTION_MESSAGE, action)))).click();
        return this;
    }

    @Step("Get actual defect code after creation of defect - {defectName}.")
    public String getDefectCode(String defectName) {
        return driver.findElement(By.xpath(String.format(DEFECT_CODE, defectName))).getText();
    }

    @Step("Get all defect names as list.")
    public List<WebElement> getDefectsOnPage() {
        return driver.findElements(By.xpath(DEFECTS_ON_PAGE));
    }

    @Step("Get all defect codes as list.")
    public List<WebElement> getCodeDefectsOnPage() {
        return driver.findElements(By.xpath(DEFECT_CODES));
    }

    @Step("Check if defect is actually present on page.")
    public boolean isDefectOnPage(List<WebElement> defects, String defect) {
        boolean isDisplayed = false;
        for (int i = 0; i < defects.size(); i++) {
            if (defects.get(i).getText().equals(defect)) {
                isDisplayed = true;
                break;
            }
        }
        return isDisplayed;
    }

    @Step("Confirms that defect code for created defect is correct.")
    public boolean isDefectCodeCorrect(List<WebElement> defects, String defect, List<WebElement> defectCodes, String code) {
        boolean isDefectCodeCorrect = false;
        for (int i = 0; i < defects.size(); i++) {
            if (defects.get(i).getText().equals(defect) && defectCodes.get(i).getText().equals(code)) {
                isDefectCodeCorrect = true;
                break;
            }
        }
        return isDefectCodeCorrect;
    }
}
