package tests.api;

import io.qameta.allure.Description;
import models.Response;
import models.defects.CreateDefectRq;
import models.defects.DefectResponse;
import models.defects.Status;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.Optional;

import static adapters.DefectsAPI.*;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;

public class DefectTest extends BaseTest {

    @Test
    public void runOnfail() {
        deleteProjectByCode("QASE");
    }

    CreateDefectRq rq = CreateDefectRq.builder()
            .title("Defect 1")
            .actualResult("Result 1")
            .severity(2)
            .build();

    CreateDefectRq rq_upd = CreateDefectRq.builder()
            .title("Defect UPDATED")
            .actualResult("Result 1")
            .severity(2)
            .build();

    @Test(testName = "Create Defect", description = "Check defect creation")
    @Description("Test full defect cycle using all API functions.")
    public void checkCreateDefect() {
        createProject(proj_rq);
        Response rs = createDefect(rq, "QASE");
        softAssert.assertEquals(Optional.ofNullable(rs.getStatus()), true);

        DefectResponse createdDefect = getSpecificDefect("QASE", "1");
        softAssert.assertEquals(createdDefect.getResult().getStatus(),
                "open",
                "Wrong initial defect status.");

        deleteSpecificDefect("QASE", "1");
        deleteProjectByCode("QASE");
    }

    @Test(testName = "Update defect", description = "Check defect modification")
    @Description("Test full defect cycle using all API functions.")
    public void checkUpdateDefect() {
        createProject(proj_rq);
        createDefect(rq, "QASE");

        updateSpecificDefect("QASE", "1", rq_upd);

        DefectResponse updatedDefect = getSpecificDefect("QASE", "1");
        softAssert.assertEquals(updatedDefect.getResult().getTitle(),
                "Defect UPDATED",
                "Defect title was not changed or changed incorrectly.");
        softAssert.assertEquals(updatedDefect.getResult().getStatus(),
                "open",
                "Status of defect has changed during title update.");

        deleteSpecificDefect("QASE", "1");
        deleteProjectByCode("QASE");
    }

    @Test(testName = "Change defect status", description = "Change defect status")
    @Description("Test full defect cycle using all API functions.")
    public void checkChangeDefectStatus() {
        createProject(proj_rq);
        createDefect(rq, "QASE");

        Status defectStatus = Status.builder()
                .status("in_progress")
                .build();
        updateDefectStatus("QASE", "1", defectStatus);

        DefectResponse updatedDefect = getSpecificDefect("QASE", "1");
        softAssert.assertEquals(updatedDefect.getResult().getStatus(),
                "in_progress",
                "Status of defect was NOT changed.");

        deleteSpecificDefect("QASE", "1");
        deleteProjectByCode("QASE");
    }

    @Test(testName = "Resolve Defect", description = "Check defect resolving")
    @Description("Test full defect cycle using all API functions.")
    public void checkResolveDefect() {
        createProject(proj_rq);
        createDefect(rq, "QASE");

        resolveSpecificDefect("QASE", "1");
        DefectResponse resolvedDefect = getSpecificDefect("QASE", "1");
        softAssert.assertEquals(resolvedDefect.getResult().getStatus(),
                "resolved",
                "Wrong initial defect status.");

        deleteSpecificDefect("QASE", "1");
        deleteProjectByCode("QASE");
    }

    @Test(testName = "Full Defect Cycle", description = "Check full defect cycle")
    @Description("Test full defect cycle using all API functions.")
    public void checkFullDefectCycle() {
        createProject(proj_rq);
        createDefect(rq, "QASE");

        updateSpecificDefect("QASE", "1", rq_upd);

        Status defectStatus = Status.builder()
                .status("in_progress")
                .build();
        updateDefectStatus("QASE", "1", defectStatus);

        resolveSpecificDefect("QASE", "1");

        deleteSpecificDefect("QASE", "1");
        deleteProjectByCode("QASE");
    }
}
