package classproject.dearme.domain.timeschedule;

import classproject.dearme.domain.base.BaseEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class ToDoSchedule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "to_do_schedule_id")
	private Long id; // PK로 활용하기 위한 id값

	private String content;

	private boolean checkTodo;

	private String startTime;

	private String endTime;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "day_schedule_id")
	private DaySchedule daySchedule;


	public ToDoSchedule(String content, boolean checkTodo, String startTime, String endTime) {
		this.content = content;
		this.checkTodo = checkTodo;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public void update(String content, boolean checkTodo, String startTime, String endTime) {
		this.content = content;
		this.checkTodo = checkTodo;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
