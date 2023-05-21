package carrot.backend.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPostInfoRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private int price;

    private String content;

    @NotBlank(message = "거래할 위치 정보를 입력해주세요.")
    private String place;
}
