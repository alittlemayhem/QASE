package adapters;

import models.project.CreateProjectRq;
import models.project.CreateProjectRs;

public class ProjectAPI extends BaseAPI{

    public static String projectUrl = BASE_URL + "project/";

    public static CreateProjectRs createProject(CreateProjectRq createProjectRq) {
        return spec
                .body(createProjectRq)
                .when()
                .post(projectUrl)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(CreateProjectRs.class);
    }

    public static void deleteProjectByCode(String code) {
       spec
                .when()
                .delete(projectUrl + code)
                .then()
                .log().all()
                .statusCode(200);
    }

    public static void getProjectByCode(String code) {
        spec
                .when()
                .get(projectUrl + code)
                .then()
                .log().all()
                .statusCode(200);
    }
}
