package tests.ui;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.BaseTest;

import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;

public class TestCaseTest extends BaseTest {

    @Test(testName = "Case creation", description = "Test case creation")
    @Description("Check case creation via UI with precondition: project creation using API.")
    public void checkCreateCase() {
        loginPage.open()
                .login(user, password);
        createProject(proj_rq);


        deleteProjectByCode("QASE");
    }
}
