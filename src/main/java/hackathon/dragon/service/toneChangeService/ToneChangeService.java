package hackathon.dragon.service.toneChangeService;

import hackathon.dragon.dto.OpenAiDto.response.ChatGPTResponse;

import java.util.List;

public interface ToneChangeService {
    List<String> requestTextAnalysis(String requestText, Long id);
}
