package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage{

    private static final By USERNAME = By.name("email");
    private static final By PASSWORD = By.name("password");
    private static final String SIGN_IN_BUTTON = "//span[text()='Sign in']";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open login page.")
    public LoginPage open() {
        log.info("Login page opening.");
        driver.get("https://app.qase.io/login");
        return this;
    }

    @Step("Login to application with credentials: user name - {user}, password - {password}")
    public ProjectsPage login(String user, String password) {
        log.info("Login to system using credentials '{}' - '{}'", user, password);
        driver.findElement(USERNAME).sendKeys(user);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(By.xpath(SIGN_IN_BUTTON)).click();
        return new ProjectsPage(driver);
    }
}
