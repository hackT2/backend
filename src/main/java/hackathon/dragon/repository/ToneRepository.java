package hackathon.dragon.repository;

import hackathon.dragon.domain.Tone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToneRepository extends JpaRepository<Tone, Long> {

}
