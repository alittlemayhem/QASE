package adapters;

import models.Response;
import models.cases.CaseRs;
import models.cases.CreateCaseRq;
import models.cases.CreateCasesAtOnce;

public class CasesAPI extends BaseAPI {

    public static String caseUrl = BASE_URL + "case/";

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

    public static void createCasesInBulk(String code, CreateCasesAtOnce createCasesAtOnce) {
        spec
                .body(gson.toJson(createCasesAtOnce))
                .when()
                .post(caseUrl + code)
                .then()
                .log().all()
                .statusCode(200);
    }

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

    public static void getAllCases(String code) {
        spec
                .when()
                .get(String.format(caseUrl+"%s?limit=10&offset=0", code))
                .then()
                .log().all()
                .statusCode(200);
    }

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

    public static void deleteSpecificCase(String code, String caseID) {
        spec
                .when()
                .delete(caseUrl + code +"/"+ caseID)
                .then()
                .log().all()
                .statusCode(200);
    }
}
