package umc.assac.API_Server.dto.token;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenRequestDto {
    @NotBlank(message = "토큰 재발급을 위하여 accessToken을 입력해주세요")
    @ApiModelProperty(value = "토큰 재발급을 위한 accessToken", example = "accessToken")
    private String accessToken;

    @NotBlank(message = "토큰 재발급을 위하여 refreshToken을 입력해주세요")
    @ApiModelProperty(value = "토큰 재발급을 위한 refreshToken", example = "refreshToken")
    private String refreshToken;
}
