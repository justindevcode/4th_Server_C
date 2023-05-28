package classproject.dearme.controller.user;

import classproject.dearme.dto.user.FriendInfoDto;
import classproject.dearme.dto.user.UserCreateDto;
import classproject.dearme.response.Response;
import classproject.dearme.service.user.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "친구")
@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

	private final FriendService friendService;

	@ApiOperation(
		value = "친구등록",
		notes = "친구등록하는 사용하는 API me(내가)(유저이름) opponent(상대방을)(유저이름)을 친구추가")
	@PostMapping("/{me}/{opponent}")
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveFriend(@PathVariable String me, @PathVariable String opponent) {
		return Response.success(friendService.crateFriend(me, opponent));
	}

	@ApiOperation(
		value = "친구삭제",
		notes = "친구를 삭제할때 사용하는 API me(내가)(유저이름) opponent(상대방을)(유저이름)을 친구삭제")
	@DeleteMapping("/delete/{me}/{opponent}")
	@ResponseStatus(HttpStatus.CREATED)
	public Response deleteFriend(@PathVariable String me, @PathVariable String opponent) {
		return Response.success(friendService.deleteFriend(me, opponent));
	}

	@ApiOperation(
		value = "친구검색",
		notes = "친구를 검색하는 사용하는 API me(내가)(유저이름) 등록해둔 친구목록")
	@GetMapping("my/{me}")
	@ResponseStatus(HttpStatus.CREATED)
	public Response myFriend(@PathVariable String me) {
		return Response.success(friendService.getHisFollower(me));
	}

	@ApiOperation(
		value = "팔로워검색",
		notes = "나를 친구한 사람들 검색 API me(나를)(유저이름) 나를 등록한 사람들 목록 ")
	@GetMapping("opponent/{me}")
	@ResponseStatus(HttpStatus.CREATED)
	public Response opponentFriend(@PathVariable String me) {
		return Response.success(friendService.getHisFollowee(me));
	}

	@ApiOperation(
		value = "나를 친구했지만 내가 친추안한사람",
		notes = "친추 알람용 나를 친추한사람 중에 내가 친추안한사람 API me(나)(유저이름) 아직 맞친구 안한 사람")
	@GetMapping("yet/{me}")
	@ResponseStatus(HttpStatus.CREATED)
	public Response yetFriend(@PathVariable String me) {
		return Response.success(friendService.getFriendYet(me));
	}

	@ApiOperation(
		value = "모든친구 삭제하기",
		notes = "모든 친구 삭제 API")
	@DeleteMapping("/deleteAll")
	@ResponseStatus(HttpStatus.CREATED)
	public Response deleteAllFriend() {
		return Response.success(friendService.deleteAll());
	}





}
