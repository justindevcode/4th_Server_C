package umc.assac.API_Server.dto.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.assac.API_Server.domain.comment.Comment;

// 댓글 관련 정보를 노출하는데에 활용하는 Dto
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentDto {
    private String content;
    private String writer;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getContent(), comment.getWriter());
    }
}
