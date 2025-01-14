package tests.api;

import io.qameta.allure.Description;
import models.Response;
import models.suite.CreateSuiteRq;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;
import static adapters.SuitAPI.*;

public class SuitTest extends BaseTest {

    @Test(testName = "Suite via API", description = "Create suite using API")
    @Description("Create suite using API")
    public void createTestSuite() {
        createProject(proj_rq);
        CreateSuiteRq suite_rq = CreateSuiteRq.builder()
                .title("Suite TEST")
                .build();
        Response rs = createSuite(suite_rq, "QASE");
        Assert.assertEquals(rs.getStatus(), true);
        Assert.assertEquals(rs.getResult().getId(), 1);

        getSpecificSuite("QASE", "1");

        deleteSpecificSuite("QASE", "1");
        deleteProjectByCode("QASE");
    }

    @Test(testName = "Update suite via API", description = "Update created suite.")
    @Description("Update created test suite using API.")
    public void updateTestSuite() {
        createProject(proj_rq);
        CreateSuiteRq rq = CreateSuiteRq.builder()
                .title("Suite for Update")
                .build();
        Response rs = createSuite(rq, "QASE");
        Assert.assertEquals(rs.getStatus(), true);
        Assert.assertEquals(rs.getResult().getId(), 1);

        getSpecificSuite("QASE", "1");

        CreateSuiteRq rq_upd = CreateSuiteRq.builder()
                .title("UPDATED suite")
                .description("Test of updating test suite")
                .build();
        updateSpecificSuite("QASE", "1", rq_upd);
        getSpecificSuite("QASE", "1");

        deleteSpecificSuite("QASE", "6");
    }
}
