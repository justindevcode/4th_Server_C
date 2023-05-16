package umc.assac.API_Server.domain.category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 게시글이 어떠한 범주에 속하는지 이를 관리하기 위한 Category Entity 설계
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id; // PK로 활용할 id

    @Column(nullable = false)
    private String categoryName; // 카테고리의 이름

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
