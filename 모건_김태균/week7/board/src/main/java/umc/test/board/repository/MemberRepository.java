package umc.test.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.test.board.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String username);
}
