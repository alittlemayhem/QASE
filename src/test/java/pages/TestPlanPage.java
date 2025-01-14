package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestPlanPage extends BasePage{

    public TestPlanPage(WebDriver driver) {
        super(driver);
    }

    public TestPlanPage open(String code) {
        driver.get("https://app.qase.io/plan/"+code);
        return this;
    }

    public CreateTestPlanPage createTestPlan() {
        driver.findElement(By.xpath("//span[text()='Create plan']")).click();
        return new CreateTestPlanPage(driver);
    }

    public CreateTestPlanPage editTestPlan() {
        driver.findElement(By.xpath("//button[@class='']")).click();
        driver.findElement(By.xpath("//a[text()='Edit']")).click();
        return new CreateTestPlanPage(driver);
    }

    public TestPlanPage deleteTestPlan() {
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        return this;
    }

    public TestPlanPage acceptActionMessage(String action) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//span[text()='%s']", action)))).click();
        return this;
    }
}
