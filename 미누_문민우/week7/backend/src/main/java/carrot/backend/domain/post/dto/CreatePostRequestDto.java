package carrot.backend.domain.post.dto;

import carrot.backend.domain.member.entity.Member;
import carrot.backend.domain.post.entity.ItemCategory;
import carrot.backend.domain.post.entity.Post;
import carrot.backend.domain.post.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequestDto {

    @NotBlank(message = "판매할 물품의 카테고리를 선택해주세요.")
    private String itemCategory;
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private int price;
    private String content;
    @NotBlank(message = "거래할 위치 정보를 입력해주세요.")
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
