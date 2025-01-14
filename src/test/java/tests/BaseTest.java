package tests;

import io.qameta.allure.Description;
import models.cases.CreateCaseRq;
import models.project.CreateProjectRq;
import models.suite.CreateSuiteRq;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CreatedProjectPage;
import pages.DefectsPage;
import pages.LoginPage;
import pages.TestPlanPage;
import utils.AllureUtils;
import utils.PropertyReader;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;

    public LoginPage loginPage;
    public DefectsPage defectsPage;
    public CreatedProjectPage createdProjectPage;
    public TestPlanPage testPlanPage;

    public CreateProjectRq proj_rq = CreateProjectRq.builder()
            .title("QASE")
            .code("QASE")
            .access("all")
            .build();

    public CreateSuiteRq suite_rq = CreateSuiteRq.builder()
            .title("Suite TEST")
            .build();

    public CreateCaseRq case_rq = CreateCaseRq.builder()
            .title("Case UPDATED")
            .build();

    public String user = System.getProperty("user", PropertyReader.getProperty("user"));
    public String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        defectsPage = new DefectsPage(driver);
        createdProjectPage = new CreatedProjectPage(driver);
        testPlanPage = new TestPlanPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    @Description("Closing the browser.")
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            AllureUtils.takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
