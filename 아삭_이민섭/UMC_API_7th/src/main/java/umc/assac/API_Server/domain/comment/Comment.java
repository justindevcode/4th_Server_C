package umc.assac.API_Server.domain.comment;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import umc.assac.API_Server.domain.base.BaseEntity;
import umc.assac.API_Server.domain.board.Board;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// 댓글과 관련된 정보를 관리하기 위한 Comment Entity 설계
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Getter
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id; // pk로 활용할 id

    @Column(nullable = false)
    private String content; // 댓글 내용

    @Column(nullable = false)
    private String writer; // 댓글 작성자

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board; // 댓글이 작성된 게시글을 확인하기 위하여 Board와 연관 관계 형성

    public void updateComment(String content) {
        this.content = content;
    }

    public static Comment getComment(String content, String writer, Board board) {
        return Comment.builder()
                .content(content)
                .writer(writer)
                .board(board)
                .build();
    }
}
