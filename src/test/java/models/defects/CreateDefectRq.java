package models.defects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateDefectRq {

    @SerializedName(value = "title")
    @Expose
    private String title;
    @SerializedName(value = "actual_result")
    @Expose
    private String actualResult;
    @SerializedName(value = "severity")
    @Expose
    private Integer severity;
}
