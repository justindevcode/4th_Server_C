package classproject.dearme.domain.user;

import classproject.dearme.domain.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Friend extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "friend_id")
	private Long id; // PK로 활용하기 위한 id값

	@ManyToOne
	private Users follower;

	@ManyToOne
	private Users followee;

	public static Friend getFriendList(Users users, Users opUsers) {
		return Friend.builder()
			.follower(users)
			.followee(opUsers)
			.build();
	}

}
