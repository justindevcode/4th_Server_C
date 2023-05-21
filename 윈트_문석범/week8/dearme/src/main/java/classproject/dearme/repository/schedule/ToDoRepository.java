package classproject.dearme.repository.schedule;

import classproject.dearme.domain.schedule.Schedule;
import classproject.dearme.domain.schedule.ToDo;
import classproject.dearme.domain.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
	List<ToDo> findBySchedule(Schedule schedule);

	List<ToDo> findBySchedule_id(Long id);


}
