package hackathon.dragon.controller;

import hackathon.dragon.apipayLoad.ApiResponse;
import hackathon.dragon.apipayLoad.exception.handler.TempHandler;
import hackathon.dragon.domain.Tone;
import hackathon.dragon.dto.ToneDto.request.ToneResponseDto;
import hackathon.dragon.service.toneService.ToneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


}
