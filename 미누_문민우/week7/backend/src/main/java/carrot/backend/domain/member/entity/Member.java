package carrot.backend.domain.member.entity;

import carrot.backend.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String location;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void editMember(String nickname, String phone, String location) {
        this.nickname = nickname;
        this.phone = phone;
        this.location = location;
    }
}
