package umc.assac.API_Server.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.assac.API_Server.domain.board.Board;
import umc.assac.API_Server.dto.comment.CommentDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 단일 게시글의 자세한 정보를 확인할 수 있는 DTO
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BoardDetailedResponseDto {
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String writer; // 게시글 작성자
    private Integer likeCount; // 게시글 좋아요 수
    private Integer viewCount; // 게시글 조회수
    private String category; // 게시글이 속한 카테고리
    private boolean isReported; // 게시글 신고 유무
    private boolean isReserved; // 게시글 예약 유무
    private List<CommentDto> comments = new ArrayList<>(); // 게시글에 작성된 댓글들, Entity 원본 노출을 피하기 위해서 DTO 활용

    public static BoardDetailedResponseDto toDto(Board board) {
        return BoardDetailedResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .likeCount(board.getLikeCount())
                .viewCount(board.getViewCount())
                .category(board.getCategory().getCategoryName())
                .isReported(board.isReported())
                .isReserved(board.isReserved())
                .comments(board.getComments().stream().map(CommentDto::toDto).collect(Collectors.toList()))
                .build();
    }
}
