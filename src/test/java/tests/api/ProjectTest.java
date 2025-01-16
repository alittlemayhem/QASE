package tests.api;

import models.project.CreateProjectRq;
import models.project.CreateProjectRs;
import org.testng.Assert;
import org.testng.annotations.Test;

import static adapters.ProjectAPI.*;

public class ProjectTest {

    @Test
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
