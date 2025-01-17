package tests.ui;

import dto.TestСaseBasic;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.BaseTest;

import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;

public class TestCaseTest extends BaseTest {

    TestСaseBasic basicCaseInfo = TestСaseBasic.builder()
            .title("Case 1")
            .build();

    @Test(testName = "Case creation", description = "Test case creation")
    @Description("Check case creation via UI using 'Create new case' button with precondition: project creation using API.")
    public void checkCreateCaseMain() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        String actualMessage = createdProjectPage.open("QASE")
                .switchToTestCaseByMainButton()
                .fillBasicSection(basicCaseInfo)
                .saveTestCase()
                .successMessage();

        String actualTitle = createdProjectPage.getTestCaseTitle();
        softAssert.assertEquals(actualMessage,
                "Test case was created successfully!",
                "Wrong message is shown or case was not created.");

        softAssert.assertEquals(actualTitle,
                "Case 1",
                "Wrong case title is displayed.");

        softAssert.assertEquals(createdProjectPage.numberOfCaseTitlesOnPage("Case 1"),
                2,
                "Case should be mentioned twice: something is wrong.");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Case creation", description = "Test case creation")
    @Description("Check case creation via UI using '+ Case' button with precondition: project creation using API.")
    public void checkCreateCaseTop() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        String actualMessage = createdProjectPage.open("QASE")
                .switchToTestCaseByPlusButton()
                .fillBasicSection(basicCaseInfo)
                .saveTestCase()
                .successMessage();

        String actualTitle = createdProjectPage.getTestCaseTitle();
        softAssert.assertEquals(actualMessage,
                "Test case was created successfully!",
                "Wrong message is shown or case was not created.");

        softAssert.assertEquals(actualTitle,
                "Case 1",
                "Wrong case title is displayed.");

        softAssert.assertEquals(createdProjectPage.numberOfCaseTitlesOnPage("Case 1"),
                2,
                "Case should be mentioned twice: something is wrong.");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Case creation", description = "Test case creation")
    @Description("Check case creation via UI with steps button with precondition: project creation using API.")
    public void checkCreateCaseWithSteps() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        String actualMessage = createdProjectPage.open("QASE")
                .switchToTestCaseByMainButton()
                .fillBasicSection(basicCaseInfo)
                .fillTestCaseSteps(3)
                .saveTestCase()
                .successMessage();

        softAssert.assertEquals(actualMessage,
                "Test case was created successfully!",
                "Wrong message is shown or case was not created.");

        softAssert.assertTrue(createdProjectPage.areStepsCreated(),
                "Steps were NOT added.");

        deleteProjectByCode("QASE");
    }
}
