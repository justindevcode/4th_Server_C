package umc.assac.API_Server.dto.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

// 카테고리를 새롭게 생성하는데에 활용할 Dto
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryCreateDto {
    @NotBlank(message = "새롭게 생성할 카테고리의 이름을 입력해주세요")
    @ApiModelProperty(value = "새롭게 생성할 카테고리의 이름", example = "test")
    private String categoryName; // 새롭게 생성할 카테고리의 이름
}
