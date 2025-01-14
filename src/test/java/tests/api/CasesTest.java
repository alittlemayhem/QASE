package tests.api;

import io.qameta.allure.Description;
import models.Response;
import models.cases.CreateCaseRq;
import models.cases.CreateCasesAtOnce;
import models.cases.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

import static adapters.CasesAPI.*;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;
import static adapters.SuitAPI.createSuite;


public class CasesTest extends BaseTest {

    @Test(testName = "Test case via API", description = "Test case creation using API.")
    @Description("Create test case with title and steps using API.")
    public void createTestCase() {
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        CreateCaseRq rq = CreateCaseRq.builder()
                .title("Case 1")
                .steps(new ArrayList<>(
                        Arrays.asList(
                                new Step("action1", "result1", "data1")
                        ))
                )
                .build();

        Response rs = createCase(rq, "QASE");
        Assert.assertEquals(rs.getStatus(), true);
        Assert.assertEquals(rs.getResult().getId(), 1);

        CreateCaseRq result = getSpecificCase("QASE", "1");
        Assert.assertEquals(result.getTitle(), "Case 1");

        deleteSpecificCase("QASE", "1");
        deleteProjectByCode("QASE");
    }

    @Test(testName = "Update case via API", description = "Update created case.")
    @Description("Update created test case using API.")
    public void updateTestcase() {
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        CreateCaseRq rq = CreateCaseRq.builder()
                .title("Case 1")
                .steps(new ArrayList<>(
                        Arrays.asList(
                                new Step("action1", "result1", "data1")
                        ))
                )
                .build();

        Response rs = createCase(rq, "QASE");
        Assert.assertEquals(rs.getStatus(), true);

        CreateCaseRq result = getSpecificCase("QASE", "1");
        Assert.assertEquals(
                result.getTitle(),
                "Case 1",
                "Incorrect case title.");

        CreateCaseRq rq_upd = CreateCaseRq.builder()
                .title("Case UPDATED")
                .build();
        Response rs_upd = updateSpecificCase("QASE", "1", rq_upd);
        Assert.assertEquals(rs_upd.getStatus(), true);

        CreateCaseRq result_upd = getSpecificCase("QASE", "1");
        Assert.assertEquals(
                result_upd.getTitle(),
                "Case UPDATED",
                "Case title was NOT updated.");


        deleteProjectByCode("QASE");
    }

    @Test(testName = "Many cases via API", description = "Create bunch of test cases at once")
    @Description("Create 3 test cases using one API call.")
    public void createManyCases() {
        createProject(proj_rq);
        createSuite(suite_rq, "QASE");
        ArrayList<CreateCaseRq> cases = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            CreateCaseRq rq = CreateCaseRq.builder()
                    .title(String.format("Case %d", i))
                    .build();
            cases.add(rq);
        }

        CreateCasesAtOnce many_cases = CreateCasesAtOnce.builder()
                .cases(cases)
                .build();

        createCasesInBulk("QASE", many_cases);
        getAllCases("QASE");
    }
}
