package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    private static final String MESSAGE = "//div[@role='alert']";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(300));
    }

    @Step("Get message about successful/unsuccessful action.")
    public String successMessage() {
        log.info("Obtaining resultant message.");
        return driver.findElement(By.xpath(MESSAGE)).getText();
    }
}
