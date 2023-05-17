package umc.test.board.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.test.board.domain.Post;
import umc.test.board.domain.User;

@Getter
@NoArgsConstructor
public class PostCreateRequestDto {
    private int user_id;
    private String title;
    private String content;

    @Builder
    public PostCreateRequestDto(int user_id, String title, String content) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .user_id(user_id)
                .title(title)
                .content(content)
                .build();
    }
}
