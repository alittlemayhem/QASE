package adapters;

import models.Response;
import models.defects.CreateDefectRq;
import models.defects.DefectResponse;
import models.defects.Status;

public class DefectsAPI extends BaseAPI{

    public static String defectUrl = BASE_URL + "defect/";

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

    public static void updateSpecificDefect(String code, String defectID, CreateDefectRq createDefectRq) {
        spec
                .body(gson.toJson(createDefectRq))
                .when()
                .patch(defectUrl + code +"/"+ defectID)
                .then()
                .log().all()
                .statusCode(200);
    }

    public static void resolveSpecificDefect(String code, String defectID) {
        spec
                .when()
                .patch(defectUrl + code +"/resolve/"+ defectID)
                .then()
                .log().all()
                .statusCode(200);
    }

    public static void updateDefectStatus(String code, String defectID, Status status) {
        spec
                .body(status)
                .when()
                .patch(defectUrl + code +"/status/"+ defectID)
                .then()
                .log().all()
                .statusCode(200);
    }

    public static void deleteSpecificDefect(String code, String defectID) {
        spec
                .when()
                .delete(defectUrl + code +"/"+ defectID)
                .then()
                .log().all()
                .statusCode(200);
    }
}
