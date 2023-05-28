package classproject.dearme.repository.timecapsule;

import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.user.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeCapsuleRepository extends JpaRepository<TimeCapsule, Long> {

	List<TimeCapsule> findByUsers(Users users);

	void deleteById(Long id);

}
