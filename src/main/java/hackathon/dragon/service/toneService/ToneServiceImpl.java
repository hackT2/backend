package hackathon.dragon.service.toneService;

import hackathon.dragon.apipayLoad.code.status.ErrorStatus;
import hackathon.dragon.apipayLoad.exception.handler.TempHandler;
import hackathon.dragon.domain.Tone;
import hackathon.dragon.dto.ToneDto.response.ToneResponseDto;
import hackathon.dragon.dto.ToneDto.request.ToneRequestDto;
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
    @Override
    public List<ToneResponseDto> getAllTones() {
        List<Tone> toneList = toneRepository.findAll();
        return toneList.stream()
                .map(tone -> new ToneResponseDto(
                        tone.getId(),
                        tone.getName(),
                        tone.getExplanation()))
                .collect(Collectors.toList());
    }

    @Override
    public ToneResponseDto createTone(ToneRequestDto toneRequestDto) {
        // 입력받은 explanation과 example을 기반으로 prompt 생성
        String prompt = toneRequestDto.getExplanation()
                + "말을 이쁘게 하기 위해서"
                + toneRequestDto.getExample()
                + "과 같은 방법으로 다음의 text의 말투를 변화시켜서 변화시킨 text만 출력해줘";

        // Tone 엔티티 생성 (builder 사용)
        Tone tone = Tone.builder()
                .name(toneRequestDto.getName())
                .explanation(toneRequestDto.getExplanation())
                .example(toneRequestDto.getExample())
                .prompt(prompt)
                .build();

        // DB에 저장
        Tone savedTone = toneRepository.save(tone);

        // 저장된 엔티티의 일부 필드를 응답 DTO로 변환 후 반환
        return new ToneResponseDto(savedTone.getId(), savedTone.getName(), savedTone.getExplanation());
    }

    @Override
    public void deleteTone(Long toneId) {
        Tone tone = toneRepository.findById(toneId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.TONE_NOT_FOUND));
        toneRepository.delete(tone);
    }
}
