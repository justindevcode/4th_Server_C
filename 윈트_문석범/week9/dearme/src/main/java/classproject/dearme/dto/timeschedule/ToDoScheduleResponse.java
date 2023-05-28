package classproject.dearme.dto.timeschedule;

import classproject.dearme.domain.timeschedule.ToDoSchedule;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "todo 스케줄 Response")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ToDoScheduleResponse {

	private Long todoId;

	private String content;

	private boolean checkTodo;

	private String startTime;

	private String endTime;

	public static ToDoScheduleResponse toDto(ToDoSchedule toDoSchedule) {
		if (toDoSchedule == null) {
			return null;
		}
		return ToDoScheduleResponse.builder()
			.todoId(toDoSchedule.getId())
			.content(toDoSchedule.getContent())
			.checkTodo(toDoSchedule.isCheckTodo())
			.startTime(toDoSchedule.getStartTime())
			.endTime(toDoSchedule.getEndTime())
			.build();
	}

}
