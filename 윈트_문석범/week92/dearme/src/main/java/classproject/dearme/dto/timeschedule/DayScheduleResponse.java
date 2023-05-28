package classproject.dearme.dto.timeschedule;

import classproject.dearme.domain.timeschedule.DaySchedule;
import io.swagger.annotations.ApiModel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "day 스케줄 Response/ ")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DayScheduleResponse {

	private Long dayId;

	private String today;

	private String tomorrow;

	private String date;

	private List<ToDoScheduleResponse> toDo;

	public static DayScheduleResponse toDto(DaySchedule daySchedule) {
		if (daySchedule == null) {
			return null;
		}
		DayScheduleResponse dayScheduleResponse = DayScheduleResponse.builder()
			.dayId(daySchedule.getId())
			.today(daySchedule.getToday())
			.tomorrow(daySchedule.getTomorrow())
			.date(daySchedule.getDate())
			.toDo(daySchedule.getToDoSchedules().stream().map(tem -> ToDoScheduleResponse.toDto(tem)).collect(
				Collectors.toList()))
			.build();
		return dayScheduleResponse;
	}

//	public void addToDoScheduleResponse(ToDoScheduleResponse toDoScheduleResponse) {
//		toDo.add(toDoScheduleResponse);
//	}
}
