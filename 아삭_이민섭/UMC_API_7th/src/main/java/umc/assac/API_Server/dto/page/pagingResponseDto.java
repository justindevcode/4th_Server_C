package umc.assac.API_Server.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class pagingResponseDto {
    private List<?> objectList = new ArrayList<>(); // 조회한 게시글들을 담고 있는 objectList
    private PageInfoDto pageInfoDto; // 페이지 관련 정보를 담고 있는 pageInfoDto

    public pagingResponseDto toDto(List<?> result, Page<?> pages) {
        return new pagingResponseDto(result, PageInfoDto.toDto(pages));
    }
}
