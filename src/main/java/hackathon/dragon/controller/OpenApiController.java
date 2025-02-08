package hackathon.dragon.controller;


import hackathon.dragon.apipayLoad.ApiResponse;
import hackathon.dragon.dto.OpenAiDto.response.ChatGPTResponse;
import hackathon.dragon.service.toneChangeService.ToneChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenApiController {

    private final ToneChangeService toneChangeService;

    @PostMapping("/text/{id}")
    public ApiResponse<List<String>> textAnalysis(
            @PathVariable Long id,
            @RequestParam String requestText) {
        List<String> responseLines = toneChangeService.requestTextAnalysis(requestText, id);
        return ApiResponse.onSuccess(responseLines);
    }

//    @PostMapping("/text")
//    public String textAnalysis(@RequestParam String requestText) {
//        ChatGPTResponse response = toneChangeService.requestTextAnalysis(requestText);
//        return response.getChoices().get(0).getMessage().getContent();
//    }
}
