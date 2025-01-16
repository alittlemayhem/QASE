package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkbox {

    WebDriver driver;
    private final String CHECKBOX_PATTERN = "//label[text()='%s']//following::span//input";

    public Checkbox(WebDriver driver) {
        this.driver = driver;
    }


    public void selectCheckbox(String label, String status) {
        if (status.equals("checked")) {
            driver.findElement(By.xpath(String.format(CHECKBOX_PATTERN, label))).click();
        }
    }
}
