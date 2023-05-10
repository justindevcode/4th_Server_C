package umc.assac.API_Server.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

// 새롭게 회원을 등록하는 데에 활용 되는 Dto
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCreateDto {

    @NotBlank(message = "가입을 위한 사용자 아이디를 입력해주세요")
    @ApiModelProperty(value = "가입하기 위한 사용자의 아이디, 중복시 오류 발생", example = "test123")
    private String username; // 중복되는 값이 존재할 경우 오류 반환

    @NotBlank(message = "가입을 위한 사용자 이메일을 입력해주세요")
    @ApiModelProperty(value = "가입하기 위한 사용자의 이메일, 중복시 오류 발생", example = "test123@test.com")
    private String email; // 중복되는 값이 존재할 경우 오류 반환

    @NotBlank(message = "가입을 위한 사용자 이름을 입력해주세요")
    @ApiModelProperty(value = "가입하기 위한 사용자의 이름", example = "test")
    private String name;

    @NotBlank(message = "가입을 위한 사용자 비밀번호를 입력해주세요")
    @ApiModelProperty(value = "가입하기 위한 사용자의 비밀번호", example = "1234")
    private String password;
}
