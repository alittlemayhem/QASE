package models.runs;

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
public class CreateRunRq {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("include_all_cases")
    @Expose
    private Boolean includeAllCases;
    @SerializedName("cases")
    @Expose
    private List<Integer> cases;
    @SerializedName("is_autotest")
    @Expose
    private Boolean isAutotest;
    @SerializedName("plan_id")
    @Expose
    private Integer planId;
    @SerializedName("environment_id")
    @Expose
    private Integer environmentId;
    @SerializedName("environment_slug")
    @Expose
    private String environmentSlug;
    @SerializedName("milestone_id")
    @Expose
    private Integer milestoneId;
    @SerializedName("author_id")
    @Expose
    private Integer authorId;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
}
