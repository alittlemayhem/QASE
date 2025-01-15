package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    private static final String MESSAGE = "//div[@role='alert']";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Get message about successful/unsuccessful action.")
    public String successMessage() {
        return driver.findElement(By.xpath(MESSAGE)).getText();
    }
}
