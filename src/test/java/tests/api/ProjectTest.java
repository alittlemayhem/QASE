package tests.api;

import io.qameta.allure.Description;
import models.project.CreateProjectRq;
import models.project.CreateProjectRs;
import org.testng.Assert;
import org.testng.annotations.Test;

import static adapters.ProjectAPI.*;

public class ProjectTest {

    @Test(testName = "Project via API", description = "Create project using API.")
    @Description("Create new project using available API functions")
    public void createNewProject() {
        CreateProjectRq rq = CreateProjectRq.builder()
                .title("QA Test")
                .code("QA")
                .build();
        CreateProjectRs rs = createProject(rq);
        Assert.assertEquals(rs.getStatus(), true);
        Assert.assertEquals(rs.getResult().getCode(), "QA");

        getProjectByCode(rs.getResult().getCode());

        deleteProjectByCode("QA");
    }
}
