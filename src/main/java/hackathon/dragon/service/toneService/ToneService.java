package hackathon.dragon.service.toneService;

import hackathon.dragon.dto.ToneDto.request.ToneResponseDto;

import java.util.List;

public interface ToneService {

    List<ToneResponseDto> getAllTones();
}
