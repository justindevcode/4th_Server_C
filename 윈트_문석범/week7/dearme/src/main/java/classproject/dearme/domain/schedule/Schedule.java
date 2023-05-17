package classproject.dearme.domain.schedule;

import classproject.dearme.domain.base.BaseEntity;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.schedule.ScheduleInfoDto;
import classproject.dearme.dto.schedule.TodoInfoDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Getter
@Builder
@Setter
public class Schedule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private Long id; // PK로 활용하기 위한 id값

	private String date;

	private String week;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

//	@OneToMany(cascade = CascadeType.ALL)
//	public List<ToDo> toDos = new ArrayList<ToDo>();


	public static Schedule getSchedule(ScheduleInfoDto scheduleInfoDto, User user) {
		return Schedule.builder()
			.date(scheduleInfoDto.getDate())
			.week(scheduleInfoDto.getWeek())
			.user(user)
			.build();
	}




}
