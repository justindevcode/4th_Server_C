package classproject.dearme.domain.timeschedule;

import classproject.dearme.domain.base.BaseEntity;
import classproject.dearme.domain.user.Users;
import java.util.ArrayList;
import java.util.List;
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
public class DaySchedule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "day_schedule_id")
	private Long id; // PK로 활용하기 위한 id값

	private String today;

	private String tomorrow;

	private String date;

	@OneToMany
	private List<ToDoSchedule> toDoSchedules = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Users users;

	public DaySchedule(String today, String tomorrow, String date, Users users) {
		this.today = today;
		this.tomorrow = tomorrow;
		this.date = date;
		this.users = users;
	}

	public void update(String today, String tomorrow, String date) {
		this.today = today;
		this.tomorrow = tomorrow;
		this.date = date;
	}

	public void addToDoSchedules(ToDoSchedule toDoSchedule) {
		this.toDoSchedules.add(toDoSchedule);
	}

	public void deleteToDoSchedule(ToDoSchedule toDoSchedule) {
		this.toDoSchedules.remove(toDoSchedule);
	}
}
