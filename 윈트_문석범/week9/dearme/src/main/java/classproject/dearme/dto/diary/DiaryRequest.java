package classproject.dearme.dto.diary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "다이어리 Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DiaryRequest {

	@ApiModelProperty(value = "유저 이름")
	private String username;

	@ApiModelProperty(value = "좌표x")
	private int coordinateX;

	@ApiModelProperty(value = "좌표y")
	private int coordinateY;

	@ApiModelProperty(value = "이미지 종류")
	private String imageType;

	@ApiModelProperty(value = "다이어리 제목")
	private String title;

	@ApiModelProperty(value = "다이어리 색깔")
	private String color;

}
