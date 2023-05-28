package umc.assac.API_Server.domain.refreshToken;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RefreshToken {

    @Id
    @Column(name = "refreshToken_id")
    private String id;

    @Column(nullable = false)
    private String token;
}
