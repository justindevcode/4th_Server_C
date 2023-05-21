package classproject.dearme.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//유저 등록을 위한 Dto
@ApiModel(value = "유저 회원 가입 DTO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCreateDto {

	private String username;

	private String email;

	private String password;

	private String phone;
}
