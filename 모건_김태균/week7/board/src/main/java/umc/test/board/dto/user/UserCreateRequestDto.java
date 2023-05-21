package umc.test.board.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.test.board.domain.Post;
import umc.test.board.domain.User;

@Getter
@NoArgsConstructor
public class UserCreateRequestDto {
    private String name;
    private int age;

    @Builder
    public UserCreateRequestDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .age(age)
                .build();
    }
}