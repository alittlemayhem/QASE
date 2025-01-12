package tests.api;

import models.cases.CreateCaseRq;
import models.project.CreateProjectRq;
import models.suite.CreateSuiteRq;

public class BaseAPITest {

    CreateProjectRq proj_rq = CreateProjectRq.builder()
            .title("QASE")
            .code("QASE")
            .access("all")
            .build();

    CreateSuiteRq suite_rq = CreateSuiteRq.builder()
            .title("Suite TEST")
            .build();

    CreateCaseRq case_rq = CreateCaseRq.builder()
            .title("Case UPDATED")
            .build();
}
