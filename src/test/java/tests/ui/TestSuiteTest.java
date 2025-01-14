package tests.ui;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.BaseTest;

import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;
import static adapters.SuitAPI.createSuite;
import static org.testng.Assert.assertEquals;

public class TestSuiteTest extends BaseTest {

    @Test(testName = "Suite creation", description = "Test suite creation")
    @Description("Check suite creation via UI button 'Create new suite' with precondition: project creation using API.")
    public void checkCreateSuiteMain() {
        loginPage.open()
                .login(user, password);
        createProject(proj_rq);
        String actualMessage = createdProjectPage.open("QASE")
                .openNewSuiteModalByMainButton()
                .fillSuitData("TestSuite", "Descr", "Precond")
                .createOrSaveSuite("Create")
                .successMessage();

        assertEquals(actualMessage,
                "Suite was successfully created.",
                "Wrong or no message was shown on test suite create");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Suite creation", description = "Test suite creation")
    @Description("Check suite creation via UI button '+ Suite' with precondition: project creation using API.")
    public void checkCreateSuiteTop() {
        loginPage.open()
                .login(user, password);
        createProject(proj_rq);
        String actualMessage = createdProjectPage.open("QASE")
                .openNewSuiteModalByPlusButton()
                .fillSuitData("TestSuite", "Descr", "Precond")
                .createOrSaveSuite("Create")
                .successMessage();

        assertEquals(actualMessage,
                "Suite was successfully created.",
                "Wrong or no message was shown on test suite create");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Suite edit", description = "Test suite modification")
    @Description("Check suite edit via UI with preconditions: project and suite creation using API.")
    public void checkEditSuite() {
        loginPage.open()
                .login(user, password);
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        String actualMessage = createdProjectPage.open("QASE")
                .openEditSuiteModal()
                .fillSuitData("SuiteUpdate", "DescrUpd", "PrecondUpd")
                .createOrSaveSuite("Save")
                .successMessage();

        assertEquals(actualMessage,
                "Suite was successfully edited.",
                "Wrong or no message was shown on test suite create");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Suite delete", description = "Test suite removal")
    @Description("Check suite delete via UI with preconditions: project and suite creation using API.")
    public void checkDeleteSuite() {
        loginPage.open()
                .login(user, password);
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        String actualMessage = createdProjectPage.open("QASE")
                .deleteSuite()
                .acceptActionMessage("Delete")
                .successMessage();

        assertEquals(actualMessage,
                "Suite was successfully deleted.",
                "Wrong or no message was shown on test suite create");

        deleteProjectByCode("QASE");
    }
}
