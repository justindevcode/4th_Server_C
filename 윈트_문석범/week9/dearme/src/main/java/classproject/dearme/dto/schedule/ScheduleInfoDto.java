package classproject.dearme.dto.schedule;

import classproject.dearme.domain.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ScheduleInfoDto {

	private Long id; // PK로 활용하기 위한 id값

	private String date;

	private String week;

	private String username;


	public static ScheduleInfoDto toDto(Schedule schedule) {
		return ScheduleInfoDto.builder()
			.id(schedule.getId())
			.date(schedule.getDate())
			.week(schedule.getWeek())
			.username(schedule.getUsers().getUsername())
			.build();
	}

}
