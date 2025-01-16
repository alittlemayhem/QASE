package adapters;

import io.qameta.allure.Step;
import models.Response;
import models.cases.CaseRs;
import models.cases.CreateCaseRq;
import models.cases.CreateCasesAtOnce;

public class CasesAPI extends BaseAPI {

    public static String caseUrl = BASE_URL + "case/";

    @Step("Create test case by API.")
    public static Response createCase(CreateCaseRq createCaseRq, String code) {
        return spec
                .body(gson.toJson(createCaseRq))
                .when()
                .post(caseUrl + code)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Response.class);
    }

    @Step("Create many cases at once by API")
    public static void createCasesInBulk(String code, CreateCasesAtOnce createCasesAtOnce) {
        spec
                .body(gson.toJson(createCasesAtOnce))
                .when()
                .post(caseUrl + code)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step("Get specific case by API")
    public static CaseRs getSpecificCase(String code, String caseID) {
       return spec
               .when()
               .get(caseUrl + code +"/"+ caseID)
               .then()
               .log().all()
               .statusCode(200)
               .extract()
               .as(CaseRs.class);
    }

    @Step("Get all created cases by API")
    public static void getAllCases(String code) {
        spec
                .when()
                .get(String.format(caseUrl+"%s?limit=10&offset=0", code))
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step("Update specific case by API")
    public static Response updateSpecificCase(String code, String caseID, CreateCaseRq createCaseRq) {
        return spec
                .body(createCaseRq)
                .when()
                .patch(caseUrl + code +"/"+ caseID)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Response.class);
    }

    @Step("Delete specific case by API")
    public static void deleteSpecificCase(String code, String caseID) {
        spec
                .when()
                .delete(caseUrl + code +"/"+ caseID)
                .then()
                .log().all()
                .statusCode(200);
    }
}
