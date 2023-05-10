package umc.assac.API_Server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.assac.API_Server.domain.user.User;

import java.time.LocalDateTime;

// 사용자 정보를 조회하는데에 활용되는 Dto
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserInfoDto {
    private String username;
    private LocalDateTime createdDate;
    private String email;
    private double degree;
    private boolean isReported;

    public static UserInfoDto toDto(User user) {
        return UserInfoDto.builder()
                .username(user.getUsername())
                .createdDate(user.getCreatedDate())
                .email(user.getEmail())
                .degree(user.getDegree())
                .isReported(user.isReport())
                .build();
    }

}
