package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateTestPlanPage extends BasePage{

    public CreateTestPlanPage(WebDriver driver) {
        super(driver);
    }

    public CreateTestPlanPage fillTitle(String title) {
        driver.findElement(By.xpath("//input[@name='title']")).sendKeys(title);
        return this;
    }

    public SelectTestCasesModal addTestCases() {
        driver.findElement(By.xpath("//button[text()=' Add cases']")).click();
        return new SelectTestCasesModal(driver);
    }

    public TestPlanPage createOrSavePlan(String action) {
        driver.findElement(By.xpath(String.format("//button[text()='%s']", action))).click();
        return new TestPlanPage(driver);
    }
}
