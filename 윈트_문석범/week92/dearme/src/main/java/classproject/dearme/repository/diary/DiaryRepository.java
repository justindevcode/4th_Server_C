package classproject.dearme.repository.diary;

import classproject.dearme.domain.diary.Diary;
import classproject.dearme.domain.user.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

	List<Diary> findByUsers(Users users);
}
