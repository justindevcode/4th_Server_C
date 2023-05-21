package classproject.dearme.service.schedule;

import classproject.dearme.domain.schedule.Schedule;
import classproject.dearme.domain.schedule.ToDo;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.schedule.ScheduleInfoDto;
import classproject.dearme.dto.schedule.TodoInfoDto;
import classproject.dearme.repository.schedule.ScheduleRepository;
import classproject.dearme.repository.schedule.ToDoRepository;
import classproject.dearme.repository.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final ToDoRepository toDoRepository;
	private final UserRepository userRepository;

	@Transactional
	public ScheduleInfoDto save(ScheduleInfoDto scheduleInfoDto) {
		User user = userRepository.findByUsername(scheduleInfoDto.getUsername());
		Schedule schedule = Schedule.getSchedule(scheduleInfoDto, user);
		scheduleRepository.save(schedule);
		return ScheduleInfoDto.toDto(schedule);
	}

	@Transactional
	public TodoInfoDto saveTodo(TodoInfoDto todoInfoDto) {
		Schedule schedule = scheduleRepository.getReferenceById(todoInfoDto.getScheduleId());
		ToDo toDo = ToDo.getToDo(todoInfoDto, schedule);
		toDoRepository.save(toDo);
		return TodoInfoDto.toDto(toDo);
	}

	@Transactional
	public TodoInfoDto updateTodo(TodoInfoDto todoInfoDto) {
		ToDo findTodo = toDoRepository.getReferenceById(todoInfoDto.getId());
		findTodo.setCheckTodo(todoInfoDto.isCheckTodo());
		findTodo.setContent(todoInfoDto.getContent());
		findTodo.setStartTime(todoInfoDto.getStartTime());
		findTodo.setEndTime(todoInfoDto.getEndTime());
		return TodoInfoDto.toDto(findTodo);
	}

	@Transactional
	public String deleteSchedule(Long id) {
		scheduleRepository.deleteById(id);
		return "id = " + id + " ScheduleDeleteSuccess";
	}

	@Transactional
	public String deleteTodo(Long id) {
		toDoRepository.deleteById(id);
		return "id = " + id + " TodoDeleteSuccess";
	}

	@Transactional
	public List<ScheduleInfoDto> getScheduleInfo(String username) {
		List<Schedule> resultDomain =  scheduleRepository.findByUser_username(username);
		List<ScheduleInfoDto> result = new ArrayList<>();
		for (Schedule schedule : resultDomain) {
			List<ToDo> toDos = toDoRepository.findBySchedule(schedule);
			result.add(ScheduleInfoDto.toDto(schedule));
		}
		return result;
	}

	@Transactional
	public List<TodoInfoDto> getTodoInfo(Long scheduleId) {
		List<ToDo> resultDomain =  toDoRepository.findBySchedule_id(scheduleId);
		List<TodoInfoDto> result = new ArrayList<>();
		for (ToDo toDo : resultDomain) {
			result.add(TodoInfoDto.toDto(toDo));
		}
		return result;
	}



}
