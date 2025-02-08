package hackathon.dragon.controller;

import java.io.IOException;
import java.util.List;

import hackathon.dragon.apipayLoad.ApiResponse;
import hackathon.dragon.service.SpeechToTextService;
import hackathon.dragon.service.toneChangeService.ToneChangeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stt")
public class SttRestController {

    @Autowired
    private SpeechToTextService sttService;

    private final ToneChangeService toneChangeService;
    /**
     * 오디오 파일을 받아서 텍스트로 변환하여 반환
     */
    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "음성파일 텍스트로 변환", description = "텍스트반환")
    public ApiResponse<List<String>> handleAudioMessage(
            @PathVariable Long id,
            @RequestParam("audioFile") MultipartFile audioFile) throws IOException {
        String transcribe = sttService.transcribe(audioFile);
        List<String> responseLines = toneChangeService.requestTextAnalysis(transcribe, id);
        return ApiResponse.onSuccess(responseLines);
    }

}