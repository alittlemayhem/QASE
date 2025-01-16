package models.runresults;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRunResRq {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("case_id")
    @Expose
    private Integer caseId;
}
