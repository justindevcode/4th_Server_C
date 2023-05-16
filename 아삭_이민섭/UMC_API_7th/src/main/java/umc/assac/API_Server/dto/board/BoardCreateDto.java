package umc.assac.API_Server.dto.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

// 게시글 작성에 필요한 정보들을 입력하는 Dto
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardCreateDto {
    @NotBlank(message = "게시글 작성에 필요한 제목을 입력해주세요")
    @ApiModelProperty(value = "게시글 작성에 필요한 제목", example = "test Title")
    private String title;

    @NotBlank(message = "게시글 작성에 필요한 내용을 입력해주세요")
    @ApiModelProperty(value = "게시글 작성에 필요한 내용", example = "test Content")
    private String content;
}
