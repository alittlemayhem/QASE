package adapters;

import io.qameta.allure.Step;
import models.CreatePlanRq;
import models.Response;

public class PlansAPI extends BaseAPI{

    public static String planUrl = BASE_URL + "plan/";

    @Step("Create test plan by API.")
    public static Response createPlan(CreatePlanRq createPlanRq, String code) {
        return spec
                .body(createPlanRq)
                .when()
                .post(planUrl + code)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Response.class);
    }

    @Step("Get test plan by API.")
    public static String getSpecificPlan(String code, String planID) {
        return spec
                .when()
                .get(planUrl + code +"/"+ planID)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .asString();
    }

    @Step("Update test plan by API.")
    public static void updateSpecificPlan(String code, String planID, CreatePlanRq createPlanRq) {
        spec
                .body(createPlanRq)
                .when()
                .patch(planUrl + code +"/"+ planID)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step("Delete test plan by API.")
    public static void deleteSpecificPlan(String code, String planID) {
        spec
                .when()
                .delete(planUrl + code +"/"+ planID)
                .then()
                .log().all()
                .statusCode(200);
    }
}
