package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PickList {

    WebDriver driver;
    private final String DROPDOWN_PATTERN = "//label[text()='%s']//following::div";
    private final String DROPDOWN_OPTION_PATTERN = "//div[text()='%s']";

    public PickList(WebDriver driver) {
        this.driver = driver;
    }

    public void selectPicklistItem(String label, String option) {
        driver.findElement(By.xpath(String.format(DROPDOWN_PATTERN, label))).click();

        WebElement element = driver.findElement(By.xpath(String.format(DROPDOWN_OPTION_PATTERN, option)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
