package hackathon.dragon.service.toneChangeService;

import hackathon.dragon.apipayLoad.code.status.ErrorStatus;
import hackathon.dragon.apipayLoad.exception.handler.TempHandler;
import hackathon.dragon.domain.Tone;
import hackathon.dragon.dto.OpenAiDto.request.ChatGPTRequest;
import hackathon.dragon.dto.OpenAiDto.response.ChatGPTResponse;
import hackathon.dragon.repository.ToneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ToneChangeServiceImpl implements ToneChangeService {
    @Value("${openai.model}")
    private String apiModel;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate template;
    private final ToneRepository toneRepository;

    public List<String> requestTextAnalysis(String requestText, Long id) {
        Tone tone = toneRepository.findById(id)
                .orElseThrow(() -> new TempHandler(ErrorStatus.TONE_NOT_FOUND));
        String prompt = tone.getPrompt();
        String resultText = prompt + "text: " + requestText;
        ChatGPTRequest request = ChatGPTRequest.createTextRequest(apiModel, 500, "user", resultText);
        ChatGPTResponse response = template.postForObject(apiUrl, request, ChatGPTResponse.class);
        assert response != null;
        String chatResponseText = response.getChoices().get(0).getMessage().getContent();
        List<String> responseLines = Arrays.stream(chatResponseText.split("\\r?\\n"))
                .filter(line -> !line.trim().isEmpty())
                .toList();
        return responseLines;
    }
//    public ChatGPTResponse requestTextAnalysis(String requestText) {
//        ChatGPTRequest request = ChatGPTRequest.createTextRequest(apiModel, 500, "user", requestText);
//        return template.postForObject(apiUrl, request, ChatGPTResponse.class);
//    }

}
