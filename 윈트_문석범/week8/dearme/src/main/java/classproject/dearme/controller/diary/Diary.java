package classproject.dearme.controller.diary;

import classproject.dearme.dto.diary.DiaryInfoDto;
import classproject.dearme.response.Response;
import classproject.dearme.service.diary.DiaryService;
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

@Api(tags = "다이어리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class Diary {

	private final DiaryService diaryService;

	@ApiOperation(
		value = "다이어리 등록",
		notes = "다이어리를 저장하는 API")
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Response save(@RequestBody DiaryInfoDto diaryInfoDto) {
		return Response.success(diaryService.save(diaryInfoDto));
	}

	@ApiOperation(
		value = "다이어리 보기",
		notes = "다이어리를 조회하는 API")
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response getDiaryInfo(@PathVariable String username) {
		return Response.success(diaryService.findAll(username));
	}

	@ApiOperation(
		value = "다이어리 업데이트",
		notes = "다이어리를 수정하는 API")
	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public Response updateDiaryInfo(@RequestBody DiaryInfoDto diaryInfoDto) {
		return Response.success(diaryService.update(diaryInfoDto));
	}

	@ApiOperation(
		value = "다이어리 모두 삭제",
		notes = "다이어리를 모두삭제하는 API")
	@DeleteMapping("/deleteAll")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteAllDiary() {
		diaryService.deleteAll();
		return Response.success("Success deleteAllDiary");
	}
}
