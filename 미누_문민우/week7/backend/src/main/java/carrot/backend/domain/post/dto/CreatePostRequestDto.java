package carrot.backend.domain.post.dto;

import carrot.backend.domain.member.entity.Member;
import carrot.backend.domain.post.entity.ItemCategory;
import carrot.backend.domain.post.entity.Post;
import carrot.backend.domain.post.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequestDto {

    private String itemCategory;
    private String title;
    private int price;
    private String content;
    private String place;

    public Post toEntity(Member member) {
        return Post.builder()
                .member(member)
                .itemCategory(ItemCategory.nameOf(itemCategory))
                .status(Status.SALE)
                .title(this.title)
                .price(this.price)
                .content(this.content)
                .place(this.place)
                .request(0)
                .like(0)
                .view(0)
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
