package tests.api;

import io.qameta.allure.Description;
import models.Response;
import models.cases.CaseRs;
import models.cases.CreateCaseRq;
import models.cases.Step;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static adapters.CasesAPI.*;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;
import static adapters.SuitAPI.createSuite;


public class CasesTest extends BaseTest {

    CreateCaseRq rq = CreateCaseRq.builder()
            .title("Case 1")
            .steps(new ArrayList<>(
                    List.of(
                            new Step("action1", "result1", "data1")
                    ))
            )
            .build();

    CreateCaseRq rq_upd = CreateCaseRq.builder()
            .title("Case UPDATED")
            .build();

    @Test(testName = "Test case via API", description = "Test case creation using API.")
    @Description("Create test case with title and steps using API.")
    public void createTestCase() {
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        Response rs = createCase(rq, "QASE");

        softAssert.assertEquals(Optional.ofNullable(rs.getResult().getId()), 1);

        CaseRs result = getSpecificCase("QASE", "1");
        softAssert.assertEquals(result.getResult().getTitle(), "Case 1");

        deleteSpecificCase("QASE", "1");
        deleteProjectByCode("QASE");
    }

    @Test(testName = "Update case via API", description = "Update created case.")
    @Description("Update created test case using API.")
    public void updateTestcase() {
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        Response rs = createCase(rq, "QASE");
        softAssert.assertEquals(Optional.ofNullable(rs.getStatus()), true);

        CaseRs result = getSpecificCase("QASE", "1");
        softAssert.assertEquals(
                result.getResult().getTitle(),
                "Case 1",
                "Incorrect case title.");

        updateSpecificCase("QASE", "1", rq_upd);

        CaseRs result_upd = getSpecificCase("QASE", "1");
        softAssert.assertEquals(
                result_upd.getResult().getTitle(),
                "Case UPDATED",
                "Case title was NOT updated.");

        deleteProjectByCode("QASE");
    }
}
