package classproject.dearme.domain.schedule;

import classproject.dearme.domain.base.BaseEntity;
import classproject.dearme.domain.timecapsule.TimeCapsule;

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
public class ToDo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "todo_id")
	private Long id; // PK로 활용하기 위한 id값

	private String content;

	private boolean checkTodo;

	private String startTime;

	private String endTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;




	public static ToDo getToDo(TodoInfoDto todoInfoDto, Schedule schedule) {
		return ToDo.builder()
			.content(todoInfoDto.getContent())
			.checkTodo(todoInfoDto.isCheckTodo())
			.startTime(todoInfoDto.getStartTime())
			.endTime(todoInfoDto.getEndTime())
			.schedule(schedule)
			.build();
	}

}
