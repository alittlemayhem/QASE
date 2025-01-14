package tests.api;

import io.qameta.allure.Description;
import models.Response;
import models.defects.CreateDefectRq;
import models.defects.DefectResponse;
import models.defects.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

import static adapters.DefectsAPI.*;
import static adapters.ProjectAPI.createProject;
import static adapters.ProjectAPI.deleteProjectByCode;

public class DefectTest extends BaseTest {

    @Test(testName = "Full Defect Cycle", description = "Check full defect cycle")
    @Description("Test full defect cycle using all API functions.")
    public void checkFullDefectCycle() {
        createProject(proj_rq);
        CreateDefectRq rq = CreateDefectRq.builder()
                .title("Defect 1")
                .actualResult("Result 1")
                .severity(2)
                .build();

        Response rs = createDefect(rq, "QASE");
        Assert.assertEquals(rs.getStatus(), true);

        DefectResponse createdDefect = getSpecificDefect("QASE", "1");
        Assert.assertEquals(createdDefect.getResult().getStatus(),
                "open",
                "Wrong initial defect status.");

        CreateDefectRq rq_upd = CreateDefectRq.builder()
                .title("Defect UPDATED")
                .actualResult("Result 1")
                .severity(2)
                .build();
        updateSpecificDefect("QASE", "1", rq_upd);

        DefectResponse updatedDefect = getSpecificDefect("QASE", "1");
        Assert.assertEquals(updatedDefect.getResult().getTitle(),
                "Defect UPDATED",
                "Defect title was not changed or changed incorrectly.");

        Status defectStatus = Status.builder()
                .status("in_progress")
                .build();
        updateDefectStatus("QASE", "1", defectStatus);

        resolveSpecificDefect("QASE", "1");
        DefectResponse resolvedDefect = getSpecificDefect("QASE", "1");
        Assert.assertEquals(resolvedDefect.getResult().getStatus(),
                "resolved",
                "Wrong initial defect status.");

        deleteSpecificDefect("QASE", "1");
        deleteProjectByCode("QASE");
    }
}
