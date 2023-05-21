package umc.assac.API_Server.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.assac.API_Server.domain.board.Board;

// 게시글의 간단한 정보를 조회하는데에 활용되는 DTO
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BoardResponseDto {
    private String title;
    private String content;
    private String writer;
    private Integer likeCount;
    private Integer viewCount;

    public static BoardResponseDto toDto(Board board) {
        return BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .likeCount(board.getLikeCount())
                .viewCount(board.getViewCount())
                .build();
    }
}
