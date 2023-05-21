package classproject.dearme.domain.timecapsule;

import classproject.dearme.domain.base.BaseEntity;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.timecapsule.TimeCapsuleInfoDto;
import classproject.dearme.dto.user.UserCreateDto;
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
public class TimeCapsule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "time_capsule_id")
	private Long id; // PK로 활용하기 위한 id값

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String toDay;

	private String nextDay;

	private String content;


	public static TimeCapsule getTimeCapsule(TimeCapsuleInfoDto timeCapsuleInfoDto, User user) {
		return TimeCapsule.builder()
			.user(user)
			.toDay(timeCapsuleInfoDto.getToDay())
			.nextDay(timeCapsuleInfoDto.getNextDay())
			.content(timeCapsuleInfoDto.getContent())
			.build();
	}


}
