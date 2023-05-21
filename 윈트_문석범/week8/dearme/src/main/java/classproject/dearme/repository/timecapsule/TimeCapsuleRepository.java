package classproject.dearme.repository.timecapsule;

import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeCapsuleRepository extends JpaRepository<TimeCapsule, Long> {

	List<TimeCapsule> findByUser(User user);

	void deleteById(Long id);

}
