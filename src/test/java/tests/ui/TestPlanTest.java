package tests.ui;

import io.qameta.allure.Description;
import models.CreatePlanRq;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static adapters.CasesAPI.createCase;
import static adapters.PlansAPI.createPlan;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;

public class TestPlanTest extends BaseTest {

    CreatePlanRq rq = CreatePlanRq.builder()
            .cases(new ArrayList<>(
                    List.of(1)
            ))
            .title("Plan 1")
            .description("Test plan 1")
            .build();
    CreatePlanRq rq2 = CreatePlanRq.builder()
            .cases(new ArrayList<>(
                    List.of(1)
            ))
            .title("Plan 2")
            .description("Test plan 2")
            .build();
    CreatePlanRq rq3 = CreatePlanRq.builder()
            .cases(new ArrayList<>(
                    List.of(1)
            ))
            .title("Plan 3")
            .description("Test plan 3")
            .build();

    @Test(testName = "Plan creation", description = "Test plan creation")
    @Description("Check test plan creation via UI with precondition: project and cases creation using API.")
    public void checkCreatePlan() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        createCase(case_rq, "QASE");
        String actualMessage = testPlanPage.open("QASE")
                .createTestPlan()
                .fillTitle("Plan title")
                .addTestCases()
                .selectAllTestCases()
                .finishCaseSelection()
                .createOrSavePlan("Create plan")
                .successMessage();

        List<WebElement> plans = testPlanPage.getPlansOnPage();
        softAssert.assertTrue(testPlanPage.isPlanOnPage(plans, "Plan title"),
                "Plan is absent on page.");

        softAssert.assertEquals(actualMessage,
                "Test plan was created successfully!",
                "Wrong message is shown or test plan was not created.");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Plan edit", description = "Test plan modification")
    @Description("Check test plan modification via UI with precondition: project, case and plan creation using API.")
    public void checkEditPlan() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        createCase(case_rq, "QASE");
        createPlan(rq, "QASE");
        String actualMessage = testPlanPage.open("QASE")
                .editTestPlan("Plan 1")
                .fillTitle("Updated")
                .createOrSavePlan("Save")
                .successMessage();

        List<WebElement> plans = testPlanPage.getPlansOnPage();
        softAssert.assertTrue(testPlanPage.isPlanOnPage(plans, "Plan 1Updated"),
                "Plan was not renamed or absent on page.");

        softAssert.assertEquals(actualMessage,
                "Test plan was edited successfully!",
                "Wrong message is shown or test plan was not edited.");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Plan delete", description = "Test plan delete")
    @Description("Check test plan removal via UI with precondition: project, case and plan creation using API.")
    public void checkDeletePlan() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        createCase(case_rq, "QASE");
        createPlan(rq, "QASE");
        String actualMessage = testPlanPage.open("QASE")
                .deleteTestPlan("Plan 1")
                .acceptActionMessage("Delete plan")
                .successMessage();

        List<WebElement> plans = testPlanPage.getPlansOnPage();
        softAssert.assertEquals(plans.size(),
                0,
                "Plan was not deleted.");

        softAssert.assertEquals(actualMessage,
                "Test plan Plan 1 was deleted successfully!",
                "Wrong message is shown or test plan was not edited.");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Delete checked plans", description = "Delete several selected test plans.")
    @Description("Check selected test plan removal via UI with precondition: project, case and plans creation using API.")
    public void checkDeleteSelectedPlans() {
        loginPage.open()
                .login(user, password);
        if (!projectsPage.checkIfProjectExists()) {
            createProject(proj_rq);
        }
        createCase(case_rq, "QASE");
        createPlan(rq, "QASE");
        createPlan(rq2, "QASE");
        createPlan(rq3, "QASE");

        List<WebElement> plans = testPlanPage.getPlansOnPage();
        softAssert.assertEquals(plans.size(),
                3,
                "Wrong amount of plans on page.");

        testPlanPage.open("QASE")
                .selectSpecificPlan("Plan 1")
                .selectSpecificPlan("Plan 3")
                .deleteSelectedPlans()
                .acceptActionMessage("Delete");

        List<WebElement> planAfterDelete = testPlanPage.getPlansOnPage();
        softAssert.assertEquals(planAfterDelete.size(),
                1,
                "Wrong amount of plans on page: was not removed.");

        String actualMessage = testPlanPage.successMessage();
        softAssert.assertEquals(actualMessage,
                "2 test plans were deleted successfully!",
                "Wrong message is shown or plans were not deleted.");

        deleteProjectByCode("QASE");
    }
}
