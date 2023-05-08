package carrot.backend.domain.post.entity;

import carrot.backend.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.persistence.EnumType.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(STRING)
    private ItemCategory itemCategory;
    @Enumerated(STRING)
    private Status status;
    private String title;
    private int price;
    private String content;
    private String place;
    private int request;
    @Column(name = "`like`")
    private int like;
    private int view;
    private String date;

    public void editPost(String title, int price, String content, String place) {
        this.title = title;
        this.price = price;
        this.content = content;
        this.place = place;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
