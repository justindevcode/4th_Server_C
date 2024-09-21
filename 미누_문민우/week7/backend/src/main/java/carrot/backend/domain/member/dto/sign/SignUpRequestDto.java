package carrot.backend.domain.member.dto.sign;

import carrot.backend.domain.member.entity.Authority;
import carrot.backend.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequestDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일은 @를 포함해야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, message = "닉네임이 너무 짧습니다.")
    private String nickname;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "휴대폰 번호는 하이픈(-)을 포함해야 합니다.")
    private String phone;

    @NotBlank(message = "위치 정보를 입력해주세요.")
    private String location;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(this.username)
                .password(passwordEncoder.encode(this.password))
                .nickname(this.nickname)
                .phone(this.phone)
                .location(this.location)
                .authority(Authority.ROLE_USER)
                .build();
    }
}
