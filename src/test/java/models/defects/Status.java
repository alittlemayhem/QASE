package models.defects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Status {

    @SerializedName("status")
    @Expose
    private String status;
}
