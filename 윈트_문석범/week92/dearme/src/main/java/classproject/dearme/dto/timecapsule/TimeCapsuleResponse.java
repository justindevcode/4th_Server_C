package classproject.dearme.dto.timecapsule;


import classproject.dearme.domain.timecapsule.TimeCapsule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "타임캡슐 Response ")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TimeCapsuleResponse {

	@ApiModelProperty(value = "타임캡슐 식별자")
	private Long timeCapsuleId;

	@ApiModelProperty(value = "유저 이름")
	private String userName;

	@ApiModelProperty(value = "오늘 날짜")
	private String toDay;

	@ApiModelProperty(value = "받을 날짜")
	private String nextDay;

	@ApiModelProperty(value = "내용")
	private String content;

	public static TimeCapsuleResponse toDto(TimeCapsule timeCapsule) {
		TimeCapsuleResponse timeCapsuleResponse = TimeCapsuleResponse.builder()
			.timeCapsuleId(timeCapsule.getId())
			.userName(timeCapsule.getUsers().getUsername())
			.toDay(timeCapsule.getToDay())
			.nextDay(timeCapsule.getNextDay())
			.content(timeCapsule.getContent())
			.build();

		return timeCapsuleResponse;
	}


}
