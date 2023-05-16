package umc.assac.API_Server.domain.user;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import umc.assac.API_Server.domain.base.BaseEntity;
import umc.assac.API_Server.dto.user.UserCreateDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Getter
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // PK로 활용하기 위한 id값

    @Column(nullable = false, unique = true)
    private String username; // 사용자 아이디

    @Column(nullable = false, unique = true)
    private String email; // 사용자 이메일

    @Column(nullable = false)
    private String name; // 사용자 이름

    @Column(nullable = false)
    private String password; // 사용자 비밀 번호

    @Column(nullable = false)
    private Double degree; // 사용자 매너 온도

    @Column(nullable = false)
    private boolean isReport; // 사용자 신고 여부

    public static User getUser(UserCreateDto createDto) {
        return User.builder()
                .username(createDto.getUsername())
                .email(createDto.getEmail())
                .name(createDto.getName())
                .password(createDto.getPassword())
                .degree(36.5)
                .isReport(false)
                .build();
    }

    public void reportUser() {
        this.isReport = true;
    }
}
