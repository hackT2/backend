package hackathon.dragon.dto.OpenAiDto.response;

import hackathon.dragon.dto.OpenAiDto.TextMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
    private int index;
    private TextMessage message;
}
