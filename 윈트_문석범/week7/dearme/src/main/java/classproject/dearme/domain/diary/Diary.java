package classproject.dearme.domain.diary;

import classproject.dearme.domain.base.BaseEntity;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.diary.DiaryInfoDto;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public static Diary getDiary(DiaryInfoDto diaryInfoDto, User user) {
		return Diary.builder()
			.coordinateX(diaryInfoDto.getCoordinateX())
			.coordinateY(diaryInfoDto.getCoordinateY())
			.imageType(diaryInfoDto.getImageType())
			.user(user)
			.build();
	}

}
