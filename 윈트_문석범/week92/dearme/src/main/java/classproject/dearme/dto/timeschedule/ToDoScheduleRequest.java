package classproject.dearme.dto.timeschedule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "todo 스케줄 Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ToDoScheduleRequest {

	@ApiModelProperty(value = "속할스케줄id식별자")
	private Long dayScheduleId;

	@ApiModelProperty(value = "할일내용")
	private String content;

	@ApiModelProperty(value = "체크표시")
	private boolean checkTodo;

	@ApiModelProperty(value = "시작시간")
	private String startTime;

	@ApiModelProperty(value = "끝시간")
	private String endTime;

}
