package models.cases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class CreateCasesAtOnce {

    @SerializedName("cases")
    @Expose
    @Builder.Default
    private List<CreateCaseRq> cases = new ArrayList<>();
}
