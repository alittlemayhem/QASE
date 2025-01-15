package models.runresults;

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
public class CreateRunResRq {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("case_id")
    @Expose
    private Integer caseId;
}
