package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseAPI {

    public static String token = System.getProperty("token", PropertyReader.getProperty("token"));
    public static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation().create();

    public static RequestSpecification spec = given()
            .log().all()
            .contentType(ContentType.JSON)
            .header("Token", token);

    public static final String BASE_URL = "https://api.qase.io/v1/";
}
