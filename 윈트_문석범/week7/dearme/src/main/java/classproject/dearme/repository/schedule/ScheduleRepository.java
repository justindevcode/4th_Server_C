package classproject.dearme.repository.schedule;

import classproject.dearme.domain.schedule.Schedule;
import classproject.dearme.domain.timecapsule.TimeCapsule;
import classproject.dearme.domain.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findByUser_username(String username);

}
