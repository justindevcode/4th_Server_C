package umc.assac.API_Server.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

// 회원 정보를 삭제하는데에 활용 되는 Dto
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDeleteDto {
    @NotBlank(message = "회원 탈퇴를 진행하기 위하여 회원님의 비밀번호를 입력해주세요")
    @ApiModelProperty(value = "회원 탈퇴를 진행하기 위한 비밀번호", example = "1234")
    private String password;
}
