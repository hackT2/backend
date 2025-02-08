package hackathon.dragon.controller;

import java.io.IOException;

import hackathon.dragon.apipayLoad.ApiResponse;
import hackathon.dragon.service.SpeechToTextService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/stt")
public class SttRestController {

    @Autowired
    private SpeechToTextService sttService;
    /**
     * 오디오 파일을 받아서 텍스트로 변환하여 반환
     */
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "음성파일 텍스트로 변환", description = "텍스트반환")
    public ApiResponse<String> handleAudioMessage(@RequestParam("audioFile") MultipartFile audioFile) throws IOException {
        String transcribe = sttService.transcribe(audioFile);
        return ApiResponse.onSuccess(transcribe);
    }

}