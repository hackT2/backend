package hackathon.dragon.dto.ToneDto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToneRequestDto {
    private String name;
    private String explanation;
    private String example;
    private String longExplanation;
}