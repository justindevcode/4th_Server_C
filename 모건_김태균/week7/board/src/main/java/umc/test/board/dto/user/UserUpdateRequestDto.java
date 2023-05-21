package umc.test.board.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private int age;

    @Builder
    public UserUpdateRequestDto(String name, int age){
        this.name = name;
        this.age = age;
    }
}
