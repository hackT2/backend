package hackathon.dragon.service.toneChangeService;

import hackathon.dragon.dto.OpenAiDto.request.ChatGPTRequest;
import hackathon.dragon.dto.OpenAiDto.response.ChatGPTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class ToneChangeServiceImpl implements ToneChangeService {
    @Value("${openai.model}")
    private String apiModel;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate template;

    public ChatGPTResponse requestTextAnalysis(String requestText) {
        ChatGPTRequest request = ChatGPTRequest.createTextRequest(apiModel, 500, "user", requestText);
        return template.postForObject(apiUrl, request, ChatGPTResponse.class);
    }

}
