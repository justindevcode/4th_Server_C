package classproject.dearme.domain.timecapsule;

import classproject.dearme.domain.base.BaseEntity;
import classproject.dearme.domain.user.Users;
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
	private Users users;

	private String toDay;

	private String nextDay;

	private String content;

	public TimeCapsule(Users users, String toDay, String nextDay, String content) {
		this.users = users;
		this.toDay = toDay;
		this.nextDay = nextDay;
		this.content = content;
	}
}
