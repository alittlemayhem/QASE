package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DefectsPage extends BasePage{

    public DefectsPage(WebDriver driver) {
        super(driver);
    }

    public DefectsPage open(String code) {
        driver.get("https://app.qase.io/defect/"+code);
        return this;
    }

    public DefectsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Create new defect")));
        return this;
    }

    public CreateEditDefectPage createNewDefect() {
        driver.findElement(By.linkText("Create new defect")).click();
        return new CreateEditDefectPage(driver);
    }

    public CreateEditDefectPage editDefect() {
        driver.findElement(By.xpath("//button[@aria-label='Open action menu']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Edit']"))).click();
        return new CreateEditDefectPage(driver);
    }

    public DefectsPage modifyOrDeleteDefect(String action) {
        driver.findElement(By.xpath("//button[@aria-label='Open action menu']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']", action)))).click();
        return this;
    }

    public DefectsPage acceptActionMessage(String action) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']", action)))).click();
        return this;
    }
}
