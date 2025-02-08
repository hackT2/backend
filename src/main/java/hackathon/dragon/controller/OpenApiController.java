package hackathon.dragon.controller;


import hackathon.dragon.dto.OpenAiDto.response.ChatGPTResponse;
import hackathon.dragon.service.toneChangeService.ToneChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenApiController {
    private final ToneChangeService toneChangeService;
    @PostMapping("/text")
    public String textAnalysis(@RequestParam String requestText) {
        ChatGPTResponse response = toneChangeService.requestTextAnalysis(requestText);
        return response.getChoices().get(0).getMessage().getContent();
    }
}
