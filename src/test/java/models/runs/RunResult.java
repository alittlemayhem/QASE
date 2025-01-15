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
public class RunResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("status_text")
    @Expose
    private String statusText;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private Object endTime;
    @SerializedName("public")
    @Expose
    private Boolean _public;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("time_spent")
    @Expose
    private Integer timeSpent;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("environment")
    @Expose
    private Object environment;
    @SerializedName("milestone")
    @Expose
    private Object milestone;
    @SerializedName("custom_fields")
    @Expose
    private List<Object> customFields;
    @SerializedName("tags")
    @Expose
    private List<Object> tags;
}
