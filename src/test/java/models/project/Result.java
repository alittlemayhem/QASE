package models.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Result {

    @SerializedName("code")
    @Expose
    private String code;
}
