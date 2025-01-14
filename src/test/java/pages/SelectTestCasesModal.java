package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SelectTestCasesModal extends BasePage{

    public SelectTestCasesModal(WebDriver driver) {
        super(driver);
    }

    public SelectTestCasesModal isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='modals']//following::h2")));
        return this;
    }

    public SelectTestCasesModal selectAllTestCases() {
        driver.findElement(By.xpath("//label[text()='Select all']//child::div")).click();
        return this;
    }

    public CreateTestPlanPage finishCaseSelection() {
        driver.findElement(By.xpath("//span[text()='Done']")).click();
        return new CreateTestPlanPage(driver);
    }
}
