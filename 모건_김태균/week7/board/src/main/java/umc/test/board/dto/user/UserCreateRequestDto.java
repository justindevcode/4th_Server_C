package umc.test.board.dto.user;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.test.board.domain.Post;
import umc.test.board.domain.User;

@Getter
@NoArgsConstructor
public class UserCreateRequestDto {
    private String username;
    private String password;
    private String name;
    private int age;

    @Builder
    public UserCreateRequestDto(String username, String password,String name, int age) {
        this.username=username;
        this.password=password;
        this.name = name;
        this.age = age;
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .age(age)
                .build();
    }
}