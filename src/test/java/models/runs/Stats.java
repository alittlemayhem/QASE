package models.runs;

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
public class Stats {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("statuses")
    @Expose
    private Statuses statuses;
    @SerializedName("untested")
    @Expose
    private Integer untested;
    @SerializedName("passed")
    @Expose
    private Integer passed;
    @SerializedName("failed")
    @Expose
    private Integer failed;
    @SerializedName("blocked")
    @Expose
    private Integer blocked;
    @SerializedName("skipped")
    @Expose
    private Integer skipped;
    @SerializedName("retest")
    @Expose
    private Integer retest;
    @SerializedName("in_progress")
    @Expose
    private Integer inProgress;
    @SerializedName("invalid")
    @Expose
    private Integer invalid;
}
