package hackathon.dragon.dto.OpenAiDto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import hackathon.dragon.dto.OpenAiDto.Message;
import hackathon.dragon.dto.OpenAiDto.TextMessage;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatGPTRequest {
    @JsonProperty("model")
    private String model;
    @JsonProperty("messages")
    private List<Message> messages;
    @JsonProperty("max_tokens")
    private int maxTokens;


    public static ChatGPTRequest createTextRequest(String model, int maxTokens, String role, String requestText) {
        Message message = new TextMessage(role, requestText);
        return createChatGPTRequest(model, maxTokens, Collections.singletonList(message));
    }

    private static ChatGPTRequest createChatGPTRequest(String model, int maxTokens, List<Message> messages) {
        return ChatGPTRequest.builder()
                .model(model)
                .maxTokens(maxTokens)
                .messages(messages)
                .build();
    }
}