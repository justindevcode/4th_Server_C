package classproject.dearme.dto.userjwt;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class UserRequestDto {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SignUp {

		@NotEmpty(message = "이름은 필수 입력값입니다.")
		private String username;

		@NotEmpty(message = "비밀번호는 필수 입력값입니다.")
		//@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
		private String password;

		private String email;

		private String phone;
	}

	@Getter
	@Setter
	public static class Login {
		@NotEmpty(message = "이름은 필수 입력값입니다.")
		private String username;

		@NotEmpty(message = "비밀번호는 필수 입력값입니다.")
		private String password;

		public UsernamePasswordAuthenticationToken toAuthentication() {
			return new UsernamePasswordAuthenticationToken(username, password);
		}
	}

	@Getter
	@Setter
	public static class Reissue {
		@NotEmpty(message = "accessToken 을 입력해주세요.")
		private String accessToken;

		@NotEmpty(message = "refreshToken 을 입력해주세요.")
		private String refreshToken;
	}

	@Getter
	@Setter
	public static class Logout {
		@NotEmpty(message = "잘못된 요청입니다.")
		private String accessToken;

		@NotEmpty(message = "잘못된 요청입니다.")
		private String refreshToken;
	}
}
