package classproject.dearme.dto.diary;


import classproject.dearme.domain.diary.Diary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "다이어리 Response ")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DiaryResponse {

	@ApiModelProperty(value = "다이어리 식별자")
	private Long diaryId;

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


	public static DiaryResponse toDto(Diary diary) {
		DiaryResponse diaryResponse = DiaryResponse.builder()
			.diaryId(diary.getId())
			.username(diary.getUsers().getUsername())
			.coordinateX(diary.getCoordinateX())
			.coordinateY(diary.getCoordinateY())
			.imageType(diary.getImageType())
			.title(diary.getTitle())
			.color(diary.getColor())
			.build();

		return diaryResponse;
	}

}
