package umc.assac.API_Server.dto.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

// 댓글을 생성, 수정하는 요청에 활용되는 Dto
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentRequestDto {
    @NotBlank(message = "댓글의 내용을 입력해주세요")
    @ApiModelProperty(value = "생성 혹은 수정할 댓글의 내용", example = "test")
    private String content;
}
