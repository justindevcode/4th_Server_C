package umc.assac.API_Server.dto.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

// 게시글 수정에 필요한 정보를 입력하는 Dto
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardEditDto {
    @NotBlank(message = "수정할 게시글의 내용을 입력해주세요")
    @ApiModelProperty(value = "게시글 수정에 필요한 내용", example = "test Content")
    private String content;
}
