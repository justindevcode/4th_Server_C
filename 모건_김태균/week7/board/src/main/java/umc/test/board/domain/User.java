package umc.test.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column
    private String loginID;

    @Column
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;
    //빌더
    @Builder
    public User(String loginID, String password, String name, int age) {
        this.loginID=loginID;
        this.password=password;
        this.name = name;
        this.age = age;
    }

    public void update(String name, int age){
        this.name = name;
        this.age = age;
    }
}