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
public class SimplePostInfoResponseDto {

    private Long id;
    private String title;
    private String place;
    private int price;
    private int request;
    private int like;
    private int view;

    public static SimplePostInfoResponseDto toDto(Post post) {
        return SimplePostInfoResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .place(post.getPlace())
                .price(post.getPrice())
                .request(post.getRequest())
                .like(post.getLike())
                .view(post.getView())
                .build();
    }
}
