package tests.api;

import io.qameta.allure.Description;
import models.project.CreateProjectRq;
import models.project.CreateProjectRs;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.Optional;

import static adapters.ProjectAPI.*;

public class ProjectTest extends BaseTest {

    @Test(testName = "Project via API", description = "Create project using API.")
    @Description("Create new project using available API functions")
    public void createNewProject() {
        CreateProjectRq rq = CreateProjectRq.builder()
                .title("QA Test")
                .code("QA")
                .build();
        CreateProjectRs rs = createProject(rq);
        softAssert.assertEquals(Optional.ofNullable(rs.getStatus()), true);
        softAssert.assertEquals(rs.getResult().getCode(), "QA");

        getProjectByCode(rs.getResult().getCode());

        deleteProjectByCode("QA");
    }
}
