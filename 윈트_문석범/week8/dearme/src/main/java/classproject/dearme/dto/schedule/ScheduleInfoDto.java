package classproject.dearme.dto.schedule;

import classproject.dearme.domain.schedule.Schedule;
import classproject.dearme.domain.schedule.ToDo;
import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.user.User;
import io.swagger.annotations.ApiModel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@ApiModel(value = "스케줄 DTO date=날짜 week=요일")
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
			.username(schedule.getUser().getUsername())
			.build();
	}

}
