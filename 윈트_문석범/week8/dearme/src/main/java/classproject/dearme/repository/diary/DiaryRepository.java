package classproject.dearme.repository.diary;

import classproject.dearme.domain.diary.Diary;
import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

	List<Diary> findByUser(User user);
}
