package carrot.backend.domain.post.dto;

import carrot.backend.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostInfoResponseDto {

    private SimplePostInfoResponseDto simplePostInfoResponseDto;
    private String itemCategory;
    private String content;

    public static PostInfoResponseDto toDto(Post post) {
        return PostInfoResponseDto.builder()
                .simplePostInfoResponseDto(SimplePostInfoResponseDto.toDto(post))
                .itemCategory(post.getItemCategory().getName())
                .content(post.getContent())
                .build();
    }
}
