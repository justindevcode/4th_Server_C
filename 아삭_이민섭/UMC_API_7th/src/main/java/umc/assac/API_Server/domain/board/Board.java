package umc.assac.API_Server.domain.board;

import lombok.*;
import org.hibernate.annotations.*;
import umc.assac.API_Server.domain.base.BaseEntity;
import umc.assac.API_Server.domain.category.Category;
import umc.assac.API_Server.domain.comment.Comment;
import umc.assac.API_Server.domain.user.User;
import umc.assac.API_Server.dto.board.BoardCreateDto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

// 게시글과 관련된 정보를 저장하는 Board Entity 선언
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Getter
@Entity
@Builder
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id; // PK로 사용할 id

    @Column(nullable = false)
    private String title; // 게시글의 제목

    @Column(nullable = false)
    @Lob
    private String content; // 게시글의 내용

    @Column(nullable = false)
    private String writer; // 게시글 작성자

    @Column(nullable = false)
    private Integer likeCount; // 게시글 좋아요 수

    @Column(nullable = false)
    private Integer viewCount; // 게시글 조회수

    @Column(nullable = false)
    private boolean reported; // 게시글 신고 여부

    @Column(nullable = false)
    private boolean reserved; // 게시글 예약 여부

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category; // 게시글이 어떠한 범주에 속하는지 관리하기 위하여 Category와 연관 관계 형성

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.PERSIST)
    List<Comment> comments = new ArrayList<>(); // 게시글에 작성되는 댓글을 관리하기 위하여 Comment와 연관 관계 형성

    public void updateBoard() {
        this.viewCount++;
    }

    public void updateBoard(String content) {
        this.content = content;
    }

    public void reserveBoard() {
        this.reserved = true;
    }

    public static Board getBoard(BoardCreateDto createDto, Category category, User user) {
        return Board.builder()
                .title(createDto.getTitle())
                .content(createDto.getContent())
                .writer(user.getUsername())
                .viewCount(0)
                .likeCount(0)
                .reported(false)
                .reserved(false)
                .category(category)
                .build();
    }
}
