package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PickList {

    WebDriver driver;
    private final String DROPDOWN_PATTERN = "//label[text()='%s']//following::div";
    private final String DROPDOWN_OPTION_PATTERN = "//div[text()='%s']";

    public PickList(WebDriver driver) {
        this.driver = driver;
    }

    public void selectPicklistItem(String label, String option) {
        driver.findElement(By.xpath(String.format(DROPDOWN_PATTERN, label))).click();
        driver.findElement(By.xpath(String.format(DROPDOWN_OPTION_PATTERN, option))).click();
    }
}
