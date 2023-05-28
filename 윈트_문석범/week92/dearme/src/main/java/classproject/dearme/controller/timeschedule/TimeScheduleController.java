package classproject.dearme.controller.timeschedule;

import classproject.dearme.dto.timeschedule.DayScheduleRequest;
import classproject.dearme.dto.timeschedule.ToDoScheduleRequest;
import classproject.dearme.response.Response;
import classproject.dearme.service.timeschedule.TimeScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

@Api(tags = "타임스케줄")
@RestController
@RequiredArgsConstructor
@RequestMapping("/timeschedule")
public class TimeScheduleController {

	private final TimeScheduleService timeScheduleService;

	@ApiOperation(
		value = "스케줄날짜등록",
		notes = "todo리스트를 담는 하루날짜 등록 날짜 date를 입력할때는 연도-월-일 ex)2023-05-23 형식을 맞춰주세요 API")
	@PostMapping("/day")
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveDaySchedule(@RequestBody DayScheduleRequest dayScheduleRequest) {
		return Response.success(timeScheduleService.saveDaySchedule(dayScheduleRequest));
	}

	@ApiOperation(
		value = "할일등록",
		notes = "날짜 식별자를 기반으로 할일을 등록하는 API")
	@PostMapping("/todo")
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveToDoSchedule(@RequestBody ToDoScheduleRequest toDoScheduleRequest) {
		return Response.success(timeScheduleService.saveToDoSchedule(toDoScheduleRequest));
	}

	@ApiOperation(
		value = "일주일 단위 날짜 찾기",
		notes = "기준 요일을 넣으면 그날부터 7일간의 일정을 보여주는 API")
	@ApiImplicitParams(
		{
			@ApiImplicitParam(
				name = "userName"
				, value = "사용자이름"
				, required = true
			),
			@ApiImplicitParam(
				name = "year"
				, value = "연도"
				, required = true
			),
			@ApiImplicitParam(
				name = "month"
				, value = "월"
				, required = true
			),
			@ApiImplicitParam(
				name = "day"
				, value = "일"
				, required = true
			)
		}
	)

	@GetMapping("/{userName}/{year}/{month}/{day}")
	@ResponseStatus(HttpStatus.OK)
	public Response getWeekSchedule(@PathVariable String userName, @PathVariable int year,
		@PathVariable int month, @PathVariable int day) {
		return Response.success(timeScheduleService.getWeekSchedule(userName, year, month, day));
	}

	@ApiOperation(
		value = "스케줄 삭제",
		notes = "스케줄을 삭제하는 하위 Todo도 찾을수없게됩니다. API")
	@ApiImplicitParam(
		name = "id"
		, value = "스케줄 id"
		, required = true
	)
	@DeleteMapping("/day/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteDaySchedule(@PathVariable Long id) {
		return Response.success(timeScheduleService.deleteDaySchedule(id));
	}

	@ApiOperation(
		value = "일정 삭제",
		notes = "일정을 삭제하는 API")
	@ApiImplicitParam(
		name = "id"
		, value = "할일 id"
		, required = true
	)
	@DeleteMapping("/todo/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteToDoSchedule(@PathVariable Long id) {
		return Response.success(timeScheduleService.deleteToDoSchedule(id));
	}

	@ApiOperation(
		value = "편의용 모든 스케줄 삭제",
		notes = "모든 유저의 스케줄이 전부 삭제됩니다. API")
	@DeleteMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteAll() {
		return Response.success(timeScheduleService.deleteAll());
	}

	@ApiOperation(
		value = "스케줄 수정",
		notes = "스케줄을 편집한는 API")
	@ApiImplicitParam(
		name = "id"
		, value = "스케줄 id"
		, required = true
	)
	@PatchMapping("/day/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response upDateDaySchedule(@PathVariable Long id,
		@RequestBody DayScheduleRequest dayScheduleRequest) {
		return Response.success(timeScheduleService.upDateDaySchedule(id, dayScheduleRequest));
	}

	@ApiOperation(
		value = "일정 수정",
		notes = "일정을 수정하는 API")
	@ApiImplicitParam(
		name = "id"
		, value = "할일 id"
		, required = true
	)
	@PatchMapping("/todo/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response upDateToDoSchedule(@PathVariable Long id,
		@RequestBody ToDoScheduleRequest toDoScheduleRequest) {
		return Response.success(timeScheduleService.upDateToDoSchedule(id, toDoScheduleRequest));
	}

	@ApiOperation(
		value = "하루 단위 날짜 찾기",
		notes = "기준 요일을 넣으면 그날의 일정을 보여주는 API")
	@ApiImplicitParams(
		{
			@ApiImplicitParam(
				name = "userName"
				, value = "사용자이름"
				, required = true
			),
			@ApiImplicitParam(
				name = "year"
				, value = "연도"
				, required = true
			),
			@ApiImplicitParam(
				name = "month"
				, value = "월"
				, required = true
			),
			@ApiImplicitParam(
				name = "day"
				, value = "일"
				, required = true
			)
		}
	)

	@GetMapping("/search/{userName}/{year}/{month}/{day}")
	@ResponseStatus(HttpStatus.OK)
	public Response getSearchSchedule(@PathVariable String userName, @PathVariable int year,
		@PathVariable int month, @PathVariable int day) {
		return Response.success(timeScheduleService.searchDayByUserNameAndDate(userName, year, month, day));
	}
}
