package hackathon.dragon.repository;

import hackathon.dragon.domain.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE) // 비관적 잠금 적용
    Optional<User> findByKakaoEmail(String email);
}