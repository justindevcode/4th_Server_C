package classproject.dearme.repository.schedule;

import classproject.dearme.domain.schedule.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findByUsers_username(String username);

}
