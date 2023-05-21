package classproject.dearme.dto.diary;

import classproject.dearme.domain.diary.Diary;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "다이어리 DTO coordinateX=이미지X좌표 coordinateY=이미지Y좌표 imageType=이미지파일명")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DiaryInfoDto {

	private Long id;

	private String username;

	private int coordinateX;

	private int coordinateY;

	private String imageType;

	public static DiaryInfoDto toDto(Diary diary) {
		return DiaryInfoDto.builder()
			.id(diary.getId())
			.username(diary.getUser().getUsername())
			.coordinateX(diary.getCoordinateX())
			.coordinateY(diary.getCoordinateY())
			.imageType(diary.getImageType())
			.build();
	}


}
