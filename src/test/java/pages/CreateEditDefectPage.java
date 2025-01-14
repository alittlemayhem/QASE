package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.PickList;

public class CreateEditDefectPage extends BasePage{

    public CreateEditDefectPage(WebDriver driver) {
        super(driver);
    }

    public CreateEditDefectPage open(String code) {
        driver.get("https://app.qase.io/defect/"+code+"/create");
        return this;
    }

    public CreateEditDefectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Create defect']")));
        return this;
    }

    public CreateEditDefectPage fillRequiredDefectFields(String defTitle, String result, String severity) {
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys(defTitle);
        driver.findElement(By.xpath("//*[text() = 'Actual result']//following::input[@value]")).sendKeys(result);
        //new PickList(driver).selectPicklistItem("Severity", severity);
        return this;
    }

    public DefectsPage createDefect() {
        driver.findElement(By.xpath("//button[text()='Create defect']")).click();
        return new DefectsPage(driver);
    }

    public DefectsPage updateDefect() {
        driver.findElement(By.xpath("//button[text()='Update defect']")).click();
        return new DefectsPage(driver);
    }
}
