package models.cases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Step {

    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("expected_result")
    @Expose
    private String expectedResult;
    @SerializedName("data")
    @Expose
    private String data;
}
