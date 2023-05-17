package carrot.backend.domain.post.repository;

import carrot.backend.domain.member.entity.Member;
import carrot.backend.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface PostRepository extends JpaRepository<Post, Long> {
    @NotNull
    Page<Post> findAll(@NotNull Pageable pageable);
    @EntityGraph(attributePaths = {"member"})
    @NotNull
    Page<Post> findPostsByMember(@NotNull Pageable pageable, Member member);
}
