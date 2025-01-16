package models.cases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseStep {

    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("shared_step_hash")
    @Expose
    private Object sharedStepHash;
    @SerializedName("shared_step_nested_hash")
    @Expose
    private Object sharedStepNestedHash;
    @SerializedName("attachments")
    @Expose
    private List<Object> attachments;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("expected_result")
    @Expose
    private String expectedResult;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("steps")
    @Expose
    private List<Object> steps;

}
