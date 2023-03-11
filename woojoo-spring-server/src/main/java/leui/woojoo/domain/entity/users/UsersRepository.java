package leui.woojoo.domain.entity.users;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByPhoneNumber(String phoneNumber);

    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findOneWithAuthoritiesById(Long userId);
}