package adapters;

import io.qameta.allure.Step;
import models.runs.CreateRunRq;
import models.Response;
import models.runs.RunResponse;

public class RunsAPI extends BaseAPI{

    public static String runUrl = BASE_URL + "run/";

    @Step("Create test run by API.")
    public static Response createRun(CreateRunRq createRunRq, String code) {
        return spec
                .body(gson.toJson(createRunRq))
                .when()
                .post(runUrl + code)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Response.class);
    }

    @Step("Get test run by API.")
    public static RunResponse getSpecificRun(String code, String runID) {
        return spec
                .when()
                .get(runUrl + code +"/"+ runID)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(RunResponse.class);
    }

    @Step("Complete test run by API.")
    public static void completeSpecificRun(String code, String runID) {
        spec
                .when()
                .post(runUrl + code + "/" + runID + "/complete")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step("Delete test run by API.")
    public static void deleteSpecificRun(String code, String runID) {
        spec
                .when()
                .delete(runUrl + code +"/"+ runID)
                .then()
                .log().all()
                .statusCode(200);
    }
}
