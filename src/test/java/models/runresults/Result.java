package models.runresults;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @SerializedName("case_id")
    @Expose
    private Integer caseId;
    @SerializedName("hash")
    @Expose
    private String hash;
}
