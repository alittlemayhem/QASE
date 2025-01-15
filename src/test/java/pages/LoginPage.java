package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private static final By USERNAME = By.name("email");
    private static final By PASSWORD = By.name("password");
    private static final String SIGN_IN_BUTTON = "//span[text()='Sign in']";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open login page.")
    public LoginPage open() {
        driver.get("https://app.qase.io/login");
        return this;
    }

    @Step("Login to application with credentials: user name - {user}, password - {password}")
    public ProjectsPage login(String user, String password) {
        driver.findElement(USERNAME).sendKeys(user);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(By.xpath(SIGN_IN_BUTTON)).click();
        return new ProjectsPage(driver);
    }
}
