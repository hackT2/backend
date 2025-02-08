package hackathon.dragon.dto.ToneDto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TonePromptResponseDto {
    private Long id;
    private String name;
    private String explanation;
    private String longExplanation;
    private String example;
    private String prompt;
}
