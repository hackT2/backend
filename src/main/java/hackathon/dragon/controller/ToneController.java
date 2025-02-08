package hackathon.dragon.controller;

import hackathon.dragon.apipayLoad.ApiResponse;
import hackathon.dragon.dto.ToneDto.request.ToneRequestDto;
import hackathon.dragon.dto.ToneDto.response.TonePromptResponseDto;
import hackathon.dragon.dto.ToneDto.response.ToneResponseDto;
import hackathon.dragon.service.toneService.ToneService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tone")
@RequiredArgsConstructor
public class ToneController {
    private final ToneService toneService;

    @GetMapping
    @Operation(summary = "tone 전체 조회", description = "get으로 tone id name explanation조회")
    public ApiResponse<List<ToneResponseDto>> getTones() {
        List<ToneResponseDto> toneList = toneService.getAllTones();

        return ApiResponse.onSuccess(toneList);
    }

    @PostMapping
    @Operation(summary = "tone 생성", description = "name 짧은설명 예시 긴설명으로 tone 생성")
    public ApiResponse<ToneResponseDto> createTone(@RequestBody ToneRequestDto toneRequestDto) {
        ToneResponseDto toneResponseDto = toneService.createTone(toneRequestDto);
        return ApiResponse.onSuccess(toneResponseDto);
    }

    @GetMapping("/{id}/prompt")
    @Operation(summary = "tone 프롬프트반환 테스트용", description = "id로 프롬프트 반환")
    public ApiResponse<TonePromptResponseDto> getTonePrompt(@PathVariable Long id) {
        TonePromptResponseDto promptResponse = toneService.getTonePrompt(id);
        return ApiResponse.onSuccess(promptResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "tone 삭제", description = "id로 해당 tone삭제")
    public ApiResponse<Void> deleteTone(@PathVariable Long id) {
        toneService.deleteTone(id);
        return ApiResponse.onSuccess(null);
    }


}
