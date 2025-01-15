package models.defects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
