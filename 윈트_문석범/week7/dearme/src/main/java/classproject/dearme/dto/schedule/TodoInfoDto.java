package classproject.dearme.dto.schedule;

import classproject.dearme.domain.schedule.Schedule;
import classproject.dearme.domain.schedule.ToDo;
import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.dto.timecapsule.TimeCapsuleInfoDto;
import io.swagger.annotations.ApiModel;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "할일 DTO content=내용 checkTodo=체크여부 startTime=시작시간 endTime=끝시간 scheduleId=스케줄요일Id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TodoInfoDto {

	private Long id;

	private String content;

	private boolean checkTodo;

	private String startTime;

	private String endTime;

	private Long scheduleId;

	public static TodoInfoDto toDto(ToDo toDo) {
		return TodoInfoDto.builder()
			.id(toDo.getId())
			.content(toDo.getContent())
			.checkTodo(toDo.isCheckTodo())
			.startTime(toDo.getStartTime())
			.endTime(toDo.getEndTime())
			.scheduleId(toDo.getSchedule().getId())
			.build();
	}

}
