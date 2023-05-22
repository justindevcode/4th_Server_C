package umc.assac.API_Server.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

// 새롭게 회원을 등록하는 데에 활용 되는 Dto
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCreateDto {

    @NotBlank(message = "가입을 위한 사용자 아이디를 입력해주세요")
    @ApiModelProperty(value = "가입하기 위한 사용자의 아이디, 중복시 오류 발생", example = "test123")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영문 대소문자, 숫자로 구성되어야합니다.")
    @Length(min = 6, max = 10, message = "최소 6자 이상, 최대 10자 미만의 아이디를 입력해주세요.")
    private String username; // 중복되는 값이 존재할 경우 오류 반환

    @NotBlank(message = "가입을 위한 사용자 이메일을 입력해주세요")
    @ApiModelProperty(value = "가입하기 위한 사용자의 이메일, 중복시 오류 발생", example = "test123@test.com")
    @Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", message = "이메일의 형식은 test123@test.com을 만족해야합니다.")
    private String email; // 중복되는 값이 존재할 경우 오류 반환

    @NotBlank(message = "가입을 위한 사용자 이름을 입력해주세요")
    @ApiModelProperty(value = "가입하기 위한 사용자의 이름", example = "test")
    @Length(min = 2, max = 5, message = "이름은 최소 2자, 최대 5자입니다.")
    private String name;

    @NotBlank(message = "가입을 위한 사용자 비밀번호를 입력해주세요")
    @ApiModelProperty(value = "가입하기 위한 사용자의 비밀번호", example = "1234")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$",
            message = "비밀번호는 영문과 특수문자, 숫자를 포함하여 8자 이상이어야합니다.")
    @Length(min = 8, max = 20, message = "최소 8자, 최대 20자의 비밀번호를 입력해주세요.")
    private String password;
}
