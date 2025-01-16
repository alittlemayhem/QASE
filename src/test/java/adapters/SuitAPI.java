package adapters;

import io.qameta.allure.Step;
import models.Response;
import models.suite.CreateSuiteRq;
import models.suite.SuiteRS;

public class SuitAPI extends BaseAPI{

    public static String suitUrl = BASE_URL + "suite/";

    @Step("Create test suite by API.")
    public static Response createSuite(CreateSuiteRq createSuiteRq, String code) {
        return spec
                .body(createSuiteRq)
                .when()
                .post(suitUrl + code)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Response.class);
    }

    @Step("Get test suite by API.")
    public static SuiteRS getSpecificSuite(String code, String suitID) {
        return spec
                .when()
                .get(suitUrl+ code +"/"+ suitID)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(SuiteRS.class);
    }

    @Step("Update test suite by API.")
    public static void updateSpecificSuite(String code, String suitID, CreateSuiteRq createSuiteRq) {
        spec
                .body(createSuiteRq)
                .when()
                .patch(suitUrl + code +"/"+ suitID)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step("Delete test suite by API.")
    public static void deleteSpecificSuite(String code, String suitID) {
        spec
                .when()
                .delete(suitUrl + code +"/"+ suitID)
                .then()
                .log().all()
                .statusCode(200);
    }
}
