package classproject.dearme.controller.timecapsule;

import classproject.dearme.dto.timecapsule.TimeCapsuleInfoDto;
import classproject.dearme.response.Response;
import classproject.dearme.service.timecapsule.TimeCapsuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "타임캡슐")
@RestController
@RequiredArgsConstructor
@RequestMapping("/timecapsule")
public class TimeCapsuleController {


	private final TimeCapsuleService timeCapsuleService;

	@ApiOperation(
		value = "타입캡슐등록",
		notes = "새로 타임캡슐을 등록하는 API")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveTimeCapsule(@RequestBody TimeCapsuleInfoDto timeCapsuleInfoDto) {
		return Response.success(timeCapsuleService.save(timeCapsuleInfoDto));
	}

	@ApiOperation(
		value = "타입캡슐정보",
		notes = "타임캡슐 내용을 볼때사용하는 API")
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response getTimeCapsuleInfo(@PathVariable String username) {
		return Response.success(timeCapsuleService.findAll(username));
	}

	@ApiOperation(
		value = "타입캡슐삭제",
		notes = "타입캡슐을 삭제할때 사용하는 API")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteTimeCapsule(@PathVariable Long id) {
		return Response.success(timeCapsuleService.delete(id));
	}

	@ApiOperation(
		value = "타임캡슐 모두 삭제",
		notes = "타임캡슐 모두삭제하는 API")
	@DeleteMapping("/deleteAll")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteAllTimeCapsule() {
		 timeCapsuleService.deleteAll();
		return Response.success("Success deleteAllTimeCapsule");
	}

}
