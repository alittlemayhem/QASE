package adapters;

import models.Response;
import models.suite.CreateSuiteRq;

public class SuitAPI extends BaseAPI{

    public static String suitUrl = BASE_URL + "suite/";

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

    public static void getSpecificSuite(String code, String suitID) {
        spec
                .when()
                .get(suitUrl+ code +"/"+ suitID)
                .then()
                .log().all()
                .statusCode(200);
    }

    public static void updateSpecificSuite(String code, String suitID, CreateSuiteRq createSuiteRq) {
        spec
                .body(createSuiteRq)
                .when()
                .patch(suitUrl + code +"/"+ suitID)
                .then()
                .log().all()
                .statusCode(200);
    }

    public static void deleteSpecificSuite(String code, String suitID) {
        spec
                .when()
                .delete(suitUrl + code +"/"+ suitID)
                .then()
                .log().all()
                .statusCode(200);
    }
}
