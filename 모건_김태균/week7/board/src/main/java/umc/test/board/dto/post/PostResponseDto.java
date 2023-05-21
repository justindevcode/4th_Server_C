package umc.test.board.dto.post;

import lombok.Getter;
import umc.test.board.domain.Post;

@Getter
public class PostResponseDto {
    private Long id;
    private int user_id;
    private String title;
    private String content;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.user_id = entity.getUser_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}