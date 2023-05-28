package classproject.dearme.dto.schedule;

import classproject.dearme.domain.schedule.ToDo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
