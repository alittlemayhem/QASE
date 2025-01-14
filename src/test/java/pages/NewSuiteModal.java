package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewSuiteModal extends BasePage{

    public NewSuiteModal(WebDriver driver) {
        super(driver);
    }

    public NewSuiteModal fillSuitData(String title, String description, String preconditions) {
        driver.findElement(By.id("title")).sendKeys(title);
        driver.findElement(By.id("description")).sendKeys(description);
        driver.findElement(By.id("preconditions")).sendKeys(preconditions);
        return this;
    }

    public CreatedProjectPage createOrSaveSuite(String action) {
        driver.findElement(By.xpath(String.format("//span[text()='%s']", action))).click();
        return new CreatedProjectPage(driver);
    }
}
