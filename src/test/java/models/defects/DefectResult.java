package models.defects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class DefectResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("actual_result")
    @Expose
    private String actualResult;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("milestone_id")
    @Expose
    private Object milestoneId;
    @SerializedName("severity")
    @Expose
    private String severity;
    @SerializedName("member_id")
    @Expose
    private Integer memberId;
    @SerializedName("author_id")
    @Expose
    private Integer authorId;
    @SerializedName("attachments")
    @Expose
    private List<Object> attachments;
    @SerializedName("custom_fields")
    @Expose
    private List<Object> customFields;
    @SerializedName("external_data")
    @Expose
    private String externalData;
    @SerializedName("runs")
    @Expose
    private List<Object> runs;
    @SerializedName("results")
    @Expose
    private List<Object> results;
    @SerializedName("resolved_at")
    @Expose
    private String resolvedAt;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("tags")
    @Expose
    private List<Object> tags;
}
