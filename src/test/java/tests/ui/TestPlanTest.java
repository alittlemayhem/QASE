package tests.ui;

import io.qameta.allure.Description;
import models.CreatePlanRq;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

import static adapters.CasesAPI.createCase;
import static adapters.PlansAPI.createPlan;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;

public class TestPlanTest extends BaseTest {

    @Test
    public void onfailedRun() {
        deleteProjectByCode("QASE");
    }

    CreatePlanRq rq = CreatePlanRq.builder()
            .cases(new ArrayList<>(
                    Arrays.asList(1)
            ))
            .title("Plan 1")
            .description("Test plan 1")
            .build();

    @Test(testName = "Plan creation", description = "Test plan creation")
    @Description("Check test plan creation via UI with precondition: project and cases creation using API.")
    public void checkCreatePlan() {
        loginPage.open()
                .login(user, password);
        createProject(proj_rq);
        createCase(case_rq, "QASE");
        testPlanPage.open("QASE")
                        .createTestPlan()
                .fillTitle("Plan title")
                .addTestCases()
                .selectAllTestCases()
                        .finishCaseSelection()
                                .createOrSavePlan("Create plan");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Plan edit", description = "Test plan modification")
    @Description("Check test plan modification via UI with precondition: project, case and plan creation using API.")
    public void checkEditPlan() {
        loginPage.open()
                .login(user, password);
        createProject(proj_rq);
        createCase(case_rq, "QASE");
        createPlan(rq, "QASE");
        testPlanPage.open("QASE")
                .editTestPlan()
                .fillTitle("Updated")
                .createOrSavePlan("Save");

        deleteProjectByCode("QASE");
    }

    @Test(testName = "Plan delete", description = "Test plan delete")
    @Description("Check test plan removal via UI with precondition: project, case and plan creation using API.")
    public void checkDeletePlan() {
        loginPage.open()
                .login(user, password);
        createProject(proj_rq);
        createCase(case_rq, "QASE");
        createPlan(rq, "QASE");
        testPlanPage.open("QASE")
                        .deleteTestPlan()
                                .acceptActionMessage("Delete plan");

        deleteProjectByCode("QASE");
    }
}
