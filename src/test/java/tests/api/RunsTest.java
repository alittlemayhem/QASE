package tests.api;

import io.qameta.allure.Description;
import models.runs.CreateRunRq;
import models.Response;
import models.runs.RunResponse;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.Optional;

import static adapters.CasesAPI.createCase;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;
import static adapters.RunsAPI.*;
import static adapters.SuitAPI.createSuite;

public class RunsTest extends BaseTest {

    @Test(testName = "Run via API", description = "Create test run by API")
    @Description("Create test run using API functions.")
    public void createNewTestRun() {
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        createCase(case_rq, "QASE");
        CreateRunRq rq = CreateRunRq.builder()
                .title("Run 1")
                .build();

        Response rs = createRun(rq, "QASE");
        softAssert.assertEquals(Optional.ofNullable(rs.getStatus()), true);

        RunResponse createdRun = getSpecificRun("QASE", "1");
        softAssert.assertEquals(
                createdRun.getResult().getTitle(),
                "Run 1",
                "Title of test run was NOT set correctly.");
        softAssert.assertEquals(
                createdRun.getResult().getStatusText(),
                "in_progress",
                "Run is in incorrect status");

        deleteSpecificRun("QASE", "1");
        deleteProjectByCode("QASE");
    }

    @Test(testName = "Run via API", description = "Create test run by API")
    @Description("Create test run using API functions.")
    public void completeTestRun() {
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        createCase(case_rq, "QASE");
        CreateRunRq rq = CreateRunRq.builder()
                .title("Run 1")
                .build();
        createRun(rq, "QASE");

        completeSpecificRun("QASE", "1");
        RunResponse completedRun = getSpecificRun("QASE", "1");
        softAssert.assertEquals(
                completedRun.getResult().getStatusText(),
                "passed",
                "Run was NOT set to complete");
        softAssert.assertEquals(
                completedRun.getResult().getTitle(),
                "Run 1",
                "Title has changed after complete.");

        deleteSpecificRun("QASE", "1");
        deleteProjectByCode("QASE");
    }
}
