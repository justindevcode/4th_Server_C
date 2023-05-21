package umc.assac.API_Server.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.assac.API_Server.domain.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
