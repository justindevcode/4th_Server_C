package classproject.dearme.domain.diary;

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
public class Diary extends BaseEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diary_id")
	private Long id; // PK로 활용하기 위한 id값

	private int coordinateX;

	private int coordinateY;

	private String imageType;

	private String title;

	private String color;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Users users;

	public Diary(int coordinateX, int coordinateY, String imageType, String title, String color,
		Users users) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.imageType = imageType;
		this.title = title;
		this.color = color;
		this.users = users;
	}
}
