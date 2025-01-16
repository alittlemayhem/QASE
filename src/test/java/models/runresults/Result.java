package models.runresults;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Result {

    @SerializedName("case_id")
    @Expose
    private Integer caseId;
    @SerializedName("hash")
    @Expose
    private String hash;
}
