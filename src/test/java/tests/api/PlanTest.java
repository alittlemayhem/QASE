package tests.api;

import io.qameta.allure.Description;
import models.CreatePlanRq;
import models.Response;
import models.cases.CreateCaseRq;
import models.project.CreateProjectRq;
import models.suite.CreateSuiteRq;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

import static adapters.CasesAPI.createCase;
import static adapters.PlansAPI.*;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;
import static adapters.SuitAPI.createSuite;

public class PlanTest extends BaseTest {

    @Test(testName = "Plan via API", description = "Create test plan via API.")
    @Description("Create test plan using API functions.")
    public void createNewPlan() {
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        createCase(case_rq, "QASE");
        CreatePlanRq rq = CreatePlanRq.builder()
                .cases(new ArrayList<>(
                        Arrays.asList(1)
                ))
                .title("Plan 1")
                .description("Test plan 1")
                .build();

        Response rs = createPlan(rq, "QASE");
        Assert.assertEquals(rs.getStatus(), true);

        getSpecificPlan("QASE", "1");

        CreatePlanRq rq_upd = CreatePlanRq.builder()
                .cases(new ArrayList<>(
                        Arrays.asList(1)
                ))
                .title("Plan UPDATED")
                .build();
        updateSpecificPlan("QASE", "1", rq_upd);

        getSpecificPlan("QASE", "1");
        deleteSpecificPlan("QASE", "1");

        deleteProjectByCode("QASE");
    }
}
