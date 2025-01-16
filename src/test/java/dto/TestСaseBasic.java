package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class TestСaseBasic {

    private final String title;
    @Builder.Default
    private final String status = "Actual";
    @Builder.Default
    private final String severity = "Normal";
    @Builder.Default
    private final String priority = "Not set";
    @Builder.Default
    private final String type = "Other";
    @Builder.Default
    private final String layer = "Not set";
    @Builder.Default
    private final String isFlaky = "No";
    @Builder.Default
    private final String behavior = "Not set";
    @Builder.Default
    private final String autoStatus = "Manual";
    @Builder.Default
    private final String toBeAuto = "unchecked";
    @Builder.Default
    private final String muted = "unchecked";
}
