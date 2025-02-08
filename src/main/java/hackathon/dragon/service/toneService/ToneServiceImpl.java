package hackathon.dragon.service.toneService;

import hackathon.dragon.domain.Tone;
import hackathon.dragon.dto.ToneDto.request.ToneResponseDto;
import hackathon.dragon.repository.ToneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToneServiceImpl implements ToneService{
    private final ToneRepository toneRepository;

    /**
     * DB에 저장된 모든 Tone 엔티티를 조회하여, id, name, explanation 정보만 포함한 DTO 리스트로 변환 후 반환.
     */
    public List<ToneResponseDto> getAllTones() {
        List<Tone> toneList = toneRepository.findAll();
        return toneList.stream()
                .map(tone -> new ToneResponseDto(
                        tone.getId(),
                        tone.getName(),
                        tone.getExplanation()))
                .collect(Collectors.toList());
    }
}
