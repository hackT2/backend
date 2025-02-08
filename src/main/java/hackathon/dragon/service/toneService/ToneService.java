package hackathon.dragon.service.toneService;

import hackathon.dragon.dto.ToneDto.request.ToneRequestDto;
import hackathon.dragon.dto.ToneDto.response.TonePromptResponseDto;
import hackathon.dragon.dto.ToneDto.response.ToneResponseDto;

import java.util.List;

public interface ToneService {

    List<ToneResponseDto> getAllTones();

    ToneResponseDto createTone(ToneRequestDto toneRequestDto);

    TonePromptResponseDto getTonePrompt(Long toneId);

    void deleteTone(Long toneId);
}
