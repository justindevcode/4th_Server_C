package classproject.dearme.dto.timeschedule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@ApiModel(value = "스케줄 Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder

public class DayScheduleRequest {

	@ApiModelProperty(value = "유저 이름")
	private String userName;

	@ApiModelProperty(value = "오늘메시지")
	private String today;

	@ApiModelProperty(value = "내일메시지")
	private String tomorrow;

	@ApiModelProperty(value = "날짜(2023-05-25 형식 지켜주세요)")
	private String date;




}
