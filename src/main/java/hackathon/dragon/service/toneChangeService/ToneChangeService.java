package hackathon.dragon.service.toneChangeService;

import hackathon.dragon.dto.OpenAiDto.response.ChatGPTResponse;

public interface ToneChangeService {
    ChatGPTResponse requestTextAnalysis(String requestText);
}
