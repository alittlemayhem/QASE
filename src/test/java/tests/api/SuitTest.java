package tests.api;

import io.qameta.allure.Description;
import models.Response;
import models.suite.CreateSuiteRq;
import models.suite.SuiteRS;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.Optional;

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
        softAssert.assertEquals(Optional.ofNullable(rs.getStatus()), true);
        softAssert.assertEquals(Optional.ofNullable(rs.getResult().getId()), 1);

        SuiteRS result = getSpecificSuite("QASE", "1");
        softAssert.assertEquals(result.getResult().getTitle(),
                "Suite TEST",
                "Wrong suit name.");

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

        CreateSuiteRq rq_upd = CreateSuiteRq.builder()
                .title("UPDATED suite")
                .description("Test of updating test suite")
                .build();
        updateSpecificSuite("QASE", "1", rq_upd);
        softAssert.assertEquals(Optional.ofNullable(rs.getStatus()), true);

        SuiteRS result = getSpecificSuite("QASE", "1");
        softAssert.assertEquals(result.getResult().getTitle(),
                "UPDATED suite",
                "Title of suite was NOT updated.");

        deleteSpecificSuite("QASE", "1");
        deleteProjectByCode("QASE");
    }
}
