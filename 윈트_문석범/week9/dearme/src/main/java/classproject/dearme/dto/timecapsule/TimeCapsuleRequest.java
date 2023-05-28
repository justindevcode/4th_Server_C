package classproject.dearme.dto.timecapsule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "타임캡슐 Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TimeCapsuleRequest {

	@ApiModelProperty(value = "유저 이름")
	private String userName;

	@ApiModelProperty(value = "오늘 날짜")
	private String toDay;

	@ApiModelProperty(value = "받을 날짜")
	private String nextDay;

	@ApiModelProperty(value = "내용")
	private String content;

}
