package tests.api;

import models.runs.CreateRunRq;
import models.Response;
import models.cases.CreateCaseRq;
import models.project.CreateProjectRq;
import models.runs.RunResponse;
import models.suite.CreateSuiteRq;
import org.testng.Assert;
import org.testng.annotations.Test;

import static adapters.CasesAPI.createCase;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;
import static adapters.RunsAPI.*;
import static adapters.SuitAPI.createSuite;

public class RunsTest extends BaseAPITest{

    @Test
    public void createNewTestRun() {
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        createCase(case_rq, "QASE");
        CreateRunRq rq = CreateRunRq.builder()
                .title("Run 1")
                .build();

        Response rs = createRun(rq, "QASE");
        Assert.assertEquals(rs.getStatus(), true);

        RunResponse createdRun = getSpecificRun("QASE", "1");
        Assert.assertEquals(
                createdRun.getResult().getTitle(),
                "Run 1",
                "Title of test run was NOT set correctly.");
        Assert.assertEquals(
                createdRun.getResult().getStatusText(),
                "in_progress",
                "Run is in incorrect status");

        completeSpecificRun("QASE", "1");
        RunResponse completedRun = getSpecificRun("QASE", "1");
        Assert.assertEquals(
                completedRun.getResult().getStatusText(),
                "passed",
                "Run was NOT set to complete");


        deleteSpecificRun("QASE", "1");
        deleteProjectByCode("QASE");
    }
}
