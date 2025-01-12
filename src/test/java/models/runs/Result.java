package models.runs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Result {

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
    private String endTime;
    @SerializedName("public")
    @Expose
    private Boolean _public;
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
}
