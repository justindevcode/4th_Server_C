package classproject.dearme.controller.schedule;

import classproject.dearme.dto.schedule.ScheduleInfoDto;
import classproject.dearme.dto.schedule.TodoInfoDto;
import classproject.dearme.response.Response;
import classproject.dearme.service.schedule.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "스케줄")
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

	private final ScheduleService scheduleService;

	@ApiOperation(
		value = "스케줄등록",
		notes = "일정의 기반이되는 스케줄 요일 등록 API")
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Response saveSchedule(@RequestBody ScheduleInfoDto scheduleInfoDto) {
		return Response.success(scheduleService.save(scheduleInfoDto));
	}

	@ApiOperation(
		value = "할일등록",
		notes = "스케줄 요일 기반으로 할일을 등록하는 API")
	@PostMapping("/todo")
	@ResponseStatus(HttpStatus.OK)
	public Response saveTodo(@RequestBody TodoInfoDto todoInfoDto) {
		return Response.success(scheduleService.saveTodo(todoInfoDto));
	}

	@ApiOperation(
		value = "스케줄보기",
		notes = "유저가 등록한 스케줄 보기 API")
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response getScheduleInfo(@PathVariable String username) {
		return Response.success(scheduleService.getScheduleInfo(username));
	}

	@ApiOperation(
		value = "할일 보기",
		notes = "유저가 등록한 스캐줄요일에 등록된 할일 API")
	@GetMapping("/todo/{scheduleid}")
	@ResponseStatus(HttpStatus.OK)
	public Response getScheduleInfo(@PathVariable Long scheduleid) {
		return Response.success(scheduleService.getTodoInfo(scheduleid));
	}

	@ApiOperation(
		value = "할일 수정",
		notes = "할일을 수정하는 API")
	@PatchMapping("/todo")
	@ResponseStatus(HttpStatus.OK)
	public Response updateTodoInfo(@RequestBody TodoInfoDto todoInfoDto) {
		return Response.success(scheduleService.updateTodo(todoInfoDto));
	}

	@ApiOperation(
		value = "스케줄 삭제",
		notes = "스케줄을 삭제하는 API")
	@DeleteMapping("/{scheduleid}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteSchedule(@PathVariable Long scheduleid) {
		return Response.success(scheduleService.deleteSchedule(scheduleid));
	}

	@ApiOperation(
		value = "할일 삭제",
		notes = "할일을 삭제하는 API")
	@DeleteMapping("/todo/{todoid}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteTodo(@PathVariable Long todoid) {
		return Response.success(scheduleService.deleteTodo(todoid));
	}

	@ApiOperation(
		value = "스케줄 할일 모두 삭제",
		notes = "스케줄 할일 모두삭제하는 API")
	@DeleteMapping("/deleteAll")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteAllSchedule() {
		scheduleService.deleteAll();
		return Response.success("Success deleteAllSchedule");
	}
}
