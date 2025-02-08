package hackathon.dragon.controller;

import hackathon.dragon.apipayLoad.ApiResponse;
import hackathon.dragon.dto.ToneDto.request.ToneRequestDto;
import hackathon.dragon.dto.ToneDto.response.TonePromptResponseDto;
import hackathon.dragon.dto.ToneDto.response.ToneResponseDto;
import hackathon.dragon.service.toneService.ToneService;
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
    public ApiResponse<List<ToneResponseDto>> getTones() {
        List<ToneResponseDto> toneList = toneService.getAllTones();

        return ApiResponse.onSuccess(toneList);
    }

    @PostMapping
    public ApiResponse<ToneResponseDto> createTone(@RequestBody ToneRequestDto toneRequestDto) {
        ToneResponseDto toneResponseDto = toneService.createTone(toneRequestDto);
        return ApiResponse.onSuccess(toneResponseDto);
    }

    @GetMapping("/{id}/prompt")
    public ApiResponse<TonePromptResponseDto> getTonePrompt(@PathVariable Long id) {
        TonePromptResponseDto promptResponse = toneService.getTonePrompt(id);
        return ApiResponse.onSuccess(promptResponse);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTone(@PathVariable Long id) {
        toneService.deleteTone(id);
        return ApiResponse.onSuccess(null);
    }


}
