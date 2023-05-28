package classproject.dearme.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저 로그인 DTO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLogin {

	private String username;

	private String password;

}
