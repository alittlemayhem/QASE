package adapters;

import io.qameta.allure.Step;
import models.Response;
import models.defects.CreateDefectRq;
import models.defects.DefectResponse;
import models.defects.Status;

public class DefectsAPI extends BaseAPI{

    public static String defectUrl = BASE_URL + "defect/";

    @Step("Create defect by API.")
    public static Response createDefect(CreateDefectRq createDefectRq, String code) {
        return spec
                .body(gson.toJson(createDefectRq))
                .when()
                .post(defectUrl + code)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Response.class);
    }

    @Step("Get specific defect by API.")
    public static DefectResponse getSpecificDefect(String code, String defectID) {
        return spec
                .when()
                .get(defectUrl + code +"/"+ defectID)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(DefectResponse.class);
    }

    @Step("Update existing defect by API.")
    public static void updateSpecificDefect(String code, String defectID, CreateDefectRq createDefectRq) {
        spec
                .body(gson.toJson(createDefectRq))
                .when()
                .patch(defectUrl + code +"/"+ defectID)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step("Change status of defect to resolved by API.")
    public static void resolveSpecificDefect(String code, String defectID) {
        spec
                .when()
                .patch(defectUrl + code +"/resolve/"+ defectID)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step("Change defect status by API.")
    public static void updateDefectStatus(String code, String defectID, Status status) {
        spec
                .body(status)
                .when()
                .patch(defectUrl + code +"/status/"+ defectID)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Step("Delete defect by API.")
    public static void deleteSpecificDefect(String code, String defectID) {
        spec
                .when()
                .delete(defectUrl + code +"/"+ defectID)
                .then()
                .log().all()
                .statusCode(200);
    }
}
