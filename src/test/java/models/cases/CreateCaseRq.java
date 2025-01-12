package models.cases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CreateCaseRq {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("preconditions")
    @Expose
    private String preconditions;
    @SerializedName("postconditions")
    @Expose
    private String postconditions;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("severity")
    @Expose
    private Integer severity;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("behavior")
    @Expose
    private Integer behavior;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("layer")
    @Expose
    private Integer layer;
    @SerializedName("is_flaky")
    @Expose
    private Integer isFlaky;
    @SerializedName("author_id")
    @Expose
    private Integer authorId;
    @SerializedName("suite_id")
    @Expose
    private Integer suiteId;
    @SerializedName("milestone_id")
    @Expose
    private Integer milestoneId;
    @SerializedName("automation")
    @Expose
    private Integer automation;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("steps")
    @Expose
    @Builder.Default
    private List<Step> steps = new ArrayList<Step>();
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
}
