package classproject.dearme.controller.user;

import classproject.dearme.dto.Response;
import classproject.dearme.dto.userjwt.UserRequestDto;
import classproject.dearme.jwt.JwtTokenProvider;
import classproject.dearme.lib.Helper;
import classproject.dearme.service.user.UserJwtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유저 jwt")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/userjwt")
@RestController
public class UserJwtController {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserJwtService usersService;
	private final Response response;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@Validated @RequestBody UserRequestDto.SignUp signUp, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return usersService.signUp(signUp);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @RequestBody UserRequestDto.Login login, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return usersService.login(login);
	}

	@PostMapping("/reissue")
	public ResponseEntity<?> reissue(@Validated @RequestBody UserRequestDto.Reissue reissue, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return usersService.reissue(reissue);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@Validated @RequestBody UserRequestDto.Logout logout, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return usersService.logout(logout);
	}

	@GetMapping("/authority")
	public ResponseEntity<?> authority() {
		log.info("ADD ROLE_ADMIN");
		return usersService.authority();
	}

	@GetMapping("/userTest")
	public ResponseEntity<?> userTest() {
		log.info("ROLE_USER TEST");
		return response.success();
	}

	@GetMapping("/adminTest")
	public ResponseEntity<?> adminTest() {
		log.info("ROLE_ADMIN TEST");
		return response.success();
	}
}