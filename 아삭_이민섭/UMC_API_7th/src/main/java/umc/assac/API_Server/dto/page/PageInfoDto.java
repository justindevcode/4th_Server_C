package umc.assac.API_Server.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

// 페이지관련 정보를 담고 있는 PageInfoDto
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PageInfoDto {

    private int totalPages; // 전체 페이지 수
    private int currentPages; // 현재 페이지 수
    private int pageSize; // 페이지 내부에 위치할 수 있는 게시글들의 수
    private boolean hasNext; // 다음 페이지의 존재 유무

    public static PageInfoDto toDto(Page<?> pages) {
        return new PageInfoDto(pages.getTotalPages(), pages.getNumber()+1,
                pages.getNumberOfElements(), pages.hasNext());
    }
}
